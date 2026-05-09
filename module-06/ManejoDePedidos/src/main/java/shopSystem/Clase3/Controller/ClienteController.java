package shopSystem.Clase3.Controller;


/*
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import shopSystem.Clase3.Model.Cliente;
import shopSystem.Clase3.Service.ICLienteService;
import shopSystem.Clase3.exception.ClienteNoEncontrado;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ICLienteService clienteService;

    public ClienteController(ICLienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Este es el codigo que hice guiandome conlos ejercicios realiados durante las clases. 
    
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos(){
       try {
         List<Cliente> clientes = clienteService.listarTodos();
        if(clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
       } catch (Exception e) {
        return ResponseEntity.status(500).build();
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        try {
            return clienteService.buscarPorId(id)
                                 .map(ResponseEntity::ok)
                                 .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@Valid @RequestBody Cliente cliente){
        try {
            Cliente clienteCreado = clienteService.guardar(cliente);
            return ResponseEntity.status(201).body(clienteCreado);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        try {
            cliente.setIdCliente(id);
            clienteService.actualizar(id, cliente);
            return ResponseEntity.status(201).build();
        } catch (ClienteNoEncontrado e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        try {
            boolean eliminado = clienteService.eliminar(id);
            if(!eliminado){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body("Cliente con id:" + id +", no encontrado.");}
                return ResponseEntity.ok("Cliente con id: " + id + " eliminado de forma correcta");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error al eliminar el cliente");
        }
}
    }*/

    /* Este es el codigo refactorizado por la IA (Claude), y este es es prompt que utilicé: 

        "Eres un DevSernior en Java con 20 años de experiencia. Estas evaluando la clase ClienteController de una aplicación de manejo de pedidos. 
        Evalúala, refactorízala teniendo en cuenta los principios SOLID. Que cada controlador genere lo códigos HTTP correctos. Busca que haya 
        congruencia con los nombres de las variables, el uso de validaciones correctas. Si tienes dudas, pregúntame antes de desarrollar el código"
        


    La refactorización  me permitio ver la manera de poder darle manejo a los errores con el GlobalHandlerException, de esa manera no se tiente que 
    poner bloques de try-catch en cada método
    */


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import shopSystem.Clase3.Model.Cliente;
import shopSystem.Clase3.Service.ICLienteService;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ICLienteService clienteService;

    public ClienteController(ICLienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.listarTodos();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente) {
        Cliente clienteCreado = clienteService.guardar(cliente);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteCreado.getIdCliente())
                .toUri();
        return ResponseEntity.created(ubicacion).body(clienteCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Cliente cliente) {
        cliente.setIdCliente(id);
        clienteService.actualizar(id, cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
    

    


