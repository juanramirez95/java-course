package shopSystem.Clase3.Service;

import java.util.List;
import java.util.Optional;

import shopSystem.Clase3.Model.Cliente;

public interface ICLienteService {

    List<Cliente> listarTodos();
    Optional<Cliente> buscarPorId(Long id);
    Cliente guardar(Cliente cliente);
    Optional<Cliente> actualizar(Long id, Cliente datosNuevos);
    boolean eliminar(Long id);

}
