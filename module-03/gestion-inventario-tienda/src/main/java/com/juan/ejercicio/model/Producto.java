package com.juan.ejercicio.model;

public class Producto {

    private String productoId;
    private String nombre;
    private double precio;
    private int cantidad;
    
    // Constructors
    public Producto() {
    }

    public Producto(String productoId, String nombre, double precio, int cantidad) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad=cantidad;
        
    }

    


    public Producto(Producto otro) {
        this.productoId =   otro. productoId;
        this.nombre = otro.nombre;
        this.precio = otro.precio;
        this.cantidad = 0;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

     public int getCantidad() {
        return cantidad;
    }

    public int setCantidad(int cantidad) {
      return  this.cantidad = cantidad;
    }

    

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productoId == null) ? 0 : productoId.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        long temp;
        temp = Double.doubleToLongBits(precio);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + cantidad;
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
        Producto other = (Producto) obj;
        if (productoId == null) {
            if (other.productoId != null)
                return false;
        } else if (!productoId.equals(other.productoId))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
            return false;
        if (cantidad != other.cantidad)
            return false;
        return true;
    }

    @Override
    public String toString() {
return "ID: " + productoId + "\nNombre: " + nombre + "\nPrecio: $" + precio + "\nCantidad: "+ cantidad +".\n" ;
    }

   

    
   


    
}
