package com.juan.ejercicio.exceptions.uncheked;

public class ProductoNotFoundException extends RuntimeException{

    public ProductoNotFoundException(String arg0) {
        super(arg0);
    }

    public ProductoNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
