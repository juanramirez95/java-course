package com.devsenior.colmena.controller;

import com.devsenior.colmena.dto.EspacioRequest;
import com.devsenior.colmena.dto.EspacioResponse;
import com.devsenior.colmena.dto.EstadoRequest;
import com.devsenior.colmena.dto.FranjaOcupada;
import com.devsenior.colmena.service.EspacioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Espacios", description = "Catálogo, ocupación y gestión de espacios")
@RequestMapping("/api/espacios")
public class EspacioController {

    private final EspacioService espacioService;

    public EspacioController(EspacioService espacioService) {
        this.espacioService = espacioService;
    }

    @PreAuthorize("hasAuthority('ESPACIOS_VER')")
    @Operation(summary = "Listar espacios", description = "La administración ve todos; un miembro, solo los activos.")
    @GetMapping
    public ResponseEntity<List<EspacioResponse>> listar(Authentication autenticacion) {
        return ResponseEntity.ok(espacioService.listar(autenticacion));
    }

    @PreAuthorize("hasAuthority('ESPACIOS_VER')")
    @Operation(summary = "Ver un espacio")
    @GetMapping("/{id}")
    public ResponseEntity<EspacioResponse> buscarPorId(@PathVariable Long id, Authentication autenticacion) {
        return ResponseEntity.ok(espacioService.buscarPorId(id, autenticacion));
    }

    // Horarios ocupados de un espacio en una fecha: /api/espacios/3/ocupacion?fecha=2026-10-20
    @PreAuthorize("hasAuthority('ESPACIOS_VER')")
    @Operation(summary = "Horarios ocupados de un día", description = "Las franjas reservadas de ese espacio en ?fecha=…")
    @GetMapping("/{id}/ocupacion")
    public ResponseEntity<List<FranjaOcupada>> ocupacion(@PathVariable Long id, @RequestParam LocalDate fecha) {
        return ResponseEntity.ok(espacioService.ocupacion(id, fecha));
    }

    @PreAuthorize("hasAuthority('ESPACIOS_GESTIONAR')")
    @Operation(summary = "Crear un espacio", description = "Entra activo. 409 si el nombre ya existe.")
    @PostMapping
    public ResponseEntity<EspacioResponse> crear(@Valid @RequestBody EspacioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(espacioService.crear(request));
    }

    @PreAuthorize("hasAuthority('ESPACIOS_GESTIONAR')")
    @Operation(summary = "Editar un espacio")
    @PutMapping("/{id}")
    public ResponseEntity<EspacioResponse> actualizar(@PathVariable Long id,
                                                      @Valid @RequestBody EspacioRequest request) {
        return ResponseEntity.ok(espacioService.actualizar(id, request));
    }

    // Activar o desactivar: { "activo": false }
    @PreAuthorize("hasAuthority('ESPACIOS_GESTIONAR')")
    @Operation(summary = "Activar o desactivar un espacio")
    @PatchMapping("/{id}/estado")
    public ResponseEntity<EspacioResponse> cambiarEstado(@PathVariable Long id,
                                                         @Valid @RequestBody EstadoRequest request) {
        return ResponseEntity.ok(espacioService.cambiarEstado(id, request.activo()));
    }
}