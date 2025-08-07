package EjerciciosAdicionales;
// Escriba un programa que le permita ingresar tres números e imprima en la consola el más grande de ellos.

import java.util.Scanner;

public class EjercicioAdicional_02 {
        public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el primer número: ");
        var num1 = scanner.nextInt();

         System.out.print("Ingrese el segúndo número: ");
        var num2 = scanner.nextInt();

         System.out.print("Ingrese el tercer número: ");
        var num3 = scanner.nextInt();


        if ((num1>num2)&&(num1>num3)) {
            System.out.print(num1+" es el número mas grande.");
            
        } else if(num2>num3) {
            System.out.print(num2+" es el número mas grande.");
            
        } else {
            System.out.print(num3+" es el número mas grande.");
        }

        scanner.close();
        
    }
    
}
