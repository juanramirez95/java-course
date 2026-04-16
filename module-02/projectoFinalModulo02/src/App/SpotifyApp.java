package App;
import java.util.InputMismatchException;
import java.util.Scanner;
import menus.Menu;
import modelo.CuentaSpotify;



public class SpotifyApp {

    public static void main(String[] args)  {
       
        Scanner scanner = new Scanner(System.in);
        /**
         * Usuarios inicializados para poder ingresar a la aplicación
         * Usuario: juancrr123
         * Password: 1234
         */
        CuentaSpotify cuenta1 = new CuentaSpotify("juancrr123", "1234");
        var bibliotecaMusical = cuenta1.getBiblioteca();
        Menu menu = new Menu();
        int intentos=0;


        System.out.println("-----INICIAR SESIÓN SPOTIFY-----");
do {

    System.out.print("Ingrese el usuario: ");
    var usuario = scanner.nextLine();
    System.out.print("Ingrese la contraseña: ");
    var password = scanner.nextLine();

    if (!cuenta1.iniciarSesion(usuario, password)) {
        intentos++;
        System.out.println("Usuario o contraseña incorrectos. Intentos restantes: " + (3 - intentos));
    }

} while (!cuenta1.sesionIniciada && intentos < 3);

if (!cuenta1.sesionIniciada) {
    System.out.println("Demasiados intentos fallidos.Cuenta Bloqueada.");
    scanner.close();
    return;
}            
      
        while (cuenta1.sesionIniciada==true) {
           System.out.println("------------------------------------------------------");
            System.out.println("¡Sesion Iniciada! Bienvenido "+cuenta1.getUsuario());
            System.out.println("------------------------------------------------------");
            menu.menuPrincipal();
           
            System.out.print("Seleccione una opción: ");     
            try {// utilizo este mecanismo para validar que en los campos que debe de recibir números, no se detenga si recibe letras
            var opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
    
                case 1: //GESTIONAR BIBLIOTECA MUSICAL
                        do { 
                        menu.gestionarBibliotecaMusical();
                        System.out.print("Ingrese la opción deseada: ");
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcion) {
                            case 1: //LISTAR CANCIONES
                                System.out.println("------CANCIONES EN LA BIBLIOTECA MUSICAL-----");
                                System.out.println(bibliotecaMusical.listarCanciones());
                                System.out.printf("Total canciones: %d%n",bibliotecaMusical.getContadorCatalogo());
                                System.out.print("Presione ENTER para continuar...");
                                    scanner.nextLine();
                                break;
                            case 2: //AGREGAR CANCION
                                    System.out.println("-----AGREGAR CANCION A BIBLIOTECA MUSICAL-----");
                                    System.out.print("Nombre de la cancion: ");
                                    var titulo = scanner.nextLine();
                                
                                    System.out.print("Artista: ");
                                    var artista = scanner.nextLine();
                                    
                                    System.out.print("Duracion de la cancion en segundos: ");
                                    var duracionSegundos = scanner.nextInt();
                                    scanner.nextLine();
                                    bibliotecaMusical.agregarCancion(titulo, artista, duracionSegundos);
                                    System.out.println("-:CANCION AGREGADA EXITOSAMENTE A LA BIBLIOTECA MUSICAL:-");
                                    System.out.print("Presione ENTER para continuar...");
                                    scanner.nextLine();
                                        break;
                            case 3: // BUSCAR POR NOMBRE
                                    System.out.println("-----BUSCAR CANCION POR NOMBRE-----");
                                    System.out.println("Ingrese el nombre de la canción: ");
                                    var nombreCancion = scanner.nextLine();
                                    System.out.println(bibliotecaMusical.buscarCancion(nombreCancion));
                                    System.out.print("Presione ENTER para continuar...");
                                    scanner.nextLine();
                            
                                break;
                            
                            
                                
                                
                        }
                        } while (opcion!=4);
                        break;
                        
                        
                case 2: // GESTIONAR PLAYLIST 
                    do {
                        
                        menu.gestionarPlaylists();
                        
                        System.out.print("Seleccione una opción: ");
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        
                        switch (opcion) {
                            case 1: // crear playlist
                            
                            System.out.println("------ CREAR PLAYLIST-----");
                            System.out.print("Ingrese el nombre de la nueva Playlist: ");
                            var nombre = scanner.nextLine();
                            cuenta1.agregarPlaylist(nombre);;
                            System.out.println("¡PLaylist creada exitosamente!");
                            System.out.print("Presione ENTER para continuar...");
                                    scanner.nextLine();
                                break;
                            case 2: // Listar playlist
                            System.out.println("---TUS PLAYLISTS---");
                            cuenta1.listarPlaylistUsusario();
                            System.out.print("Presione ENTER para continuar...");
                                scanner.nextLine();
                                break;
                            
                            case 3: //Agregar cancion a playlist
                            System.out.println("------AGREGAR CANCION A PLAYLIST-----");
                            System.out.println("-:TUS PLAYLISTS:-");
                            cuenta1.listarPlaylistUsusario();
                            System.out.print("Ingresa el indice de tu playlist: ");
                            var indexNombre = scanner.nextInt();
                            System.out.println(bibliotecaMusical.listarCanciones());
                            System.out.print("Ingresa el indice de la cancion que deseas agregar a tu playlis: ");
                            var index = scanner.nextInt();
                            scanner.nextLine();

                            cuenta1.agregarCancionAPlaylist(indexNombre, index);
                            System.out.print("Presione ENTER para continuar...");
                            scanner.nextLine();
                            

                            break;
                            
                            case 4: //Listar canciones
                            
                            System.out.println("------AGREGAR CANCION A PLAYLIST-----");
                            cuenta1.listarPlaylistUsusario();
                            System.out.print("Ingresa el indice de tu playlist: ");
                            index = scanner.nextInt();
                            scanner.nextLine();
                    
                            cuenta1.listarCancionesPlaylist(index);
                            
                            System.out.println("Presione ENTER para continuar...");
                                    scanner.nextLine();
                           
                            break;


                            
                            
                        
                            default:
                                System.out.println("Ingresa una opción valida");
                                break;
                        }
                        
                    } while (opcion!=5);
                        

                        
                        break;
                case 3: //REPRODUCIR CANCIONES
                       do {
                        
                        menu.reproducirCanciones();
                        System.out.print("Ingrese la opcion que desea hacer: ");
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcion) {
                            case 1://Reproducir cancion desde playlist
                            System.out.println("-----REPRODUCIR CANCIÓN DESDE PLAYLIST-----");
                            cuenta1.listarPlaylistUsusario();
                            System.out.print("Ingrese el indice de la playlist: ");
                            var indexNombre = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("-------------------------------------------");
                            cuenta1.listarCancionesPlaylist(indexNombre);
                            System.out.print("Ingrese el indice de la cancion que quiere reproducir: ");
                            int index = scanner.nextInt();
                            scanner.nextLine();

                            cuenta1.reproducir(indexNombre,index);
                            System.out.println("Presione ENTER para continuar...");
                                    scanner.nextLine();
                            
                                
                                break;
                            case 2:// Detener Reproduccion 
                            cuenta1.detener();
                                break;
                            case 3: // Volver
                                break;
                            default:
                                System.out.println("Ingresa una opción valida");
                                break;
                        }
                       } while (opcion!=3); 
                        break;
                case 4: // CERRAR SESIÓN
                        cuenta1.cerrarSesion();
                        System.out.println("------------------------------------------------------");
                        System.out.println("Sesión Finalizada");
                        break;
                
            
                default:
                    System.out.println("Escoja una opción valida.");
                    break;
            } } catch (InputMismatchException e) {
                System.out.println("Solo puedes ingresar numeros.");
                scanner.nextLine();
                
            }

        }
        System.out.println("Gracias por usar MINI-SPOTIFY, ¡HASTA PRONTO!");
        scanner.close();

    }

    
}
