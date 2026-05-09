package modelo;

public class Cancion extends Multimedia implements IReproducible {
    
    private String artista;

    public Cancion(String titulo, String artista, int duracionSegundos) {
        super(titulo, duracionSegundos);
        this.artista = artista;
    }

    
    
    @Override
    public void reproducir() {
        System.out.printf("Reproduciendo: %s (%d seg.), artista: %s%n",getTitulo().toUpperCase(),getDuracionSegundos(),getArtista().toUpperCase());
    }

    @Override
    public void detener() {
     System.out.println("Se detiene la canción: "+getTitulo());
    }

    @Override
    public String getInfo() {
        return "Cancion: "+getTitulo().toUpperCase()+" Artista: "+getArtista().toUpperCase()+" Duración en segundos: "+getDuracionSegundos();
    }

    public String getArtista() {
        return artista;
    }

    @Override
    public String toString() {
        return "Cancion: " + titulo.toUpperCase() + ", Artista: " + artista.toUpperCase() + ", Duración: " + duracionSegundos + " segundos";
    }

    
    

}
