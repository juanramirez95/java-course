import java.util.Scanner;


public class AnalizadorNumeros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;  //se declara que variable es un número entero.

        do {
            System.out.println("----------Analizador de Números----------");
            System.out.println("Selecciona una de las siguientes Opciones:");
            System.out.println("Opcion 1: Analizar un sólo numero.");
            System.out.println("Opcion 2: Generar secuencia de números.");
            System.out.println("Opcion 0:Salir.");
            System.out.print("Ingrese la opción deseada: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1: // Analizar un solo número
                    System.out.println("-------Analizar un Número---------");
                    System.out.print("Ingrese un número entero: ");
                    var numeroEntero = scanner.nextInt();

                    if (numeroEntero <0) {
                        System.out.printf(" es un número negativo.%n"); // esta condición  determina si es un número negativo.
                        
                    } else if (numeroEntero==0) {
                        System.out.printf(" es cero.%n");// Esta condición establece si el número es igual a cero

                        
                    }else { // en caso de que el numero ingresado sea mayor a cero, se imprime la salida de que el número es positivo.

                        System.out.printf(" es un número positivo.%n");

                    }

                     if (numeroEntero <100) {
                        System.out.printf(" es menor que 100.%n"); // esta condición  determina si es un número negativo.
                        
                    } else if (numeroEntero==100) {
                        System.out.printf(" es 100.%n");// Esta condición establece si el número es igual a cero

                        
                    }else { // en caso de que el numero ingresado sea mayor a cero, se imprime la salida de que el número es positivo.

                        System.out.printf(" es mayor que 100.%n");

                    }

                    String mensajeternario =(numeroEntero % 2 == 0) ?"es un  número  par":"es un número impar"; // Se usa el operador ternario para determinar si el número es par o impar.
                    System.out.println("    " +mensajeternario);




                    break;
            
                default:
                    break;
                case 2: // Generar secuencia de Números
                    
                    // Solicita los datos por teclado al usuario
                    System.out.printf("--------Generar secuencia de numeros--------.%n");

                    System.out.print("Ingrese el número inicial: ");
                     var numeroInicial = scanner.nextInt();
                    System.out.print("Ingrese el número inicial: ");
                     var numeroFinal = scanner.nextInt();

                     System.out.println("Escoja que tipo de secuencia desea: Par o Impar");
                     System.out.print("Ingrese la opción elegida: ");

                     String secuencia = scanner.next().toLowerCase(); // Registro la variable "Secuencia" como string y utilizo ".tolowerCase()" para asegurarme de que no importa si el usuario escribe en mayúscula o minúscula, la variable va a ser guardada en letra minúscula.

                    
                        
                    for (int i = numeroInicial;  i <= numeroFinal ; i++) { /*utilizo el bucle "for" para que se haga el recorrido por cada número que se incluye en la secuencia.
                                                                            el recorrido inicia en la posición que se registra en la variable "numeroInicial" y llega hasta la posición "numeroFinal"*/                     
                     
                        if (secuencia.equals("par")) {  /*Utilizo otro condicional para determinar que tipo de secuencia se va a imprimir: Par o impar */
                            if (i % 2 == 0) {
                                System.out.println(i);
                            }
                        } else if (secuencia.equals("impar")) {
                            if (i % 2 != 0) {
                                System.out.println(i);
                            }
                        }
                    }
                    break;
            }
        } while (opcion != 0);

        System.out.print("----------Analizador de Números Finalizado----------");

        scanner.close();
    }

    
}
