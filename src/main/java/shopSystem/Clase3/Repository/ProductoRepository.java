package shopSystem.Clase3.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopSystem.Clase3.Model.Producto;
@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long>{
List<Producto>findByNombre(String nombre);
    List<Producto>findByStock(Integer stock);
}
