package proyectoFinal.gestion.clientes.taller.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proyectoFinal.gestion.clientes.taller.dto.VehiculoRequestDTO;
import proyectoFinal.gestion.clientes.taller.dto.VehiculoResponseDTO;
import proyectoFinal.gestion.clientes.taller.service.VehiculoService;

@Validated
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

	private final VehiculoService vehiculoService;

	public VehiculoController(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}

	@PostMapping
	public ResponseEntity<VehiculoResponseDTO> crear(@Valid @RequestBody VehiculoRequestDTO request) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(vehiculoService.crear(request));
	}

	@GetMapping
	public ResponseEntity<List<VehiculoResponseDTO>> listar() {
		return ResponseEntity.ok(vehiculoService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<VehiculoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(vehiculoService.buscarPorId(id));
	}

	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<List<VehiculoResponseDTO>> listarPorCliente(@PathVariable Long clienteId) {
		return ResponseEntity.ok(vehiculoService.listarPorCliente(clienteId));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		vehiculoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
