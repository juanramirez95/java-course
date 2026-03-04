package modelo;

public class Cancion extends Multimedia implements IReproducible {
    
    private String artista;

    public Cancion(String titulo, int duracionSegundos, String artista) {
        super(titulo, duracionSegundos);
        this.artista = artista;
    }

    @Override
    public void reproducir() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reproducir'");
    }

    @Override
    public void detener() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'detener'");
    }

    @Override
    public String getinfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getinfo'");
    }
    

}
