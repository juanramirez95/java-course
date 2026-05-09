package shopSystem.Clase3.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
    int status,
    String mensaje,
    LocalDateTime timestamp
) {
    public static ErrorResponse of(int status, String mensaje) {
        return new ErrorResponse(status, mensaje, LocalDateTime.now());
    }
}