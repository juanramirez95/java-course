package com.modulo03.manejoexcepciones.exceptions;

public class CapacidadListaLlenaException extends RuntimeException {

    public CapacidadListaLlenaException(String message) {
        super(message);
    }

    public CapacidadListaLlenaException(String message, Throwable cause) {
        super(message, cause);
    }

}
