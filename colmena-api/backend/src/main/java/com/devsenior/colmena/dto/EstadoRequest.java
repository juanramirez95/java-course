package com.devsenior.colmena.dto;

import jakarta.validation.constraints.NotNull;

// Activar o desactivar un espacio o una cuenta: { "activo": false }
public record EstadoRequest(
        @NotNull(message = "Indica si queda activo (true) o inactivo (false)")
        Boolean activo
) {
}
