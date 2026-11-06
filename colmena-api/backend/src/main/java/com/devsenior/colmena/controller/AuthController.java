package com.devsenior.colmena.controller;

import com.devsenior.colmena.dto.AuthResponse;
import com.devsenior.colmena.dto.LoginRequest;
import com.devsenior.colmena.dto.RegistroRequest;
import com.devsenior.colmena.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Autenticación", description = "Login y registro público: no piden token")
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Iniciar sesión", description = "Responde un JWT con nombre, rol y permisos.")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // Registro publico: cualquiera puede volverse miembro (USER). Devuelve el token de una vez.
    @Operation(summary = "Crear una cuenta de miembro", description = "Siempre crea un USER: el contrato no tiene campo rol.")
    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registro(@Valid @RequestBody RegistroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registrar(request));
    }
}