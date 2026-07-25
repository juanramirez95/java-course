package com.juan.ejercicio.exceptions.checked;

public class CapacidadListaLlenaException extends Exception{

    public CapacidadListaLlenaException(String message) {
        super(message);
    }

    public CapacidadListaLlenaException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
