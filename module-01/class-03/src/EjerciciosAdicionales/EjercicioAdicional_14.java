package EjerciciosAdicionales;
/* Realice un programa en el que ingrese un número y muestre el cubo. Repita el proceso hasta que se introduzca un valor negativo.*/

import java.util.Scanner;

public class EjercicioAdicional_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;

        while (numero >= 0) {
            System.out.print("Ingrese un número: ");
             numero = scanner.nextInt();

             if (numero<0) {
                break;
                
             }

             System.out.printf("El cubo %d es: %d.%n",numero,numero*numero*numero);

            
        }
        
        System.out.print("-----Programa Finalizado-----");

        scanner.close();


    }
}
