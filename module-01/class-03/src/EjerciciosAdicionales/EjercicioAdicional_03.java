package EjerciciosAdicionales;
//Escriba un programa que permita ingresar un número e identificar si es par o impar.

import java.util.Scanner;

public class EjercicioAdicional_03 {
    public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);

    System.out.print("Ingrese un número entero: ");
    var numero = scanner.nextInt();

     String mensajeternario =(numero % 2 == 0) ?"es un  número  par":"es un número impar"; // Se usa el operador ternario para determinar si el número es par o impar.
                    System.out.printf("%d  " +mensajeternario,numero);

    scanner.close();
        



    }
}
