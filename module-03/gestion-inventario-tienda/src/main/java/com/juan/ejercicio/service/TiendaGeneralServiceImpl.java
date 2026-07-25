package com.juan.ejercicio.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.juan.ejercicio.exceptions.checked.CapacidadListaLlenaException;
import com.juan.ejercicio.exceptions.uncheked.AlmacenNotFoundException;
import com.juan.ejercicio.exceptions.uncheked.IdAlreadyExistsException;
import com.juan.ejercicio.exceptions.uncheked.InvalidDataException;
import com.juan.ejercicio.exceptions.uncheked.ProductoNotFoundException;
import com.juan.ejercicio.model.Almacen;
import com.juan.ejercicio.model.Producto;
import com.juan.ejercicio.model.TiendaGeneral;


public class TiendaGeneralServiceImpl implements ITiendaGeneralService {

    private static final Logger logger = LoggerFactory.getLogger(TiendaGeneralServiceImpl.class);

    private static final int MAX_ALMACENES = 3;
    private final TiendaGeneral tienda;
    private final IAlmacenService almacenService;

    public TiendaGeneralServiceImpl(TiendaGeneral tienda, IAlmacenService almacenService) {
        this.tienda = tienda;
        this.almacenService = almacenService;
    }

    public Almacen addAlmacen(String almacenId, String nombreAlmacen, String ubicacion) throws CapacidadListaLlenaException {

        if (almacenId==null||almacenId.toLowerCase().trim().isEmpty()||nombreAlmacen==null||nombreAlmacen.toLowerCase().trim().isEmpty()||ubicacion==null||ubicacion.toLowerCase().trim().isEmpty()) {
            logger.warn("Intento de registrar almacén con datos inválidos: almacenId={}, nombreAlmacen={}, ubicacion={}",
                    almacenId, nombreAlmacen, ubicacion);
            throw new InvalidDataException("Los datos de ingresados estan vacios, incorrectos");
        }
        if (tienda.totalAlmacenes()>=MAX_ALMACENES) {
            logger.warn("Capacidad de almacenes llena ({} almacenes). No se pudo registrar almacenId={}", MAX_ALMACENES, almacenId);
            throw new CapacidadListaLlenaException("No se pueden agregar más productos al Inventario, Inventario LLeno");
        }

        Almacen almacen = new Almacen(almacenId, nombreAlmacen, ubicacion);

        if (tienda.existeAlmacen(almacenId)) {
            logger.warn("Intento de registrar un almacén con un Id existente: almacenId={}", almacenId);
            throw new IdAlreadyExistsException("ya existe un almacen con este ID: " + almacen.getAlmacenId());
        }
        tienda.agregarAlmacen(almacenId, almacen);
        logger.info("Almacén registrado: almacenId={}, nombre={}, ubicacion={}", almacenId, nombreAlmacen, ubicacion);

        return almacen;
    }

    public Almacen findAlmacen(String almacenId){
        if (almacenId==null||almacenId.trim().isEmpty()) {
            logger.warn("Intento de buscar almacén con id inválido: almacenId={}", almacenId);
            throw new InvalidDataException("Los datos de ingresados estan vacios");
        }

        Almacen almacen = tienda.buscarAlmacen(almacenId);

        if (almacen == null) {
            logger.warn("No existe un almacén con id={}", almacenId);
            throw new AlmacenNotFoundException("No existe un Almacen con id: " + almacenId);
        }

        return almacen;
    }

    public void agregarProductoAlmacen(String almacenId, String productoId){
        if (almacenId==null||almacenId.trim().isEmpty()||productoId==null||productoId.toLowerCase().trim().isEmpty()) {
            logger.warn("Intento de asignar producto con datos inválidos: almacenId={}, productoId={}", almacenId, productoId);
            throw new InvalidDataException("Los datos de ingresados estan vacios");
        }
        Almacen almacen = findAlmacen(almacenId);

        if (almacen.existeProducto(productoId)) {
            logger.warn("El producto con Id={} ya está registrado en el almacén={}", productoId, almacenId);
            throw new IdAlreadyExistsException("El producto con ID " + productoId +
                " ya está en el almacén " + almacen.getNombreAlmacen());
        }

        Producto productoCatalogo = almacenService.findProducto(productoId);
        Producto productoAlmacen = new Producto(productoCatalogo);
        almacen.agregarProducto(productoId, productoAlmacen);
        logger.info("Producto agregado con éxito al almacén: productoId={}, almacenId={}", productoId, almacenId);
    }

    public Producto actualizarCantidadProducto(String almacenId, String productoId, int cantidad){
        if (almacenId==null||almacenId.trim().isEmpty()||productoId==null||productoId.toLowerCase().trim().isEmpty()||cantidad<0) {
            logger.warn("Intento de actualizar cantidad con datos inválidos: almacenId={}, productoId={}, cantidad={}",
                    almacenId, productoId, cantidad);
            throw new InvalidDataException("Los datos de ingresados estan vacios");
        }

        Almacen almacen = findAlmacen(almacenId);

        if (!almacen.existeProducto(productoId)) {
            logger.warn("No existe el producto con Id={} en el almacén={}", productoId, almacenId);
            throw new ProductoNotFoundException("No existe un producto con este ID: "+ productoId);
        }

        Producto producto = almacen.buscaProducto(productoId);
        producto.setCantidad(cantidad);
        logger.info("Cantidad actualizada: almacenId={}, productoId={}, nuevaCantidad={}", almacenId, productoId, cantidad);

        return producto;
    }

// ------------------------------------------------------------------------------------------------------------------

    public void mostrarAlmacenes(){
        for (Almacen almacen : tienda.getAlmacenes().values()) {
            System.out.println(almacen);
            for (Producto producto: almacen.getProductos().values()) {
              System.out.println(producto);
            }
        }
    }

    public void mostrarAlmacenesSedes(){
        for (Almacen almacen : tienda.getAlmacenes().values()) {
            System.out.println(almacen);
        }
    }

}