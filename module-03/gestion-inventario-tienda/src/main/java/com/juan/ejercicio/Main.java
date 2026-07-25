package com.juan.ejercicio;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.juan.SistemaGeneral;
import com.juan.ejercicio.model.TiendaGeneral;
import com.juan.ejercicio.service.AlmacenServiceImpl;
import com.juan.ejercicio.service.TiendaGeneralServiceImpl;



public class Main {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);

       AlmacenServiceImpl almacenService = new AlmacenServiceImpl();

        TiendaGeneral tienda = new TiendaGeneral();

        TiendaGeneralServiceImpl tiendaGeneralService = new TiendaGeneralServiceImpl(tienda, almacenService);

        SistemaGeneral sistemaGeneral = new SistemaGeneral(almacenService, tiendaGeneralService);

        
        
        sistemaGeneral.cargarDatosDemo();
        boolean salir = false;

        
        

        while (!salir) {
            
            System.out.print("""
                --- MENU INVENTARIO GENERAL TIENDA---
                1. Registrar producto (catálogo general)
                2. Registrar almacén
                3. Asignar producto existente a un almacén
                4. Agregar stock a producto en un almacén
                5. Buscar producto por ID (catálogo)
                6. Buscar almacén por ID
                7. Mostrar todos los almacenes y su stock
                0. Salir
                Elige una opción:  """);
            
            try {
            int opcion = sc.nextInt();
            sc.nextLine();


            switch (opcion) {
                case 1 -> sistemaGeneral.registrarProducto(sc);
                case 2 -> sistemaGeneral.registrarAlmacen(sc);
                case 3 -> sistemaGeneral.asignarProductoAlmacen(sc);
                case 4 -> sistemaGeneral.agregarStock(sc);
                case 5 -> sistemaGeneral.buscarProducto(sc);
                case 6 -> sistemaGeneral.buscarAlmacen(sc);
                case 7 -> sistemaGeneral.mostrarAlmacenes();
                case 0 -> salir = true;
                default -> System.out.println("Opción inválida");
            }
        
       
            
        } catch (InputMismatchException e) {
             System.out.println("Error: Ingresa un valor númerico dentro del rango para escoger que accion realizar");
             sc.nextLine();
        }
    }
        System.out.println("Saliste del sistema.");
        sc.close();

    }

}
