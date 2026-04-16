package modelo;

public class BibliotecaMusical {

private Cancion[] catalogo;
private int contadorCatalogo;
private int maxCatalogo;
//Constructor 

public BibliotecaMusical(){

}
public BibliotecaMusical(int maxCanciones) { // Las canciones tienen una relación por composición con la biblioteca musical, por eso se crean desde la biblioteca y es la unica que puede crear canciones y agregarlas a la biblioteca
    this.maxCatalogo=maxCanciones;
    this.catalogo = new Cancion[maxCatalogo];
    this.contadorCatalogo = 0;
    catalogo[contadorCatalogo++] = new Cancion("Amor mio","Ricardo Montaner",180);
    catalogo[contadorCatalogo++] = new Cancion("felina","Hector y tito",184);
    catalogo[contadorCatalogo++] = new Cancion("Amor"," Montaner",180);
    catalogo[contadorCatalogo++] = new Cancion("Jardin con enanitos","Melendi",220);

}



//metodo getter
public int getContadorCatalogo() {
    return contadorCatalogo;
}

public void agregarCancion(Cancion cancion){
    if (contadorCatalogo<maxCatalogo) {
        catalogo[contadorCatalogo]=cancion;
        contadorCatalogo++;
    }
}

public void agregarCancion(String titulo, String artista, int duracionSegundos){
    if (contadorCatalogo<maxCatalogo) {
        catalogo[contadorCatalogo]= new Cancion(titulo, artista, duracionSegundos);
        contadorCatalogo++;
    }
}


public void listarCatalogo() {
    System.out.print(listarCanciones());
}

public String listarCanciones(){
    StringBuilder sb = new StringBuilder();
    for(int i =0; i<contadorCatalogo; i++){
        sb.append("[").append(i).append("]")
          .append(catalogo[i].toString()).append("\n");
    }
    return sb.toString();
}


public Cancion buscarPorNombre(String nombre) {
    if (nombre == null) return null;

    String criterio = nombre.trim();
    if (criterio.isEmpty()) return null;
    criterio = criterio.toLowerCase();

    for (int i = 0; i < contadorCatalogo; i++) {
        Cancion c = catalogo[i];
        if (c == null) continue;

        String t = c.getTitulo();
        if (t != null && t.toLowerCase().contains(criterio)) {
            return c;
        }
    }
    return null;
}

// Compatibilidad con tu código previo
public Cancion buscarCancion(String nombreCancion){
    return buscarPorNombre(nombreCancion);
}

public Cancion getCancionPorIndice(int index){
    if(index<0||index>=contadorCatalogo)return null;
    return catalogo[index];
}




}
