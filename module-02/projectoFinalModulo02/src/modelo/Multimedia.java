package modelo;

public abstract class Multimedia {
protected String titulo;
protected int duracionSegundos;

//Constructor
public Multimedia(String titulo, int duracionSegundos) {
    this.titulo = titulo;
    this.duracionSegundos = duracionSegundos;
}
//Getters y setters

public String getTitulo() {
    return titulo;
}

public void setTitulo(String titulo) {
    this.titulo = titulo;
}

public int getDuracionSegundos() {
    return duracionSegundos;
}

public void setDuracionSegundos(int duracionSegundos) {
    this.duracionSegundos = duracionSegundos;
}

public abstract String getInfo();

// Compatibilidad con tu código previo (no rompe llamadas existentes)
public String getinfo() {
    return getInfo();
}

}
