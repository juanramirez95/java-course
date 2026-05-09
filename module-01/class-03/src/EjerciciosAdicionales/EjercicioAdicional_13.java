package EjerciciosAdicionales;
/*Tres hermanos acuerdan reunirse. Los hermanos son identificados por los siguientes números: 1 para el mayor, 2 para el del medio y 3 para el menor.
Cuando llega la hora de la reunión, uno de los hermanos va tarde. Dados los números de los hermanos que llegan a tiempo, determine el número del hermano que llegará tarde.
Por ejemplo:

Input : 1 2
Output : 3
El tercer hermano es el el que llegara a la reunión tarde. */

import java.util.Scanner;

public class EjercicioAdicional_13 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Primero en llegar: ");
        var llegada1 = scanner.nextInt();
        System.out.print("Segundo en llegar: ");
        var llegada2 = scanner.nextInt();

        if ((llegada1 ==1)){

            if ((llegada2 ==2)) {
                 System.out.print("LLega tarde: Hermano Menor");
                
            } else if ((llegada2 ==3)) {
                 System.out.print("LLega tarde: Hermano del Medio"); 
                
            }    
        } else if ((llegada1 ==2)){

            if ((llegada2 ==1)) {
                 System.out.print("LLega tarde: Hermano Menor");
                
            } else if ((llegada2 ==3)) {
                 System.out.print("LLega tarde: Hermano Mayor"); 
                
            } 
         } else if ((llegada1 ==3)){

            if ((llegada2 ==2)) {
                 System.out.print("LLega tarde: Hermano Mayor");
                
            } else if ((llegada2 ==1)) {
                 System.out.print("LLega tarde: Hermano del medio"); 
                
            } 
        }
        scanner.close();
    
    } 
    
}
