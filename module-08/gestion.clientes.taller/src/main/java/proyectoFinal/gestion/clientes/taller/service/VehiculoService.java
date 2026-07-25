package proyectoFinal.gestion.clientes.taller.service;

import java.util.List;

import proyectoFinal.gestion.clientes.taller.dto.VehiculoRequestDTO;
import proyectoFinal.gestion.clientes.taller.dto.VehiculoResponseDTO;

public interface VehiculoService {

    VehiculoResponseDTO crear(VehiculoRequestDTO request);

    List<VehiculoResponseDTO> listar();

    VehiculoResponseDTO buscarPorId(Long id);

    List<VehiculoResponseDTO> listarPorCliente(Long clienteId);

    void eliminar(Long id);
}