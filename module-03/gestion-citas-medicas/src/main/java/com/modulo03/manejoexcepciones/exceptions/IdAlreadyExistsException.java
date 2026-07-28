package com.modulo03.manejoexcepciones.exceptions;

/**
 * IdAlreadyExistsException
 */
public class IdAlreadyExistsException extends RuntimeException {

    public IdAlreadyExistsException(String message) {
        super(message);
    }

    public IdAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
