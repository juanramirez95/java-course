package com.devsenior.colmena.dto;

import com.devsenior.colmena.model.Rol;

import java.time.LocalDateTime;

// Nunca expone la contraseña
public record UsuarioResponse(
        Long id,
        String nombre,
        String email,
        Rol rol,
        boolean activo,
        LocalDateTime creadoEn
) {
}
