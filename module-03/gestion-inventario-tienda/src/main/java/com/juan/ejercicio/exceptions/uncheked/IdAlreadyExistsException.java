package com.juan.ejercicio.exceptions.uncheked;

public class IdAlreadyExistsException extends RuntimeException{

    public IdAlreadyExistsException(String message) {
        super(message);
    }

    public IdAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
