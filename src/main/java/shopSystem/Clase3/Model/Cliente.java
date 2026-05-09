package shopSystem.Clase3.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Clientes")
public class Cliente {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message = "Nombre obligatorio")
@Size(min = 2,max = 100,message = "El nombre debe de estar entre 2 a 100 carácteres")
@Column(name = "nombre",nullable = false, length = 100)
private String nombre;

@NotBlank(message = "El email es necesario")
@Email(message = "no tiene el formato valido")
@Column(name = "email",nullable = false, unique = true)
private String email;


@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
@JsonIgnore
private List<Pedido> pedidos = new ArrayList<>();


public Cliente() {}
public Cliente(Long id,String nombre,String email) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
}


public Long getIdCliente() {
    return id;
}


public void setIdCliente(Long id) {
    this.id = id;
}


public String getNombre() {
    return nombre;
}


public void setNombre(String nombre) {
    this.nombre = nombre;
}


public String getEmail() {
    return email;
}


public void setEmail(String email) {
    this.email = email;
}


public List<Pedido> getPedidos() {
    return pedidos;
}


public void setPedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
}








}
