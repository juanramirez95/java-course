package proyectoFinal.gestion.clientes.taller.service;

import java.util.List;

import proyectoFinal.gestion.clientes.taller.dto.OrdenServicioRequestDTO;
import proyectoFinal.gestion.clientes.taller.dto.OrdenServicioResponseDTO;
import proyectoFinal.gestion.clientes.taller.model.enums.EstadoOrden;

public interface OrdenServicioService {

    OrdenServicioResponseDTO crear(OrdenServicioRequestDTO request);

    List<OrdenServicioResponseDTO> listar();

    OrdenServicioResponseDTO buscarPorId(Long id);

    OrdenServicioResponseDTO cambiarEstado(Long id, EstadoOrden nuevoEstado);

    List<OrdenServicioResponseDTO> listarPorEstado(EstadoOrden estado);
}