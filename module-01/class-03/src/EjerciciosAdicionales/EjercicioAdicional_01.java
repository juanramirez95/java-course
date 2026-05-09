package EjerciciosAdicionales;
import java.util.Scanner;

public class EjercicioAdicional_01 {
    public static void main(String[] args) {
        /*Escriba un programa que permita ingresar un número real e identifique si el número es negativo, positivo o cero. */

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese un número real: ");
        int numero = scanner.nextInt();

        if (numero < 0) {
            System.out.print(numero+" es un número negativo.");
            
        } 
         else if (numero > 0) {
            System.out.print(numero+" es un número positivo.");
            
        } else {
            System.out.print(numero+" es cero.");
        }

        scanner.close();




    }
    
}