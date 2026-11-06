package com.devsenior.colmena.exception;

// 409: los datos son validos, pero el negocio no lo permite (ej: horario ya reservado)
public class ReglaNegocioException extends RuntimeException {
    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}
