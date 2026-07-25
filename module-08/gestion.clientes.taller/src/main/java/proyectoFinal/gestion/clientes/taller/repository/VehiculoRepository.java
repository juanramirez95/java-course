package proyectoFinal.gestion.clientes.taller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectoFinal.gestion.clientes.taller.model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    boolean existsByPlaca(String placa);

    List<Vehiculo> findByClienteId(Long clienteId);
}