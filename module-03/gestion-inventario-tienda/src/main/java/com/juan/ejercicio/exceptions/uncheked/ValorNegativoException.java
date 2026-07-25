package com.juan.ejercicio.exceptions.uncheked;

public class ValorNegativoException extends RuntimeException {

    public ValorNegativoException(String message) {
        super(message);
    }

    public ValorNegativoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
