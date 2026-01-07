
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
      Scanner scanner = new Scanner(System.in);
      int opcion;
      var salir = false;

      do {// Menu principal que contiene los ejercicios propuestos en el modulo 2.
        System.out.println("-----------: Menu Ejercicios Modulo 2 :-----------");
        System.out.println("""
                            1. Ejercicio Autónomo: Modelando un Empleado
                            2. Ejercicio 1: Modelando una cuenta Bancaria Simple
                            3. Ejercicio 2: Ejercicio "Perfil de Usuario tipo Instagram"
                            4. Ejercicio 3: Cancion estilo Spotify
                """);
                System.out.print("Ingrese su opción: ");
        opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) { // EJERCICIOS DEL MODULO 2
            case 1:// Ejecicio aútonomo 
                  //Objetos inicializados
               var empleado1 = new Empleado("Juan Camilo Ramirez Ruiz","544695","Restaurante");
                 var empleado2 = new Empleado("Alexandra Muñoz", "1144085545", 2500000.00, "Recursos Humanos");
                 var empleado3 = new Empleado("Alejandro Ortiz","123456789","Medicina" );
               do {// Sub-menu que muestra las opciones para hacer en el modelado del empleado
                
                      System.out.println("-----------: Modelando un Empleado :-----------");
                  System.out.println("""
                            
                            1. Mostrar información Inicial 
                            2. Modificar salario y departamento de alguno de los empleados
                            3. Mostrar informcaión modificada
                            4. Calcular salario Anual
                            5. Regresar al menu anterior
                """);
                System.out.print("Ingrese su opción: ");
                opcion = scanner.nextInt();

                    switch (opcion) {
                      case 1:// muestra la información inicial de los empleados
                         System.out.println("=============.:Información Inicial:.===================");
                      System.out.println(empleado1.mostrarInformacion());
                      System.out.println(empleado2.mostrarInformacion());
                      System.out.println(empleado3.mostrarInformacion());
                      break;

                      case 2: // Se modifica el salario y departametno de alguno de los empleados
                       System.out.println("=============.:Modificar salario y departamento de alguno de los empleados:.===================");
                        System.out.printf("""
                            Selecciones el empleado que desea modificar
                            
                            1. Emlpeado 1: %s%n
                            2. Empleado 2: %s%n
                            3. Empleado 3: %s%n
                           
                            """,empleado1.getNombreCompleto(),empleado2.getNombreCompleto(),empleado3.getNombreCompleto());
                            System.out.print("Ingrese su opción: ");
                            opcion = scanner.nextInt();

                            System.out.printf("Ingrese el valor del salario mensual: ");
                            Double nuevoSalario= scanner.nextDouble();
                            System.out.print("Ingrese el nuevo departamento: ");
                            String nuevoDepartamento= scanner.next();

                            switch (opcion) { // MODIFICA EL SALARIO DEL EMPLEADO SELECCIONADO
                              case 1:
                                empleado1.setSalarioMensual(nuevoSalario);
                                empleado1.setDepartamento(nuevoDepartamento);
                                System.out.println("---------Informacion actualizada--------");
                                break;
                              case 2: 
                                empleado2.setSalarioMensual(nuevoSalario);
                                empleado2.setDepartamento(nuevoDepartamento);
                                System.out.println("---------Informacion actualizada--------");
                                break;
                              case 3: 
                                empleado3.setSalarioMensual(nuevoSalario);
                                empleado3.setDepartamento(nuevoDepartamento);
                                System.out.println("---------Informacion actualizada--------");
                                break;
                              default:
                                System.out.println("Opción inválida, no se modificó ningún empleado.");
                           
                            }
                       break;
                      case 3: //Información actualizada de los empleados
                         System.out.println("==========Informacion actualizada======================");
                         System.out.println(empleado1.mostrarInformacion());
                         System.out.println(empleado2.mostrarInformacion());
                         System.out.println(empleado3.mostrarInformacion());
                      break;
                      case 4: // Calcula el Salario Anual de un empleado (empleado 1)
                      System.out.println("=============.: Calcular Salario Anual :.===================");  
                                                                                                                                // Sub-menu que muestra los empleados que puede modificar
                      System.out.printf(""" 
                            Selecciones el empleado al que desea calcular el salario anual:
                            
                            1. Emlpeado 1: %s%n
                            2. Empleado 2: %s%n
                            3. Empleado 3: %s%n
                           
                            """,empleado1.getNombreCompleto(),empleado2.getNombreCompleto(),empleado3.getNombreCompleto());
                            System.out.print("Ingrese su opción: ");
                            opcion = scanner.nextInt();

                            switch (opcion) { // MODIFICA EL SALARIO DEL EMPLEADO SELECCIONADO
                              case 1:
                               var salarioAnual =empleado1.calcularSalarioAnual(empleado1.getSalarioMensual()); 
                                System.out.printf("salario anual del empleado %s es: $ %.2f%n",empleado1.getNombreCompleto(), salarioAnual);
                                break;
                              case 2: 
                                var salarioAnual2 =empleado2.calcularSalarioAnual(empleado2.getSalarioMensual()); 
                                System.out.printf("salario anual del empleado %s es: $ %.2f%n",empleado2.getNombreCompleto(), salarioAnual2);
                                break;
                              case 3: 
                                var salarioAnual3 =empleado3.calcularSalarioAnual(empleado3.getSalarioMensual()); 
                                System.out.printf("salario anual del empleado %s es: $ %.2f%n",empleado3.getNombreCompleto(), salarioAnual3);
                                break;
                              default:
                                System.out.println("Opción inválida, no se modificó ningún empleado.");
                           
                            }
                       break;


                    
                      
                    }
                
               } while (opcion!=5);
                     
              
                break;
            case 2:// Ejercicio "cuenta Bancaria Simple"
             var cuentaBancaria1 = new CuentaBancaria("null", "null");
             System.out.println("-----------: Ejercicio 1: Modelando una cuenta Bancaria Simple :-----------");
             do {
        
              System.out.println("""
                    ===============MENU CUENTA BANCARIA SIMPLE======================
                    1. Crear cuenta Bancaria
                    2. Mostrar información.
                    3. Realizar Deposito bancario.
                    4. Realizar Retiro Bancario.
                    5. Regresar al menu anterior
                """);
              System.out.print("Ingrese la opción: ");
              opcion = scanner.nextInt();
              switch (opcion) {
              case 1:// crear cuenta bancaria
                
                System.out.println("----------------------------------------------");
                System.out.println("Crear cuenta Bancaria");

                System.out.print("Ingrese nombre del Usuario: ");
                scanner.nextLine();
                var nombreCliente = scanner.nextLine().toUpperCase();
                //scanner.nextLine();
                System.out.print("Ingrese la nueva cuenta del Usuario: ");
                var nuevaCuenta = scanner.nextLine();

                if ((nombreCliente.isEmpty())&&(nuevaCuenta.isEmpty())) {
                    System.out.println("Error: los datos deben de estar completos");
                }else{
                    cuentaBancaria1.setNombreTitular(nombreCliente);
                    cuentaBancaria1.setNumeroCUenta(nuevaCuenta);
                    
                  
                    System.out.println("-------------:Cuenta reada con Exito:--------------");
                }
                break;
              case 2:// mostrar informcacion del cliente
                cuentaBancaria1.mostrarInformacion();
                break;
              case 3:// realizar deposito bancario 
              System.out.println("----------------------------------------------");
              System.out.print("Ingrese el monto a DEPOSITAR: ");
              var monto = scanner.nextDouble();
            
              cuentaBancaria1.depositar(monto);
              break;
              case 4:// realizar retiro bancario
                System.out.println("----------------------------------------------");
                System.out.print("Ingrese el monto a RETIRAR: ");
                var monto2 = scanner.nextDouble();
            
                cuentaBancaria1.retirar(monto2);
              break;

              default:
              break;}// fin de switch sub-menu cuenta bacaria
           } while (opcion!=5);
           break;
            case 3:// Ejercicio "Perfil de Usuario tipo Instagram"
            // instanciar clase
            PerfilInstagram miPerfil = new PerfilInstagram("@Jramirezruiz95", "Bibliografia de Juan");
            System.out.println("Perfil creado exitosamente");
            break;
            case 4: //Cancion estilo Spotify
            Cancion micaCancion=new Cancion("Ponte", "DFZM");
            micaCancion.reproducir();
            micaCancion.reproducir();
            System.out.println(micaCancion.toString()); 
            break;
            



            
          }; // fin witch menu principal
    
        }while (!salir);
        
          
        scanner.close();
      }//do-while bucle menu principal
    } // main
