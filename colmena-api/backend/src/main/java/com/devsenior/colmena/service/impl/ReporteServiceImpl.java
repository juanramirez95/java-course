package com.devsenior.colmena.service.impl;

import com.devsenior.colmena.dto.OcupacionEspacio;
import com.devsenior.colmena.dto.ResumenResponse;
import com.devsenior.colmena.model.Espacio;
import com.devsenior.colmena.model.EstadoReserva;
import com.devsenior.colmena.model.Reserva;
import com.devsenior.colmena.repository.EspacioRepository;
import com.devsenior.colmena.repository.ReservaRepository;
import com.devsenior.colmena.repository.UsuarioRepository;
import com.devsenior.colmena.service.ReporteService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EspacioRepository espacioRepository;

    public ReporteServiceImpl(ReservaRepository reservaRepository,
                              UsuarioRepository usuarioRepository,
                              EspacioRepository espacioRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.espacioRepository = espacioRepository;
    }

    @Override
    public ResumenResponse resumen() {
        LocalDate hoy = LocalDate.now();
        LocalDate inicioMes = hoy.withDayOfMonth(1);
        LocalDate finMes = hoy.withDayOfMonth(hoy.lengthOfMonth());

        List<Reserva> delMes = reservaRepository
                .findByFechaBetweenAndEstado(inicioMes, finMes, EstadoReserva.CONFIRMADA);

        long reservasHoy = delMes.stream().filter(r -> r.getFecha().equals(hoy)).count();
        int ingresosMes = delMes.stream().mapToInt(Reserva::getValorTotal).sum();

        List<OcupacionEspacio> ocupacion = espacioRepository.findAllByOrderByNombreAsc().stream()
                .map(espacio -> ocupacionDe(espacio, delMes))
                .sorted(Comparator.comparingDouble(OcupacionEspacio::horasReservadas).reversed())
                .toList();

        return new ResumenResponse(
                reservasHoy,
                delMes.size(),
                ingresosMes,
                usuarioRepository.countByActivoTrue(),
                espacioRepository.countByActivoTrue(),
                ocupacion
        );
    }

    private OcupacionEspacio ocupacionDe(Espacio espacio, List<Reserva> reservas) {
        List<Reserva> suyas = reservas.stream()
                .filter(r -> r.getEspacio().getId().equals(espacio.getId()))
                .toList();
        long minutos = suyas.stream()
                .mapToLong(r -> Duration.between(r.getHoraInicio(), r.getHoraFin()).toMinutes())
                .sum();
        int ingresos = suyas.stream().mapToInt(Reserva::getValorTotal).sum();
        return new OcupacionEspacio(espacio.getNombre(), minutos / 60.0, ingresos);
    }
}
