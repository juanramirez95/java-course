package com.devsenior.colmena.service.impl;

import com.devsenior.colmena.dto.EspacioRequest;
import com.devsenior.colmena.dto.EspacioResponse;
import com.devsenior.colmena.dto.FranjaOcupada;
import com.devsenior.colmena.exception.RecursoNoEncontradoException;
import com.devsenior.colmena.exception.ReglaNegocioException;
import com.devsenior.colmena.model.Espacio;
import com.devsenior.colmena.model.EstadoReserva;
import com.devsenior.colmena.model.Permiso;
import com.devsenior.colmena.repository.EspacioRepository;
import com.devsenior.colmena.repository.ReservaRepository;
import com.devsenior.colmena.service.EspacioService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EspacioServiceImpl implements EspacioService {

    private final EspacioRepository espacioRepository;
    private final ReservaRepository reservaRepository;

    public EspacioServiceImpl(EspacioRepository espacioRepository, ReservaRepository reservaRepository) {
        this.espacioRepository = espacioRepository;
        this.reservaRepository = reservaRepository;
    }

    // Mismo endpoint, distinta respuesta segun el permiso:
    // quien gestiona ve todos (activos e inactivos); un miembro solo los que puede reservar
    @Override
    public List<EspacioResponse> listar(Authentication autenticacion) {
        List<Espacio> espacios = Permiso.ESPACIOS_GESTIONAR.estaEn(autenticacion)
                ? espacioRepository.findAllByOrderByNombreAsc()
                : espacioRepository.findByActivoTrueOrderByNombreAsc();

        return espacios.stream().map(this::toResponse).toList();
    }

    @Override
    public EspacioResponse buscarPorId(Long id, Authentication autenticacion) {
        Espacio espacio = buscarEntidad(id);

        // Para un miembro, un espacio inactivo "no existe"
        if (!espacio.isActivo() && !Permiso.ESPACIOS_GESTIONAR.estaEn(autenticacion)) {
            throw new RecursoNoEncontradoException("No existe un espacio con id " + id);
        }
        return toResponse(espacio);
    }

    @Override
    public EspacioResponse crear(EspacioRequest request) {
        if (espacioRepository.existsByNombreIgnoreCase(request.nombre().trim())) {
            throw new ReglaNegocioException("Ya existe un espacio llamado " + request.nombre().trim());
        }
        Espacio espacio = new Espacio();
        aplicarDatos(espacio, request);
        return toResponse(espacioRepository.save(espacio));
    }

    @Override
    public EspacioResponse actualizar(Long id, EspacioRequest request) {
        Espacio espacio = buscarEntidad(id);
        if (espacioRepository.existsByNombreIgnoreCaseAndIdNot(request.nombre().trim(), id)) {
            throw new ReglaNegocioException("Ya existe otro espacio llamado " + request.nombre().trim());
        }
        aplicarDatos(espacio, request);
        return toResponse(espacioRepository.save(espacio));
    }

    @Override
    public EspacioResponse cambiarEstado(Long id, boolean activo) {
        Espacio espacio = buscarEntidad(id);
        espacio.setActivo(activo);
        return toResponse(espacioRepository.save(espacio));
    }

    // Franjas ocupadas de un dia: sirve para pintar la disponibilidad
    @Override
    public List<FranjaOcupada> ocupacion(Long id, LocalDate fecha) {
        buscarEntidad(id);
        return reservaRepository
                .findByEspacioIdAndFechaAndEstadoOrderByHoraInicioAsc(id, fecha, EstadoReserva.CONFIRMADA)
                .stream()
                .map(reserva -> new FranjaOcupada(reserva.getHoraInicio(), reserva.getHoraFin()))
                .toList();
    }

    private Espacio buscarEntidad(Long id) {
        return espacioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un espacio con id " + id));
    }

    private void aplicarDatos(Espacio espacio, EspacioRequest request) {
        espacio.setNombre(request.nombre().trim());
        espacio.setTipo(request.tipo());
        espacio.setCapacidad(request.capacidad());
        espacio.setPrecioHora(request.precioHora());
        espacio.setDescripcion(request.descripcion());
        espacio.setImagenUrl(request.imagenUrl());
    }

    private EspacioResponse toResponse(Espacio espacio) {
        return new EspacioResponse(
                espacio.getId(),
                espacio.getNombre(),
                espacio.getTipo(),
                espacio.getCapacidad(),
                espacio.getPrecioHora(),
                espacio.getDescripcion(),
                espacio.getImagenUrl(),
                espacio.isActivo()
        );
    }
}
