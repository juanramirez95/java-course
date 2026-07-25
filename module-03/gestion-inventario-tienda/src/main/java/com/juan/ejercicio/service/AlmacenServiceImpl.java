package com.juan.ejercicio.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.juan.ejercicio.exceptions.checked.CapacidadListaLlenaException;
import com.juan.ejercicio.exceptions.uncheked.IdAlreadyExistsException;
import com.juan.ejercicio.exceptions.uncheked.InvalidDataException;
import com.juan.ejercicio.exceptions.uncheked.ProductoNotFoundException;
import com.juan.ejercicio.model.Producto;

public class AlmacenServiceImpl implements IAlmacenService {

    private static final Logger logger = LoggerFactory.getLogger(AlmacenServiceImpl.class);

    private static final int MAX_PRODUCTOS = 10;
    private final Map<String,Producto> productos = new HashMap<>();

    public Producto addProducto(String productoId, String nombre, double precio, int cantidad) throws CapacidadListaLlenaException{
        if (productoId==null||productoId.trim().isEmpty()||
            nombre==null||nombre.trim().isEmpty()|| precio < 0 || cantidad<0) {
            logger.warn("Intento de registrar producto con datos inválidos: id={}, nombre={}, precio={}, cantidad={}",
                    productoId, nombre, precio, cantidad);
            throw new InvalidDataException("Los datos de ingresados estan vacios, incorrectos o ingesaste valores negativos");
        }

        if (productos.size()>=MAX_PRODUCTOS) {
            logger.warn("Catálogo lleno ({} productos). No se pudo registrar id={}", MAX_PRODUCTOS, productoId);
            throw new CapacidadListaLlenaException("No se pueden agregar más productos al Inventario, Inventario LLeno");
        }

        Producto producto = new Producto(productoId, nombre, precio, cantidad);

        if (productos.containsKey(producto.getProductoId())) {
            logger.warn("Intento de registrar producto duplicado: id={}", productoId);
            throw new IdAlreadyExistsException("ya existe un producto con este ID: " + producto.getProductoId());
        }

        productos.put(productoId, producto);
        logger.info("Producto registrado en catálogo: id={}, nombre={}, precio={}, cantidad={}",
                productoId, nombre, precio, cantidad);

        return producto;
    }

    public Producto findProducto(String productoId){
        if (productoId==null||productoId.trim().isEmpty()) {
            logger.warn("Intento de buscar producto con id inválido: id={}", productoId);
            throw new InvalidDataException("Los datos de ingresados estan vacios");
        }
        Producto producto = productos.get(productoId);

        if (producto==null) {
            logger.warn("No existe un producto con id={}", productoId);
            throw new ProductoNotFoundException("No existe un producto con id: " + productoId);
        }
        logger.info("Producto encontrado en el catálogo: id={}, producto={}", productoId, producto);
        return producto;
    }

    public void mostrarTodosProductos(){
        for (Producto producto : productos.values()) {
            System.out.println(producto);
        }
    }

}