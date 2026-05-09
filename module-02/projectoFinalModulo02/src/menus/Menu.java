package menus;

public class Menu {

public void menuPrincipal() {
System.out.println("""
        ===== MINI-SPOTIFY=====
    
        MENU PRINCIPAL:

        1. Gestionar Biblioteca Musical
        2. Gestionar Playlists
        3. Reproducir Canciones
        4. Cerrar Sesión
        
        """);
    
}
public void gestionarBibliotecaMusical(){
    System.out.println("""
        --------------BIBLIOTECA MUSICAL-----------------
            1. Listar canciones
            2. Agregar canción
            3. Buscar por nombre
            4. Volver
            """);
}

public void gestionarPlaylists(){
    System.out.println("""
    -----GESTION DE PLAYLIST-----
        1. Crear playlist
        2. Listar playlists
        3. Agregar canción a playlist
        4. Ver canciones
        5. Volver    
            
            """);
}

public void reproducirCanciones(){
    System.out.println("""
        ----- REPRODUCCIÓN DE CANCIONES-----
            1. Reproducir canción desde playlist
            2. Detener reproducción
            3. Volver
            """);
}
}
