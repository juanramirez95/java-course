package EjerciciosAdicionales;
//Escriba un programa que permita ingresar tres números (representan la longitud de una línea) y compruebe si con estos números se puede formar un triángulo.

import java.util.Scanner;

public class EjercicioAdicional_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la longitud del lado 1: ");
        var lado1= scanner.nextDouble();

        
        System.out.print("Ingrese la longitud del lado 2: ");
        var lado2= scanner.nextDouble();

        
        System.out.print("Ingrese la longitud del lado 3: ");
        var lado3= scanner.nextDouble();

        // Para saber si los tres lados pueden formar un triángulo, se debe de cumplir
        // que la suma de las dos longitudes de dos lados debe ser mayor que la longitud del tercer lado.

        if((lado1+lado2>lado3)&&(lado1+lado3>lado2)&&(lado2+lado3>lado1)){
            System.out.print("Con estas longitudes se puede formar un triángulo.");
        }else{
            System.out.print("No se puede hacer un triángulo con estas longitudes");
            
        }
        scanner.close();
    }
}
