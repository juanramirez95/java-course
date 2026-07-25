package com.juan.ejercicio.service;

import com.juan.ejercicio.exceptions.checked.CapacidadListaLlenaException;
import com.juan.ejercicio.model.Producto;

public interface IAlmacenService {
    Producto addProducto(String productoId,String nombre, double precio, int cantidad) throws CapacidadListaLlenaException;
    // Producto actualizarCantidadProducto(String productoId, int cantidad);
    Producto findProducto(String productoId);
    void mostrarTodosProductos();
}

