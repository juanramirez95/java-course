package shopSystem.Clase3.exception;
/**
 * 
 */
public class ClienteNoEncontrado extends RuntimeException {
    private final Long idCliente;

    public ClienteNoEncontrado(Long idCliente){
        super("Cliente con id "+idCliente+" no encontrado.");
        this.idCliente = idCliente;
    }

    public Long getIdCliente(){
        return idCliente;
    }
}
