import java.util.Scanner;

public class UtilidadConsola {
    public static void main(String[] args) throws Exception {
         Scanner scanner = new Scanner(System.in);

    

      // Declaración de variables 
        final double pi = 3.14159;
        int opcion;
        
        // Ciclo do-while para que se ejecute el bucle hasta que se ingrese el número 4.
        do { 

            System.out.println("-------Aplicación de Utilidades en consola-------"); //  Se despliega el menú de opciones que hay.
           

            System.out.println("Opción 1: Calcular áera de una figura.");
            System.out.println("Opción 2: Calcular Edad Futura.");
            System.out.println("Opción 3: Verificar si el número es un número primo.");
            System.out.println("Opción 4: Salir.");

             System.out.print("Ingresa la Opción Deseada: "); // Registra la opción ingresada en la variable "opcion".
            opcion = scanner.nextInt();
            

            switch (opcion) {   // Utilizo la sentencia switch-case para navegar entre las opciones
                case 1: // En el case 1 esta la opcion de calcular area de una figura y se utiliza otra sentencia switch-case para escoger que tipo de area calcular (circulo o rectangulo).
                    System.out.println("----- CAlCULAR ÁREA DE UNA FIGURA -----");
                    System.out.println("Opcion 1: Calcular área del circulo.");
                    System.out.println("Opcion 2: Calcular área del Rectángulo.");
                    System.out.print("Ingrese la opción: ");
                    int opcionArea = scanner.nextInt(); // Guardo la opción en la variable "opcionArea".

                   
                    switch (opcionArea) { // Utilizo switch-case para escoger que área de la figura calcular.
                        case 1:
                            System.out.println("----- CAlCULAR ÁREA DE UN CIRCULO -----"); 
                            System.out.print("Ingrese el radio del circulo(en centimetros): "); // solicita al usuario ingresar un dato numérico
                            var radio = scanner.nextDouble(); // guarda el valor en una variable double


                            System.out.printf("El area del circulo es: %.2f cm2.%n",pi * (radio * radio));// Muestra el mensaje formateado con el especificador de punto flotante y salto de linea.
                            
                            
                            break;
                        case 2:
                            System.out.println("----- CAlCULAR ÁREA DE UN RECTÁNGULO -----");
                            System.out.print("Ingrese el largo del rectángulo(en centimetros): ");
                            var largo = scanner.nextDouble();

                            System.out.print("Ingrese el ancho del rectángulo(en centimetros): ");
                            var ancho = scanner.nextDouble();

                            System.out.printf("El area del Rectangulo es: %.1f cm2.%n", largo*ancho); // Muestra el mensaje formateado con el especificador de punto flotante y salto de linea.
                                  
                        default:
                            break;
                        } break;
                 case 2:
                            System.out.println("----- CAlCULAR EDAD FUTURA -----");

                            System.out.print("Ingrese el año de nacimiento: "); // Registra el año de nacimiento de la persona
                            var AñoNacimiento = scanner.nextInt();

                            System.out.print("Ingrese el año futuro: "); // Registra el año en el que desea saber la edad.
                            var AñoFuturo = scanner.nextInt();

                            System.out.printf("En el año "+ AñoFuturo+", usted tendrá %d años..%n", AñoFuturo-AñoNacimiento); // Muestra el mensaje formateado que muestra la resta entre año futuro y año de nacimiento.


                    break;
                case 3: System.out.println("----- VERIFICAR SI EL NÚMERO ES UN NÚMERO PRIMO -----");
                    
                    int numero;
                    System.out.print("Ingrese un número: ");
                    numero = scanner.nextInt();

                    var contador = 0;

                    for (int i = 1;i <=numero; i++){

                        if (numero % i ==0) {  //verifica si i es un numero divisor
                            contador++;
                        
                            
                        }


                    }

                    if (contador ==2){ // Si "contador" tiene exactamente dos divisores(1 y si mismo), se considera numero primo.
                        System.out.println( numero + " es un número primo.");
                    } else{
                        System.out.println( numero + " NO es un número primo.");
                    }

                    break;

                default:
                    break;
            }


            
        } while (opcion != 4); // si la opcion ingresada es diferente a "4", el bucle seguirá corriendo hasta que se ingrese la opción "4". 

        System.out.print("-----Menú finalizado-----");

        scanner.close();






    }
}