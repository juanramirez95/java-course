package com.juan;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.juan.ejercicio.exceptions.checked.CapacidadListaLlenaException;
import com.juan.ejercicio.exceptions.uncheked.AlmacenNotFoundException;
import com.juan.ejercicio.exceptions.uncheked.IdAlreadyExistsException;
import com.juan.ejercicio.exceptions.uncheked.InvalidDataException;
import com.juan.ejercicio.exceptions.uncheked.ProductoNotFoundException;
import com.juan.ejercicio.exceptions.uncheked.ValorNegativoException;
import com.juan.ejercicio.service.IAlmacenService;
import com.juan.ejercicio.service.ITiendaGeneralService;

public class SistemaGeneral {

     Scanner sc = new Scanner(System.in);
        // AlmacenService almacenService;
        // TiendaGeneralService tienda;
        private static final Logger logg = LoggerFactory.getLogger(SistemaGeneral.class);
        private final IAlmacenService almacenService;
        private final ITiendaGeneralService tiendaGeneralService;


        public SistemaGeneral(IAlmacenService almacenService, ITiendaGeneralService tiendaGeneralService) {
            this.almacenService = almacenService;
            this.tiendaGeneralService = tiendaGeneralService;
        }

        public void registrarProducto(Scanner sc){ 
            System.out.println("---- REGISTRO DE PRODUCTOS----");
            try{
            System.out.print("Ingrese el ID del Producto: ");
            String productoId = sc.nextLine().toUpperCase();
            System.out.print("Ingrese el nombre del Producto: ");
            String nombre = sc.nextLine().toUpperCase();
            System.out.print("Ingrese el precio del Producto: ");
            double precio = sc.nextDouble();
            sc.nextLine();
            System.out.print("Ingrese la cantidad del Producto: ");
            int cantidad = sc.nextInt();
            sc.nextLine();

            almacenService.addProducto(productoId, nombre, precio, cantidad);
            System.out.println("Producto registrado exitosamente");
            }catch( InvalidDataException | IdAlreadyExistsException e){ //captura los errores de valores invalidos o Id existente
                System.out.println("Error: "+ e.getMessage());
            }catch(InputMismatchException e){
                logg.warn("El usuario ingresó un valor no numérico al registrar un producto");
                System.out.println("Error: debe de ingrear un valor número valido para el precio");
                sc.nextLine();
            } catch(CapacidadListaLlenaException e){
                System.out.println("Error: " + e.getMessage());
            }
            System.out.print("Presione ENTER para continuar...");
            sc.nextLine();
            System.out.println("===========================================");

        }

        public void registrarAlmacen(Scanner sc){
            System.out.println("---- REGISTRO DE ALMACEN----");
            try {
                 System.out.print("Ingrese el ID del Almacen: ");
            String almacenId = sc.nextLine().toUpperCase();
            System.out.print("Ingrese el nombre del Almacen: ");
            String nombreAlmacen = sc.nextLine().toUpperCase();
            System.out.print("Ingrese la dirección del Almacen: ");
            String ubicacion = sc.nextLine();
        

            tiendaGeneralService.addAlmacen(almacenId, nombreAlmacen, ubicacion);
            System.out.println("------Almacen registrado exitosamente------");
            } catch (IdAlreadyExistsException|InvalidDataException e) {
                 System.out.println("Error: "+ e.getMessage());

            }catch(InputMismatchException e){
                logg.warn("El usuario ingresó un valor no numérico al registrar un producto");
                System.out.println("Error: debe de ingrear un valor número valido para el precio");
                sc.nextLine();
            }catch(CapacidadListaLlenaException e){
                System.out.println("Error: " + e.getMessage());
            }
            System.out.print("Presione ENTER para continuar...");
            sc.nextLine();
            System.out.println("===========================================");
        }

        public void asignarProductoAlmacen(Scanner sc){
            System.out.println("---- AGREGAR PRODUCTOS AL ALMACEN----");
            System.out.println("Lista de los Almacenes de la Tienda: ");
            tiendaGeneralService.mostrarAlmacenesSedes();
            try {
                 System.out.print("Ingrese el ID del Almacen: ");
            String almacenId = sc.nextLine().toUpperCase();

            System.out.println("------------------------------------");
            System.out.println("Lista de los Almacenes de la Tienda: ");
            almacenService.mostrarTodosProductos();
            System.out.print("Ingrese el ID del producto: ");
            String productoId = sc.nextLine().toUpperCase();

            tiendaGeneralService.agregarProductoAlmacen(almacenId, productoId);;
            System.out.println("Producto: " + almacenService.findProducto(productoId).getNombre() +" Agregado a la sede: " + tiendaGeneralService.findAlmacen(almacenId).getNombreAlmacen()  );
            } catch (InvalidDataException|ProductoNotFoundException|AlmacenNotFoundException e) {
                 System.out.println("Error: "+ e.getMessage());

            }catch(InputMismatchException e){
                logg.warn("El usuario ingresó un valor no numérico al registrar un producto");
                System.out.println("Error: debe de ingrear un valor número valido para el precio");
                sc.nextLine();
            }
           
            System.out.print("Presione ENTER para continuar...");
            sc.nextLine();
            System.out.println("===========================================");
        }

        public void agregarStock(Scanner sc){

            try {
            
                System.out.println("---- AGREGAR STOCK DE PRODUCTO----");
                System.out.println("Lista de los Almacenes de la Tienda: ");
                tiendaGeneralService.mostrarAlmacenesSedes();

                System.out.print("Ingrese el ID del Almacen: ");
                String almacenId = sc.nextLine().toUpperCase();

                System.out.println("------------------------------------");
                System.out.println("Lista de los Productos de la Tienda: ");
            
                almacenService.mostrarTodosProductos();
                System.out.print("Ingrese el ID del Producto: ");
                String productoId = sc.nextLine().toUpperCase();
                sc.nextLine();
                System.out.println("------------------------------------");
                System.out.print("Ingresar Cantidad de stock del Producto "+almacenService.findProducto(productoId).getNombre()+": ");
                int cantidad = sc.nextInt();
                sc.nextLine();
                System.out.println("------------------------------------");
                tiendaGeneralService.actualizarCantidadProducto(almacenId, productoId, cantidad);
                System.out.println("-----CANTIDAD DE PRODUCTO AGREGADO EXITOSAMENTE------");

            } catch (InvalidDataException|ProductoNotFoundException|AlmacenNotFoundException e) {
                 System.out.println("Error: "+ e.getMessage());

            }catch(InputMismatchException e){
                logg.warn("El usuario ingresó un valor no numérico al registrar un producto");
                System.out.println("Error: debe de ingrear un valor número valido para el precio");
                sc.nextLine();
            }catch(ValorNegativoException e){
                System.out.println("Error: "+e.getMessage());
            }
           
            System.out.print("Presione ENTER para continuar...");
            sc.nextLine();
            System.out.println("===========================================");
        }

        

        public void buscarProducto(Scanner sc){
            try {
                System.out.print("Ingrese el ID del Producto: ");
            String productoId = sc.nextLine().toUpperCase();
            System.out.println(almacenService.findProducto(productoId));
            
            } catch (InvalidDataException|ProductoNotFoundException e) {
                 System.out.println("Error: "+ e.getMessage());

            }catch(InputMismatchException e){
                logg.warn("El usuario ingresó un valor no numérico al registrar un producto");
                System.out.println("Error: debe de ingrear un valor número valido para el precio");
                sc.nextLine();
            }
            System.out.print("Presione ENTER para continuar...");
            sc.nextLine();
            System.out.println("===========================================");
        }

        public void buscarAlmacen(Scanner sc){
            try {
                System.out.print("Ingrese el ID del Almacen: ");
                String almacenId = sc.nextLine().toUpperCase();
                System.out.println(tiendaGeneralService.findAlmacen(almacenId));
            
            
            } catch (InvalidDataException|AlmacenNotFoundException e) {
                 System.out.println("Error: "+ e.getMessage());

            }catch(InputMismatchException e){
                logg.warn("El usuario ingresó un valor no numérico al registrar un producto");
                System.out.println("Error: debe de ingrear un valor número valido para el precio");
                sc.nextLine();
            }
           
            System.out.print("Presione ENTER para continuar...");
            sc.nextLine();
            System.out.println("===========================================");
        }

        public void mostrarAlmacenes(){
           
            tiendaGeneralService.mostrarAlmacenes();
            System.out.print("Presione ENTER para continuar...");
            sc.nextLine();
            System.out.println("===========================================");
        }


        // Datos que se cargan al sistema para que arranque con Información y asi no toque ingresarla manualmente

        public  void cargarDatosDemo() {
           try {
            this.almacenService.addProducto("P001", "Teclado mecánico", 120000, 15);
            this.almacenService.addProducto("P002", "Mouse inalámbrico", 45000, 30);
            this.almacenService.addProducto("P003", "Monitor 24 pulgadas", 650000, 10);
            this.almacenService.addProducto("P004", "Audífonos bluetooth", 89000, 20);
            this.almacenService.addProducto("P005", "Webcam HD", 75000, 12);
            this.almacenService.addProducto("P006", "Silla ergonómica", 480000, 8);
            this.almacenService.addProducto("P007", "Base para portátil", 35000, 25);
            this.almacenService.addProducto("P008", "Micrófono USB", 150000, 18);


            // Almacenes
            this.tiendaGeneralService.addAlmacen("A001", "Almacén Central", "Cali");
            this.tiendaGeneralService.addAlmacen("A002", "Sucursal Norte", "Bogotá");
            this.tiendaGeneralService.addAlmacen("A003", "Sucursal Sur", "Medellín");

            // Asignación de productos a almacenes
            this.tiendaGeneralService.agregarProductoAlmacen("A001", "P001");
            this.tiendaGeneralService.agregarProductoAlmacen("A001", "P002");
            this.tiendaGeneralService.agregarProductoAlmacen("A001", "P003");
            this.tiendaGeneralService.agregarProductoAlmacen("A001", "P004");

            this.tiendaGeneralService.agregarProductoAlmacen("A002", "P001");
            this.tiendaGeneralService.agregarProductoAlmacen("A002", "P005");
            this.tiendaGeneralService.agregarProductoAlmacen("A002", "P006");

            this.tiendaGeneralService.agregarProductoAlmacen("A003", "P002");
            this.tiendaGeneralService.agregarProductoAlmacen("A003", "P003");
            this.tiendaGeneralService.agregarProductoAlmacen("A003", "P007");
            this.tiendaGeneralService.agregarProductoAlmacen("A003", "P008");
        } catch (CapacidadListaLlenaException | InvalidDataException | IdAlreadyExistsException e) {
     System.out.println("Error al cargar los productos iniciales: " + e.getMessage());}

           
    } 

}
