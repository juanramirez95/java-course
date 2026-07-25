package proyectoFinal.gestion.clientes.taller.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import proyectoFinal.gestion.clientes.taller.dto.ClienteRequestDTO;
import proyectoFinal.gestion.clientes.taller.dto.ClienteResponseDTO;
import proyectoFinal.gestion.clientes.taller.model.Cliente;
import proyectoFinal.gestion.clientes.taller.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteResponseDTO crear(ClienteRequestDTO request) {
        if (clienteRepository.existsByCedula(request.cedula())) {
            throw new IllegalArgumentException("Ya existe un cliente con la cedula indicada");
        }

        Cliente cliente = new Cliente();
        actualizarDatos(cliente, request);
        return toResponse(clienteRepository.save(cliente));
    }

    @Override
    public List<ClienteResponseDTO> listar() {
        return clienteRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO buscarPorId(Long id) {
        return toResponse(obtenerCliente(id));
    }

    @Override
    public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO request) {
        Cliente cliente = obtenerCliente(id);
        actualizarDatos(cliente, request);
        return toResponse(clienteRepository.save(cliente));
    }

    @Override
    public void eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new NoSuchElementException("No existe un cliente con el id indicado");
        }
        clienteRepository.deleteById(id);
    }

    private Cliente obtenerCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No existe un cliente con el id indicado"));
    }

    private void actualizarDatos(Cliente cliente, ClienteRequestDTO request) {
        cliente.setNombre(request.nombre());
        cliente.setCedula(request.cedula());
        cliente.setTelefono(request.telefono());
        cliente.setEmail(request.email());
    }

    private ClienteResponseDTO toResponse(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getCedula(),
                cliente.getTelefono(),
                cliente.getEmail());
    }

}