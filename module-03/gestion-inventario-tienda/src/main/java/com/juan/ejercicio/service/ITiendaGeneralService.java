package com.juan.ejercicio.service;

import com.juan.ejercicio.exceptions.checked.CapacidadListaLlenaException;
import com.juan.ejercicio.model.Almacen;
import com.juan.ejercicio.model.Producto;

public interface ITiendaGeneralService {

    Almacen addAlmacen(String almacenId, String nombreAlmacen,String ubicacion) throws CapacidadListaLlenaException;
    Almacen findAlmacen(String almacenId);
    void agregarProductoAlmacen(String almacenId,String productoId);
    Producto actualizarCantidadProducto(String almacenId,String productoId,int cantidad);
    void mostrarAlmacenes();
    void mostrarAlmacenesSedes();
}
