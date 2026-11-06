package com.devsenior.colmena.service.impl;

import com.devsenior.colmena.dto.ReservaRequest;
import com.devsenior.colmena.dto.ReservaResponse;
import com.devsenior.colmena.exception.DatoInvalidoException;
import com.devsenior.colmena.exception.RecursoNoEncontradoException;
import com.devsenior.colmena.exception.ReglaNegocioException;
import com.devsenior.colmena.model.*;
import com.devsenior.colmena.repository.EspacioRepository;
import com.devsenior.colmena.repository.ReservaRepository;
import com.devsenior.colmena.repository.UsuarioRepository;
import com.devsenior.colmena.service.ReservaService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    // Horario de atencion del coworking
    private static final LocalTime APERTURA = LocalTime.of(7, 0);
    private static final LocalTime CIERRE = LocalTime.of(21, 0);

    private final ReservaRepository reservaRepository;
    private final EspacioRepository espacioRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository,
                              EspacioRepository espacioRepository,
                              UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.espacioRepository = espacioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<ReservaResponse> listarMias(String email) {
        return reservaRepository.findByUsuarioEmailOrderByFechaDescHoraInicioDesc(email)
                .stream().map(this::toResponse).toList();
    }

    @Override
    public List<ReservaResponse> listarTodas(LocalDate fecha) {
        List<Reserva> reservas = fecha == null
                ? reservaRepository.findAllByOrderByFechaDescHoraInicioDesc()
                : reservaRepository.findByFechaOrderByHoraInicioAsc(fecha);
        return reservas.stream().map(this::toResponse).toList();
    }

    @Override
    public ReservaResponse crear(ReservaRequest request, String email) {
        Espacio espacio = espacioRepository.findById(request.espacioId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un espacio con id " + request.espacioId()));

        if (!espacio.isActivo()) {
            throw new ReglaNegocioException(espacio.getNombre() + " no está disponible para reservas");
        }

        validarHorario(request.fecha(), request.horaInicio(), request.horaFin());

        if (request.asistentes() > espacio.getCapacidad()) {
            throw new DatoInvalidoException(espacio.getNombre() + " admite máximo "
                    + espacio.getCapacidad() + (espacio.getCapacidad() == 1 ? " persona" : " personas"));
        }



        boolean seCruza = reservaRepository
                .existsByEspacioIdAndFechaAndEstadoAndHoraInicioLessThanAndHoraFinGreaterThan(
                        espacio.getId(), request.fecha(), EstadoReserva.CONFIRMADA,
                        request.horaFin(), request.horaInicio());
        if (seCruza) {
            throw new ReglaNegocioException(espacio.getNombre() + " ya tiene una reserva en ese horario");
        }

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el usuario " + email));

        long minutos = Duration.between(request.horaInicio(), request.horaFin()).toMinutes();

        Reserva reserva = new Reserva();
        reserva.setEspacio(espacio);
        reserva.setUsuario(usuario);   // el dueño de la reserva sale del TOKEN, nunca del JSON
        reserva.setFecha(request.fecha());
        reserva.setHoraInicio(request.horaInicio());
        reserva.setHoraFin(request.horaFin());
        reserva.setAsistentes(request.asistentes());
        reserva.setNota(request.nota() == null ? null : request.nota().trim());
        reserva.setValorTotal((int) (espacio.getPrecioHora() * minutos / 60));
        reserva.setEstado(EstadoReserva.CONFIRMADA);

        return toResponse(reservaRepository.save(reserva));
    }

    @Override
    public ReservaResponse cancelar(Long id, Authentication autenticacion) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe una reserva con id " + id));

        // El permiso de cancelar "propias" no basta: hay que verificar que SEA suya.
        // Esto no lo puede decidir @PreAuthorize: depende del dato, no solo del permiso.
        boolean puedeCancelarCualquiera = Permiso.RESERVAS_CANCELAR_TODAS.estaEn(autenticacion);
        boolean esSuya = reserva.getUsuario().getEmail().equals(autenticacion.getName());

        if (!puedeCancelarCualquiera && !esSuya) {
            throw new AccessDeniedException("Solo puedes cancelar tus propias reservas");
        }

        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new ReglaNegocioException("La reserva ya estaba cancelada");
        }

        LocalDateTime inicio = LocalDateTime.of(reserva.getFecha(), reserva.getHoraInicio());
        if (inicio.isBefore(LocalDateTime.now())) {
            throw new ReglaNegocioException("No se puede cancelar una reserva que ya comenzó");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);
        return toResponse(reservaRepository.save(reserva));
    }

    private void validarHorario(LocalDate fecha, LocalTime inicio, LocalTime fin) {
        if (!fin.isAfter(inicio)) {
            throw new DatoInvalidoException("La hora de fin debe ser posterior a la hora de inicio");
        }
        if (inicio.isBefore(APERTURA) || fin.isAfter(CIERRE)) {
            throw new DatoInvalidoException("El coworking abre de 7:00 a. m. a 9:00 p. m.");
        }
        if (inicio.getMinute() % 30 != 0 || fin.getMinute() % 30 != 0
                || inicio.getSecond() != 0 || fin.getSecond() != 0) {
            throw new DatoInvalidoException("Las reservas se hacen en bloques de 30 minutos (ej: 9:00 o 9:30)");
        }
        if (LocalDateTime.of(fecha, inicio).isBefore(LocalDateTime.now())) {
            throw new DatoInvalidoException("No puedes reservar en una fecha u hora que ya pasó");
        }
    }

    private ReservaResponse toResponse(Reserva reserva) {
        Espacio espacio = reserva.getEspacio();
        Usuario usuario = reserva.getUsuario();
        return new ReservaResponse(
                reserva.getId(),
                espacio.getId(),
                espacio.getNombre(),
                espacio.getTipo(),
                usuario.getId(),
                usuario.getNombre(),
                reserva.getFecha(),
                reserva.getHoraInicio(),
                reserva.getHoraFin(),
                reserva.getAsistentes(),
                reserva.getNota(),
                reserva.getValorTotal(),
                reserva.getEstado(),
                reserva.getCreadaEn()
        );
    }
}
