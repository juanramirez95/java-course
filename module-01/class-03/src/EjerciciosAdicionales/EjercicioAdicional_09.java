package EjerciciosAdicionales;
/* Un vendedor al por mayor de camisas ofrece diferentes precios de acuerdo a la cantidad de unidades que le compren. La siguiente tabla muestra la información:

Unidades	Precio por unidad
[1 - 50]	$50.000
(50 - 100]	$45.000
(100 - 150]	$40.000
(150 - 200]	$35.000
> 200	$25.000*/

import java.util.Scanner;

public class EjercicioAdicional_09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de camisas a comprar: ");
        var cantidad = scanner.nextInt();

        if ((cantidad>= 1) && (cantidad <= 50)) {
            System.out.print("Precio por unidad: $50.000");
        }
         else if( (cantidad > 50) && (cantidad <= 100)){
            System.out.print("Precio por unidad: $45.000");
        } 
        else if (( cantidad>100) && ( cantidad <=150)){
            System.out.print("Precio por unidad: $40.000");
        }
         else if ((cantidad > 150) && (cantidad<=200)) {
            System.out.print("Precio por unidad: $35.000");
        } else{
            System.out.print("Precio por unidad: $25.000");
        }

        scanner.close();






    }
}
