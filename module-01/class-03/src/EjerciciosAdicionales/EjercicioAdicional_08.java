package EjerciciosAdicionales;
//Escriba un programa que permita ingresar un número (va a representar la cantidad de años) 
//y de como respuesta la etapa en la que se encuentra la persona de acuerdo con la siguiente tabla.
/*Años	Etapas
0 - 5	Primera infancia
6 - 11	Infancia
12 - 18	Adolescencia
19 - 26	Juventud
27 - 59	Adultez
> 59	Persona mayor*/

import java.util.Scanner;

public class EjercicioAdicional_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la edad de la persona: ");
        int edad = scanner.nextInt();

       
        if ((edad>=0) && (edad<= 5)) {
            System.out.print("Primera infancia");
        }
         else if( (edad>=6) && (edad <= 11)){
            System.out.print("Infancia");
        } 
        else if (( edad>=12) && (edad <=18)){
            System.out.print("Adolecencia");
        }
         else if (( edad >=19) && (edad<=26)) {
            System.out.print("Juventud");
        }
         else if ((edad >= 27) && (edad<=59)){
            System.out.print("Adultez");
        } else{
            System.out.print("Persona Mayor");
        }

        scanner.close();


    }
}
