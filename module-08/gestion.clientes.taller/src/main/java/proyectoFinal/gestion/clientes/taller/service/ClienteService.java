package proyectoFinal.gestion.clientes.taller.service;

import java.util.List;

import proyectoFinal.gestion.clientes.taller.dto.ClienteRequestDTO;
import proyectoFinal.gestion.clientes.taller.dto.ClienteResponseDTO;

public interface ClienteService {
     ClienteResponseDTO crear(ClienteRequestDTO request);

    List<ClienteResponseDTO> listar();

    ClienteResponseDTO buscarPorId(Long id);

    ClienteResponseDTO  actualizar(Long id, ClienteRequestDTO request);

    void eliminar(Long id);
}
