package proyectoFinal.gestion.clientes.taller.exception;

public class RecursoDuplicadoException extends RuntimeException {

    public RecursoDuplicadoException() {
        super();
    }

    public RecursoDuplicadoException(String message) {
        super(message);
    }

    public RecursoDuplicadoException(String message, Throwable cause) {
        super(message, cause);
    }
}
