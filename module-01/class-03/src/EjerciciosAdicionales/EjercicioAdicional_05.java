package EjerciciosAdicionales;
//Escriba un programa que permita ingresar una letra e identifique si es vocal o consonante.
import java.util.Scanner;

public class EjercicioAdicional_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese una letra del abecedario: ");
        var letra = scanner.next().toLowerCase().charAt(0);

        if (Character.isLetter(letra)){
        if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u'){

            System.out.print("La letra es una vocal ");
        }
        else{
            System.out.print("La letra es una consonante");
        }
    } else {
        System.out.print("No ingresó una letra válida");
    }

    scanner.close();

        

    }
}
