package EjerciciosAdicionales;
/*En un día soleado y caluroso Sebastian y Adriana salen en busca de una sandia. Se dirigen a la tienda más cercana y compran la más grande y jugosa.
En casa se disponen a dividir y a comer la fruta pero se les presenta un gran problema y es que Adriana y Sebastian son amantes de los números pares. De manera que quieren dividir la sandía en dos partes no necesariamente iguales pero ambas deben ser pares.
Escriba una función en el que pueda ingresar el peso de la sandía en kilogramos y la respuesta sea:

SI en caso de que se pueda dividir este peso en números pares
NO de lo contrario. */

import java.util.Scanner;

public class EjercicioAdicional_12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el peso de la sandía en kg: ");
        var peso = scanner.nextDouble();

        String mensajePeso = (peso%2==0) ? "SI":"NO";


        System.out.println(mensajePeso);

        scanner.close();



    }
}
