package com.devsenior.colmena.dto;

import java.time.LocalDateTime;

// Formato unico de error: { codigo, mensaje, fecha }
public record ErrorResponse(int codigo, String mensaje, LocalDateTime fecha) {
}
