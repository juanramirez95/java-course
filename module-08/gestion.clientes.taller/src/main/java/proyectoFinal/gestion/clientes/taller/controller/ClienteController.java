package proyectoFinal.gestion.clientes.taller.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proyectoFinal.gestion.clientes.taller.dto.ClienteRequestDTO;
import proyectoFinal.gestion.clientes.taller.dto.ClienteResponseDTO;
import proyectoFinal.gestion.clientes.taller.service.ClienteService;

@Validated
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	public ResponseEntity<ClienteResponseDTO> crear(@Valid @RequestBody ClienteRequestDTO request) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(clienteService.crear(request));
	}

	@GetMapping
	public ResponseEntity<List<ClienteResponseDTO>> listar() {
		return ResponseEntity.ok(clienteService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.buscarPorId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponseDTO> actualizar(
			@PathVariable Long id,
			@Valid @RequestBody ClienteRequestDTO request) {
		return ResponseEntity.ok(clienteService.actualizar(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		clienteService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
