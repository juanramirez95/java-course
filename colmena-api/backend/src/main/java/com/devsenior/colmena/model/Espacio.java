package com.devsenior.colmena.model;

import jakarta.persistence.*;

@Entity
@Table(name = "espacios")
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 80)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoEspacio tipo;

    @Column(nullable = false)
    private int capacidad;

    // Valor por hora en pesos colombianos (sin decimales)
    @Column(nullable = false)
    private int precioHora;

    @Column(length = 500)
    private String descripcion;

    @Column(length = 300)
    private String imagenUrl;

    @Column(nullable = false)
    private boolean activo = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public TipoEspacio getTipo() { return tipo; }
    public void setTipo(TipoEspacio tipo) { this.tipo = tipo; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public int getPrecioHora() { return precioHora; }
    public void setPrecioHora(int precioHora) { this.precioHora = precioHora; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
