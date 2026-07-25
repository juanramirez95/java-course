package com.juan.ejercicio.exceptions.uncheked;



public class SoloNumerosException extends RuntimeException{

    public SoloNumerosException(String message) {
        super(message);
    }

    public SoloNumerosException(String message, Throwable cause) {
        super(message, cause);
    }

    
    
}
