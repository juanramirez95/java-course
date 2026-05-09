package EjerciciosAdicionales;
/* Escriba una función en la que pueda ingresar tres números enteros y diferentes. Imprima estos valores en orden ascendente. */

import java.util.Scanner;

public class EjercicioAdicional_11 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int primero =0;
        int segundo =0;
        int tercero =0;

        System.out.print("Ingrese el primer número: ");
        var num1 = input.nextInt();
        
        System.out.print("Ingrese el segundo número: ");
        var num2 = input.nextInt();

        
        System.out.print("Ingrese el tercer número: ");
        var num3 = input.nextInt();

        if ((num1<num2)&&(num1<num3)) {
            
            primero = num1;

            if (num2<num3) {
                segundo = num2;
                tercero = num3;
                
            } else if (num3<num2) {
                segundo = num3;
                tercero = num2;
                
            }
        } else if ((num2<num1)&&(num2<num3)) {
                primero = num2;

                if (num1<num3) {
                segundo = num1;
                tercero = num3;
                
            } else if (num3<num1) {
                segundo = num3;
                tercero = num1;

                
            }
        } else if ((num3<num1)&&(num3<num2)) {
            primero = num3;

                if (num1<num2) {
                segundo = num1;
                tercero = num2;
                
            } else if (num2<num1) {
                segundo = num2;
                tercero = num1;

                
            }
            
        }

        System.out.printf("%d - %d - %d",primero,segundo,tercero);
        input.close();
       
       
       
       
       



        
    }
}
