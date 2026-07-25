package proyectoFinal.gestion.clientes.taller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectoFinal.gestion.clientes.taller.model.OrdenServicio;
import proyectoFinal.gestion.clientes.taller.model.enums.EstadoOrden;

public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Long> {

    List<OrdenServicio> findByVehiculoId(Long vehiculoId);

    List<OrdenServicio> findByEstado(EstadoOrden estado);
}