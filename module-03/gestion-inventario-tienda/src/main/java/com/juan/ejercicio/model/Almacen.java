package com.juan.ejercicio.model;


import java.util.HashMap;
import java.util.Map;

public class Almacen {

    private String almacenId;
    private String nombreAlmacen;
    private String ubicacion;
    private Map<String,Producto> productos;
    // private Integer productosCount;
    
    // Constructors
    public Almacen() {
    }

    public Almacen(String almacenId,String nombreAlmacen, String ubicacion) {
        this.almacenId = almacenId;
        this.nombreAlmacen= nombreAlmacen;
        this.ubicacion = ubicacion;
        this.productos = new HashMap<>();
        // this.productosCount = 0;
    }

    public String getAlmacenId() {
        return almacenId;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Map<String, Producto> getProductos() { // Devuelve una copia inmutable, de esa manera se mantiene el encapsulamiento
        return new HashMap<>(productos);
    }

    
    public void agregarProducto(String id, Producto producto){
        productos.put(id, producto);
    }

    public boolean existeProducto(String id){
        return productos.containsKey(id);
    }
    public Producto buscaProducto(String id){
        return productos.get(id);
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((almacenId == null) ? 0 : almacenId.hashCode());
        result = prime * result + ((nombreAlmacen == null) ? 0 : nombreAlmacen.hashCode());
        result = prime * result + ((ubicacion == null) ? 0 : ubicacion.hashCode());
        result = prime * result + ((productos == null) ? 0 : productos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Almacen other = (Almacen) obj;
        if (almacenId == null) {
            if (other.almacenId != null)
                return false;
        } else if (!almacenId.equals(other.almacenId))
            return false;
        if (nombreAlmacen == null) {
            if (other.nombreAlmacen != null)
                return false;
        } else if (!nombreAlmacen.equals(other.nombreAlmacen))
            return false;
        if (ubicacion == null) {
            if (other.ubicacion != null)
                return false;
        } else if (!ubicacion.equals(other.ubicacion))
            return false;
        if (productos == null) {
            if (other.productos != null)
                return false;
        } else if (!productos.equals(other.productos))
            return false;
        return true;
    }

    @Override
    public String toString() {
    return "ID Almacen=" + almacenId + "\nNombre del Almacen=" + nombreAlmacen + "\nDirección del Almacen=" + ubicacion+".\n";
    }

    
  

  

   

   
    

    


}
