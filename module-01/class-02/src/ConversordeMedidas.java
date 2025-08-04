import java.util.Scanner;

public class ConversordeMedidas {
    public static void main(String[] args)  {
        
         Scanner scanner = new Scanner(System.in);
    
    // Declaración de Constantes

    final var CM_POR_METRO = 100.0;
    final var LIBRAS_POR_KG = 2.20462;
    final var FACTOR_CELSIUS_FAHRENHEIT = 1.8;
    final var OFFSET_FAHRENHEIT = 32.0;

   
    System.out.println("-------Bienvenido al conversor de medidas--------");
   
    System.out.print("Ingrese la cantidad en Metros: "); // Solicita ingresar el valor en metros
    var valor_metros = scanner.nextDouble(); // guarda la cantidad ingresada como un número  double

    System.out.printf(+valor_metros+" metros equivalen a %.2f centimetros.%n",valor_metros * CM_POR_METRO); // Imprime la salida de datos formateada con dos decimales y salto de linea


    System.out.print("Ingrese el peso en kilogramos: ");
    var valor_kilogramos = scanner.nextDouble(); // guarda la cantidad ingresada como un número  double
    System.out.printf(+valor_kilogramos+" metros equivalen a %.2f libras.%n",valor_kilogramos * LIBRAS_POR_KG); // Imprime la salida de datos formateada con dos decimales y salto de linea

    System.out.print("Ingrese la temperatura en grados Celsius: ");
    var valor_Celsius = scanner.nextDouble(); // guarda la cantidad ingresada como un número  double
    System.out.printf(+valor_Celsius+" celsius equivalen a %.1f grados fahrenheit.%n",valor_Celsius * FACTOR_CELSIUS_FAHRENHEIT + OFFSET_FAHRENHEIT); // Imprime la salida de datos formateada con dos decimales y salto de linea


    



        
    System.out.println("------------Conversiones Completadas------------");

    scanner.close(); // Se cierra el scanner para que el sistema no sig registrando nuevas entradas.
    

    }
}
