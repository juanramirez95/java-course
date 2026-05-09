package shopSystem.Clase3.Service;

import java.util.List;
import java.util.Optional;

import shopSystem.Clase3.Model.Producto;

public interface IProductoService {
List<Producto> listarTodos();
Optional<Producto> buscarPorId(Long id);
List<Producto>listarDisponibles();
Producto guardar(Producto producto);
Optional<Producto> actualizar(Long id, Producto datosNuevos);
boolean eliminar(Long id);
}
