package proyectoFinal.gestion.clientes.taller.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import proyectoFinal.gestion.clientes.taller.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCedula(String cedula);
}