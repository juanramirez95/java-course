import java.util.Scanner;


public class CalculadoraCompra {


    public static void main(String[] args)  {
    
    final double TASA_IMPUESTO = 0.15; // 15% de impuesto

    Scanner scanner = new Scanner(System.in);

    System.out.println("---Calculador de compa---");

    System.out.print("Ingrese el precio del articulo: ");
    var preciounitario= scanner.nextDouble();
    System.out.print("Ingrese la cantidad de articulos comprados: ");
    var articulos = scanner.nextInt();
    System.out.print("Ingrese la cantidad pagada por el cliente: ");
    var cantidadPagada = scanner.nextDouble();


    var subtotal = preciounitario * articulos; // Calcula el valor subtotal de la compra
    var impuesto = subtotal * TASA_IMPUESTO; // calcula el impuesto 
    var TotalCompra = subtotal + impuesto; // suma los valores subtotal e impuesto para hallar el total
    var cambio = cantidadPagada - TotalCompra; // El valor a devolver

    System.out.println("---Resumen de la Compra");

    System.out.printf("Subtotal: $%.2f%n",subtotal);
    System.out.printf("Impuesto: $%.2f%n",impuesto);
    System.out.printf("Total de la Compra: $%.2f%n",TotalCompra);
    System.out.printf("Cantidad pagada: $%.2f%n",cantidadPagada);
    System.out.printf("Cambio: $%.2f%n",cambio);

    System.out.println("----------------------------");

    System.out.println("Gracias por su compra");

    scanner.close();



        
    }
}
