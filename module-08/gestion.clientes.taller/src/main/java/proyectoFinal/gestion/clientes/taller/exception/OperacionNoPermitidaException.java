package proyectoFinal.gestion.clientes.taller.exception;

public class OperacionNoPermitidaException extends RuntimeException {

    public OperacionNoPermitidaException() {
        super();
    }

    public OperacionNoPermitidaException(String message) {
        super(message);
    }

    public OperacionNoPermitidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
