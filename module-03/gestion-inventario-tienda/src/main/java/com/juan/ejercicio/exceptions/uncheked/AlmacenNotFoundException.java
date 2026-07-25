package com.juan.ejercicio.exceptions.uncheked;

/**
 * AlmacenNotFoundException
 */
public class AlmacenNotFoundException extends RuntimeException{

    public AlmacenNotFoundException(String message) {
        super(message);
    }

    public AlmacenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
