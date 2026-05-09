package shopSystem.Clase3.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopSystem.Clase3.Model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente,Long>{
    Optional<Cliente> findByEmail(String email);

}
