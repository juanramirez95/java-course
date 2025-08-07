package EjerciciosAdicionales;
// Escriba un programa que permita ingresar un número y determine si es divisible por dos y siete.

import java.util.Scanner;

public class EjercicioAdicional_06 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese un número: ");
        var numero = scanner.nextInt();

        String numeroDivisible = ((numero % 2 ==0)&&(numero%7==0))? "El número es divisible por 2 y 7":"El número no es divisible por 2 y 7";

        System.out.print(numeroDivisible);

        scanner.close();

    }
    
}
