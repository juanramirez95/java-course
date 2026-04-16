package modelo;

public class Playlist {
private String nombre;
private Cancion[] canciones;
private int contadorCanciones;
private int maxCanciones;

// Según UML
public Playlist(String nombre, int max) {
    this.nombre = nombre;
    this.maxCanciones = max;
    this.canciones = new Cancion[maxCanciones];
    this.contadorCanciones = 0;
}


public Playlist(String nombre) {
    this(nombre, 50);
}

public String getNombre() {
    return nombre;
}


public void agregarCancion(Cancion cancion){
    if (cancion == null || estaLlena()) return;
    canciones[contadorCanciones]=cancion;
    contadorCanciones++;
}


public void agregarCancion(String titulo, String artista, int duracion){
    agregarCancion(new Cancion(titulo, artista, duracion));
}


public void listarCanciones(){
    if (contadorCanciones == 0) {
        System.out.println("(Playlist vacía)");
        return;
    }
    for (int i = 0; i < contadorCanciones; i++) {
        System.out.printf("%d) %s%n", i, canciones[i]);
    }
}


public void reproducirCancion(int i){
    if (i < 0 || i >= contadorCanciones) return;
    Cancion c = canciones[i];
    if (c != null) c.reproducir();
}


public boolean estaLlena(){
    return contadorCanciones >= maxCanciones;
}


@Override
public String toString() {
    return  nombre;}




}
