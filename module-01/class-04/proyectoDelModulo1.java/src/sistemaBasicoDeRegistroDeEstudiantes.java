import java.util.Scanner;

public class sistemaBasicoDeRegistroDeEstudiantes {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        menuPrincipal(scanner);
       
    }

    private static void menuPrincipal(Scanner scanner) {
       int opcion;
        do {
        
        System.out.println("----- Sistema de Registro de estudiantes-----");
        System.out.println( """ 
                        1. Registrar datos de un estudiante
                        2. Mostrar datos del estudiante actual
                        3. Calcular promedio de notas del estudiante actual
                        4. Salir """);
        System.out.print("Ingrese su opción: ");
        opcion = scanner.nextInt();
        } while (opcion != 4);
    }
}

