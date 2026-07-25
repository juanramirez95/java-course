package proyectoFinal.gestion.clientes.taller.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import proyectoFinal.gestion.clientes.taller.dto.VehiculoRequestDTO;
import proyectoFinal.gestion.clientes.taller.dto.VehiculoResponseDTO;
import proyectoFinal.gestion.clientes.taller.model.Cliente;
import proyectoFinal.gestion.clientes.taller.model.Vehiculo;
import proyectoFinal.gestion.clientes.taller.repository.ClienteRepository;
import proyectoFinal.gestion.clientes.taller.repository.VehiculoRepository;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final ClienteRepository clienteRepository;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, ClienteRepository clienteRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public VehiculoResponseDTO crear(VehiculoRequestDTO request) {
        if (vehiculoRepository.existsByPlaca(request.placa())) {
            throw new IllegalArgumentException("Ya existe un vehiculo con la placa indicada");
        }

        Vehiculo vehiculo = new Vehiculo();
        actualizarDatos(vehiculo, request);
        return toResponse(vehiculoRepository.save(vehiculo));
    }

    @Override
    public List<VehiculoResponseDTO> listar() {
        return vehiculoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VehiculoResponseDTO buscarPorId(Long id) {
        return toResponse(obtenerVehiculo(id));
    }

    @Override
    public List<VehiculoResponseDTO> listarPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new NoSuchElementException("No existe un cliente con el id indicado");
        }

        return vehiculoRepository.findByClienteId(clienteId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new NoSuchElementException("No existe un vehiculo con el id indicado");
        }
        vehiculoRepository.deleteById(id);
    }

    private Vehiculo obtenerVehiculo(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No existe un vehiculo con el id indicado"));
    }

    private void actualizarDatos(Vehiculo vehiculo, VehiculoRequestDTO request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new NoSuchElementException("No existe un cliente con el id indicado"));

        vehiculo.setPlaca(request.placa());
        vehiculo.setMarca(request.marca());
        vehiculo.setModelo(request.modelo());
        vehiculo.setAnio(request.anio());
        vehiculo.setCliente(cliente);
    }

    private VehiculoResponseDTO toResponse(Vehiculo vehiculo) {
        return new VehiculoResponseDTO(
                vehiculo.getId(),
                vehiculo.getPlaca(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getAnio(),
                vehiculo.getCliente().getId());
    }
}