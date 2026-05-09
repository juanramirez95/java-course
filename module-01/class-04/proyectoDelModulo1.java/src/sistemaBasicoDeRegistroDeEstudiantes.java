import java.util.Scanner;

public class sistemaBasicoDeRegistroDeEstudiantes { 
    // Declaracion de variables static para poder usar los datos en otras funciones  
    static String nombreEstudiante;
    static double nota1,nota2,nota3; 

    public static void main(String[] args) { //metodo principal que contiene la lógica del programa
        Scanner scanner = new Scanner(System.in);
       int opcion;            //declaro variables como "opcion" para que lea numero enteros y "salir" como una booleana para finalizar el programa.
       var salir = false;
        do { // utilizo el ciclo DO-WHILe para asegurarme de que el programa se ejecute, aunque sea, una vez y se repita hasta que la variable "salir" sea verdadera
        
        System.out.println("----- Sistema de Registro de estudiantes-----");
        System.out.println( """ 
                        1. Registrar datos de un estudiante
                        2. Mostrar datos del estudiante actual
                        3. Calcular promedio de notas del estudiante actual
                        0. Salir """);
        System.out.print("Ingrese su opción: ");
        opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) { // Utilizo SWITCH para que el menú pueda identificar que opcion ejecutar según lo requerido
            case 1:
            registroEstudiante(scanner);
                
                break;
            case 2:
            mostrarInfoEstudiante();
                
                break;
            case 3:
            calcularPromedioEstudiante();
                
                break;
            case 0:
            System.out.println("Saliendo de la aplicación"); 
            salir = true;
            
                break;
        
            default:
            System.out.println("Opción Invalida, ingrese otra opción");
                break;
        }
        } while (!salir);
        System.out.println("----- Aplicación Finalizada-----");



        scanner.close(); // El metodo "main" se asegura de cerrar el scanner y así evitar que siga recibiendo datos externos
    }

    public static double leerNota(Scanner scanner, String mensaje) { // metodo, con parametros, para pedir las notas del estudiante. 
        double entrada;
            
        do {
        System.out.print(mensaje);
         entrada = scanner.nextDouble();
        scanner.nextLine();
        
            
        } while (!validarNota(entrada));  //para validar que se siga pidiendo el valor de la nota si no se ingresa un valor que este dentro del rango, utilizo el ciclo DO-WHILE 
        return entrada;                  // en el que establezco que mientras la nota que se ingresa este por fuera del rango establecido, se seguirá pidiendo el valor de esa nota.
        
        
    }

    public static String leernombre(Scanner scanner, String mensaje) { // metodo, con parametros, para ingresar el nombre del estudiante
        String entrada;
        do{
        System.out.print(mensaje);
        entrada = scanner.nextLine().toUpperCase();
        //scanner.nextLine();
        } while(!validarNombre(entrada));
        return entrada;
    }

    private static void calcularPromedioEstudiante() { // como las variables "nota1","nota2","nota3" son static, puedo utilizar su valor para calcular el promedio y mostrarlo en la consola.
         var promedio = (nota1+nota2+nota3)/3;
         System.out.println("============================================="); 
         System.out.println("Promedio de notas del estudiante");
         System.out.printf("Promedio de las notas: %.2f%n",promedio);
          System.out.println("============================================");
       
    }


    private static void mostrarInfoEstudiante() { // Este metodo muestra los valores ingresados en el metodo "registroEstudiante" gracias a que sus variables son static
        System.out.println("============================================="); 
        System.out.println("Información del estudiante");
        System.out.println("Nombre del estudiante: "+nombreEstudiante);
        System.out.println("Nota 1: "+nota1);
        System.out.println("Nota 2: "+nota2);
        System.out.println("Nota 3: "+nota3);
        System.out.println("============================================="); 
        
    }

    private static void registroEstudiante(Scanner scanner) {// Este metodo registra la informacion del estudiante, mediante el uso de los metodos
         System.out.println("============================================="); 
        System.out.println("Registro de Estudiante");
        nombreEstudiante = leernombre(scanner, "Ingrese nombre del estudiante: ");
        System.out.println("-------Ingrese las tres notas del estudiante-------");
        nota1 = leerNota(scanner,"Ingrese la primer nota: ");
        nota2 = leerNota(scanner,"Ingrese la segunda nota: ");
        nota3 = leerNota(scanner,"Ingrese la tercera nota: ");
        System.out.println(".: Notas registradas exitosamente:.");
        System.out.println(".: Estudiante registrado exitosamente:.");
         System.out.println("============================================="); 
        System.out.println("Presione ENTER para continuar");
        scanner.nextLine();
    }

    public static boolean validarNota( double nota){ // con este metodo booleano valido que la nota ingresada este entre un rango especifico. En este caso, entre 0 y 100
        return nota >= 0 && nota <= 100;}
    
    public static boolean validarNombre(String nombre){
        return nombre != null && !nombre.isEmpty(); 

    }
}
