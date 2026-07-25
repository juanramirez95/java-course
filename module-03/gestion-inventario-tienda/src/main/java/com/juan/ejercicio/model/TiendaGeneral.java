package com.juan.ejercicio.model;



import java.util.HashMap;
import java.util.Map;

public class TiendaGeneral {
    private Map<String,Almacen>almacenes;

    public TiendaGeneral() {
        this.almacenes = new HashMap<>();
    }

    public Map<String, Almacen> getAlmacenes() {
        return new HashMap<>(almacenes);
    }

    public void agregarAlmacen(String id, Almacen almacen){
        almacenes.put(id, almacen);
    }

    public boolean existeAlmacen(String id){
        return almacenes.containsKey(id);
    }
    
    public Almacen buscarAlmacen(String id){
        return almacenes.get(id);
    }

    public int totalAlmacenes(){
        return almacenes.size();
    }

    






}
