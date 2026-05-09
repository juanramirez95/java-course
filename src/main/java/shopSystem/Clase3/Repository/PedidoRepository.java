package shopSystem.Clase3.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shopSystem.Clase3.Model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
  List<Pedido> findByClienteId(Long clienteId);
    List<Pedido> findByEstado(String estado);
}
