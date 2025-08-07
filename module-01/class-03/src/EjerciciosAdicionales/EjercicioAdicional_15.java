package EjerciciosAdicionales;

import java.util.Scanner;

/*Escriba un programa en el que ingrese un número y le diga si es positivo o negativo. Repita el proceso hasta que se ingrese el número cero. */
public class EjercicioAdicional_15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numero;
        do {
            System.out.print("Ingrese un numero: ");
            numero = scanner.nextInt();

            if (numero == 0) {
                break;}

            String mensajeNumero = (numero>0)?"Número positivo":"Número negativo";

            System.out.println(mensajeNumero);
        } while (numero!=0);

        System.out.print("-----Programa Finalizado-----");
        scanner.close();
    }
    
}
