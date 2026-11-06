package com.devsenior.colmena.exception;

// 400: los datos no tienen sentido (ej: la hora de fin es antes de la de inicio)
public class DatoInvalidoException extends RuntimeException {
    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
