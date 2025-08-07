package EjerciciosAdicionales;
/*Escriba una función en la que ingrese un número entero entre 1 y 12. Este número corresponde con un mes (1 enero, 2 febrero, ..., 12 diciembre). El resultado debe ser el número de días del mes ingresado.
Ejemplo:

Input : 12
Output : 31
A continuación encuentras la cantidad de días que tienen cada mes.

Mes	Días
1, 3, 5, 7, 8, 10 y 12	--31
4, 6, 9, 11	--30
2	--28 */

import java.util.Scanner;

public class EjercicioAdicional_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese un número entero entre 1 y 12 que representa un mes del año: ");
        var mes = scanner.nextInt();

        var DiasEnElMes = switch (mes){
            case 1,3,5,7,8,10,12 -> 31;
            case 4,6,9,11 -> 30;
            case 2 -> 28;
            default -> {
                System.out.println("No existe ese mes");
                yield -1;
            }
        };
        System.out.printf("Días en el mes %d: %d",mes,DiasEnElMes);


        scanner.close();

         
        
    }
}
