package modelo;

public class CuentaSpotify {
private String usuario;
private String password;
private Playlist[] playlists;
private BibliotecaMusical biblioteca;
private boolean sesionIniciada;
private int contadorPlaylists;
private boolean reproducir = false;

public CuentaSpotify(String usuario, String password) {
    this.usuario = usuario;
    this.password = password;
    this.sesionIniciada = false;
    this.playlists = new Playlist[50];
    this.contadorPlaylists = 0;
    this.biblioteca = new BibliotecaMusical(50);
    

}




public Playlist[] getPlaylists() {
    return playlists;
}
public BibliotecaMusical getBiblioteca() {
    return biblioteca;
}
public String getUsuario() {
    return usuario;
}
public String getPassword() {
    return password;
}
public boolean getSesionIniciada(){
    return sesionIniciada;
}
public boolean isReproducir() {
    return reproducir;
}
public void setReproducir(boolean reproducir) {
    this.reproducir = reproducir;
}




private void setSesionIniciada(boolean sesionIniciada) {
    this.sesionIniciada = sesionIniciada;
}

/**
 * valida si el usuario y el password coinciden con los datos que estan creados y retorna un valor booleano para dar acceso al menu de usuario, de esta manera se mantiene 
 * el encapsulamiento
 * @param usuario
 * @param password
 * @return
 */
public boolean iniciarSesion(String usuario, String password) { 
     setSesionIniciada(usuario.equals(this.usuario)&& password.equals(this.password)); 
    return sesionIniciada;
}



/**
 * Crea una nueva playlist  dentro de la playlist de la cuenta del usuario, Playlist y CuentaUsuario tienen una relación por composición, sin el usuario no deberia de existir esa playlist.
 * @param nombre
 */
public void agregarPlaylist(String nombre){ //
    Playlist p = new Playlist(nombre);
    if (contadorPlaylists >= playlists.length) return;
    playlists[contadorPlaylists] = p;
    contadorPlaylists++;
}
/**
 * Busca el nombre de la playlist dentro de la lista de playlists que el usuario tiene creadas
 * @param nombre
 * @return
 */

//--------------------------------------------------------

public Playlist getPlaylistPorIndice(int index){
    if (index<0 || index>=contadorPlaylists) return null;
    return playlists[index];
}


/**
 * Busca la playlist y agrega la cancion que se desea agregar a la playlist
 * @param nombre
 * @param index
 */
public void agregarCancionAPlaylist(int indexNombre, int index){ 
    
    Playlist p = getPlaylistPorIndice(indexNombre);
    if (p == null) return;

    Cancion c = biblioteca.getCancionPorIndice(index);
    if (c==null) return;
   
    p.agregarCancion(c);
    System.out.println("Canción agregada a tu playlist: "+ p.getNombre());
    
}
/**
 * Muestra las canciones que estan dentro de la playlist seleccionada
 * @param nombre
 * @return
 */

//----------------------------------------------------------------------------------
public void listarCancionesPlaylist(int index){ //Lista las canciones que estan en la playlist
    
    Playlist p = getPlaylistPorIndice(index);
    System.out.printf("""
    .:Playlist: %s%n""",p.getNombre());
     p.listarCanciones();
     
}
//----------------------------------------------------------------------------------

public boolean reproducir(int  indexNombre, int index){
    Playlist p = getPlaylistPorIndice(indexNombre);
    if (p == null) return false;
    System.out.println("----------------------------------------");
    p.reproducirCancion(index);
    System.out.println("----------------------------------------");
    return reproducir= true;
}
//----------------------------------------------------------------

public void detener(){
    if (!reproducir) {
        System.out.println("No se esta reproduciendo una canción. Reproduce una cancion de tu playlist");
    }
    System.out.println("----------------------------------------");
    System.out.println("Deteniendo la Cancion.");
    reproducir = false;
    System.out.println("----------------------------------------");

}

public void cerrarSesion(){
    setSesionIniciada( false);
}


private String listarPlaylists(){
    StringBuilder sb = new StringBuilder();
    for(int i =0; i<contadorPlaylists;i++){
        sb.append("[").append(i).append("]")
          .append(playlists[i].toString()).append("\n");
    }
    return sb.toString();
}

public void listarPlaylistUsusario(){
    var playlist = listarPlaylists();
    if(playlist==null || playlist.isEmpty()){
        System.out.println("Lista de Playlist vacia, agrega una playlist");
    } else{
        System.out.println(playlist);};
}




public int getContadorPlaylists() {
    return contadorPlaylists;
}









}
