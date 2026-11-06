package com.devsenior.colmena.controller;

import com.devsenior.colmena.dto.ResumenResponse;
import com.devsenior.colmena.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Reportes", description = "El panel de Mariana")
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @PreAuthorize("hasAuthority('REPORTES_VER')")
    @Operation(summary = "Resumen del negocio", description = "Reservas de hoy, ingresos del mes, ocupación por espacio y miembros activos.")
    @GetMapping("/resumen")
    public ResponseEntity<ResumenResponse> resumen() {
        return ResponseEntity.ok(reporteService.resumen());
    }
}