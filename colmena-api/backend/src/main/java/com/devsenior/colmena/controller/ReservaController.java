package com.devsenior.colmena.controller;

import com.devsenior.colmena.dto.ReservaRequest;
import com.devsenior.colmena.dto.ReservaResponse;
import com.devsenior.colmena.service.ReservaService;
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
@Tag(name = "Reservas", description = "Crear, consultar y cancelar reservas")
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // Todas las reservas (opcional: ?fecha=2026-10-20)
    @PreAuthorize("hasAuthority('RESERVAS_VER_TODAS')")
    @Operation(summary = "Todas las reservas", description = "Con ?fecha=2026-11-05 trae solo las de ese día. Es la Agenda de Mariana.")
    @GetMapping
    public ResponseEntity<List<ReservaResponse>> listarTodas(@RequestParam(required = false) LocalDate fecha) {
        return ResponseEntity.ok(reservaService.listarTodas(fecha));
    }

    // "Mis reservas": el email sale del token, no de la URL
    @PreAuthorize("hasAuthority('RESERVAS_VER_PROPIAS')")
    @Operation(summary = "Mis reservas", description = "Solo las de quien hace la petición: el email sale del token.")
    @GetMapping("/mias")
    public ResponseEntity<List<ReservaResponse>> listarMias(Authentication autenticacion) {
        return ResponseEntity.ok(reservaService.listarMias(autenticacion.getName()));
    }

    @PreAuthorize("hasAuthority('RESERVAS_CREAR')")
    @Operation(summary = "Crear una reserva", description = "El miembro sale del token y el valor lo calcula el backend. 409 si se cruza con otra.")
    @PostMapping
    public ResponseEntity<ReservaResponse> crear(@Valid @RequestBody ReservaRequest request,
                                                 Authentication autenticacion) {
        ReservaResponse creada = reservaService.crear(request, autenticacion.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    // Puede entrar quien tenga CUALQUIERA de los dos permisos;
    // el servicio verifica, ademas, que la reserva sea suya si solo tiene "propias"
    @PreAuthorize("hasAnyAuthority('RESERVAS_CANCELAR_PROPIAS', 'RESERVAS_CANCELAR_TODAS')")
    @Operation(summary = "Cancelar una reserva", description = "Un miembro cancela solo las suyas y antes de que empiecen; la administración, cualquiera.")
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ReservaResponse> cancelar(@PathVariable Long id, Authentication autenticacion) {
        return ResponseEntity.ok(reservaService.cancelar(id, autenticacion));
    }
}