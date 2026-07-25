package proyectoFinal.gestion.clientes.taller.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import proyectoFinal.gestion.clientes.taller.dto.OrdenServicioRequestDTO;
import proyectoFinal.gestion.clientes.taller.dto.OrdenServicioResponseDTO;
import proyectoFinal.gestion.clientes.taller.model.OrdenServicio;
import proyectoFinal.gestion.clientes.taller.model.Vehiculo;
import proyectoFinal.gestion.clientes.taller.model.enums.EstadoOrden;
import proyectoFinal.gestion.clientes.taller.repository.OrdenServicioRepository;
import proyectoFinal.gestion.clientes.taller.repository.VehiculoRepository;

@Service
public class OrdenServicioServiceImpl implements OrdenServicioService {

    private final OrdenServicioRepository ordenServicioRepository;
    private final VehiculoRepository vehiculoRepository;

    public OrdenServicioServiceImpl(
            OrdenServicioRepository ordenServicioRepository,
            VehiculoRepository vehiculoRepository) {
        this.ordenServicioRepository = ordenServicioRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public OrdenServicioResponseDTO crear(OrdenServicioRequestDTO request) {
        OrdenServicio orden = new OrdenServicio();
        actualizarDatos(orden, request);
        return toResponse(ordenServicioRepository.save(orden));
    }

    @Override
    public List<OrdenServicioResponseDTO> listar() {
        return ordenServicioRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrdenServicioResponseDTO buscarPorId(Long id) {
        return toResponse(obtenerOrden(id));
    }

    @Override
    public OrdenServicioResponseDTO cambiarEstado(Long id, EstadoOrden nuevoEstado) {
        OrdenServicio orden = obtenerOrden(id);
        orden.setEstado(nuevoEstado);
        return toResponse(ordenServicioRepository.save(orden));
    }

    @Override
    public List<OrdenServicioResponseDTO> listarPorEstado(EstadoOrden estado) {
        return ordenServicioRepository.findByEstado(estado).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private OrdenServicio obtenerOrden(Long id) {
        return ordenServicioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No existe una orden de servicio con el id indicado"));
    }

    private void actualizarDatos(OrdenServicio orden, OrdenServicioRequestDTO request) {
        Vehiculo vehiculo = vehiculoRepository.findById(request.vehiculoId())
                .orElseThrow(() -> new NoSuchElementException("No existe un vehiculo con el id indicado"));

        orden.setDescripcionTrabajo(request.descripcionTrabajo());
        orden.setFechaEntrada(request.fechaEntrada());
        orden.setFechaSalida(request.fechaSalida());
        orden.setEstado(request.estado());
        orden.setCostoTotal(request.costoTotal());
        orden.setVehiculo(vehiculo);
    }

    private OrdenServicioResponseDTO toResponse(OrdenServicio orden) {
        return new OrdenServicioResponseDTO(
                orden.getId(),
                orden.getDescripcionTrabajo(),
                orden.getFechaEntrada(),
                orden.getFechaSalida(),
                orden.getEstado(),
                orden.getCostoTotal(),
                orden.getVehiculo().getId());
    }
}