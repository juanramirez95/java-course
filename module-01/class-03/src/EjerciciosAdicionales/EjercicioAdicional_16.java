package EjerciciosAdicionales;
/*Escriba un programa que permita ingresar un número mientras estos sean negativos.
 Detener cuando el número ingresado sea positivo y de cómo resultado la cantidad de números negativos ingresado */
import java.util.Scanner;

public class EjercicioAdicional_16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num;
        int contador = 0;
        do {
            System.out.print("Ingrese un número: ");
            num = scanner.nextInt();
            contador ++;
            if (num >= 0) {
                contador--;
                break;}

            
        } while (num <0);

        System.out.printf("Cantidad de números negativos ingresados: %d%n",contador);
        System.out.print("-----Programa Finalizado-----");
        scanner.close();

        
            

            
        
    }
}
