package shopSystem.Clase3.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shopSystem.Clase3.Model.DetallePedido;

public interface DetallePedidoReository extends JpaRepository<DetallePedido,Long>{
    List<DetallePedido> findByPedido(Long pedidoId);
}
