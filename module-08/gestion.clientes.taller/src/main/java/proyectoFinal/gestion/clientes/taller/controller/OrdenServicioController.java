package proyectoFinal.gestion.clientes.taller.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proyectoFinal.gestion.clientes.taller.dto.OrdenServicioRequestDTO;
import proyectoFinal.gestion.clientes.taller.dto.OrdenServicioResponseDTO;
import proyectoFinal.gestion.clientes.taller.model.enums.EstadoOrden;
import proyectoFinal.gestion.clientes.taller.service.OrdenServicioService;

@Validated
@RestController
@RequestMapping("/api/ordenes-servicio")
public class OrdenServicioController {

	private final OrdenServicioService ordenServicioService;

	public OrdenServicioController(OrdenServicioService ordenServicioService) {
		this.ordenServicioService = ordenServicioService;
	}

	@PostMapping
	public ResponseEntity<OrdenServicioResponseDTO> crear(
			@Valid @RequestBody OrdenServicioRequestDTO request) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(ordenServicioService.crear(request));
	}

	@GetMapping
	public ResponseEntity<List<OrdenServicioResponseDTO>> listar(
			@RequestParam(required = false) EstadoOrden estado) {
		if (estado == null) {
			return ResponseEntity.ok(ordenServicioService.listar());
		}
		return ResponseEntity.ok(ordenServicioService.listarPorEstado(estado));
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrdenServicioResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(ordenServicioService.buscarPorId(id));
	}

	@PatchMapping("/{id}/estado")
	public ResponseEntity<OrdenServicioResponseDTO> cambiarEstado(
			@PathVariable Long id,
			@RequestParam EstadoOrden estado) {
		return ResponseEntity.ok(ordenServicioService.cambiarEstado(id, estado));
	}
}
