package EjerciciosAdicionales;
//Escriba un programa que permita ingresar una letra e identifique si la letra es mayúscula o minúscula.

import java.util.Scanner;


public class EjercicioAdicional_04 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);


    System.out.print("Ingrese una letra del abecedario: ");
    var letra = scanner.next().charAt(0);

    if (Character.isLetter(letra)){
        if (Character.isUpperCase(letra)){
            System.out.print("La letra es MAYÚSCULA ");
        }
        else{
            System.out.print("La letra es minúscula.");
        }
    } else {
        System.out.print("No ingresó una letra válida");
    }

    scanner.close();




  }
  

  

    
}
