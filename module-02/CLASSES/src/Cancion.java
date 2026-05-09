public class Cancion {
    private String titulo;
    private String artista;
    private Integer duracion;
    private Integer reproducciones;
    
    // constructores

    public Cancion(){

    }
    public Cancion(String titulo, String artista) {
        this.titulo = titulo;
        this.artista = artista;
        duracion = 0;
        reproducciones = 0;
    }


    public Cancion(String titulo, String artista, Integer duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        reproducciones =0;
        
    }

    // getters

    public String getTitulo() {
        return titulo;
    }
    public String getArtista() {
        return artista;
    }
    public Integer getDuracion() {
        return duracion;
    }
    public Integer getReproducciones() {
        return reproducciones;
    }
   // setters
    
   public void setTitulo(String titulo) {
        if (!titulo.isEmpty()) {
         this.titulo = titulo;   
        }
    }

    public void setArtista (String artista ){
        if (!artista.isEmpty()) {
            this.artista = artista;
        }
    }

    public void reproducir(){
        this.reproducciones++;
    }
    @Override
    public String toString() {
        return "Cancion [titulo=" + titulo +"\n" +  
               "Artista=" + artista + "\n" +
               "Duracion=" + duracion + "\n" +
               "Reproducciones=" + reproducciones + "]";
    }

    

    

    

    
    

    

}
