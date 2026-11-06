package com.devsenior.colmena.exception;

import com.devsenior.colmena.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

// Cada error responde con su codigo HTTP honesto y el mismo formato: { codigo, mensaje, fecha }
@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    // 400: fallo una validacion de @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validacion(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Hay datos inválidos en la petición");
        return responder(HttpStatus.BAD_REQUEST, mensaje);
    }

    // 400: el JSON llego mal armado (un tipo de espacio o una fecha que no existe)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> jsonInvalido(HttpMessageNotReadableException ex) {
        return responder(HttpStatus.BAD_REQUEST, "El cuerpo de la petición tiene un formato inválido");
    }

    // 400: un parametro de la URL con formato equivocado (ej: ?fecha=mañana)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> parametroInvalido(MethodArgumentTypeMismatchException ex) {
        return responder(HttpStatus.BAD_REQUEST, "El parámetro '" + ex.getName() + "' tiene un formato inválido");
    }

    // 400: datos que no tienen sentido para el negocio
    @ExceptionHandler(DatoInvalidoException.class)
    public ResponseEntity<ErrorResponse> datoInvalido(DatoInvalidoException ex) {
        return responder(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // 401: la cuenta existe pero la administracion la desactivo
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponse> cuentaDesactivada(DisabledException ex) {
        return responder(HttpStatus.UNAUTHORIZED, "Tu cuenta está desactivada. Comunícate con la administración.");
    }

    // 401: email o contraseña incorrectos
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> credenciales(AuthenticationException ex) {
        return responder(HttpStatus.UNAUTHORIZED, "Email o contraseña incorrectos");
    }

    // 403: autenticado, pero sin el permiso que pide esta accion
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accesoDenegado(AccessDeniedException ex) {
        return responder(HttpStatus.FORBIDDEN, "No tienes permiso para realizar esta acción");
    }

    // 404: recurso no encontrado
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> noEncontrado(RecursoNoEncontradoException ex) {
        return responder(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // 409: la peticion es valida, pero el negocio no la permite
    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<ErrorResponse> reglaNegocio(ReglaNegocioException ex) {
        return responder(HttpStatus.CONFLICT, ex.getMessage());
    }

    // 409: dato que la base de datos exige unico (email, nombre de espacio)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> datoDuplicado(DataIntegrityViolationException ex) {
        return responder(HttpStatus.CONFLICT, "Ya existe un registro con esos datos");
    }

    private ResponseEntity<ErrorResponse> responder(HttpStatus estado, String mensaje) {
        return ResponseEntity.status(estado)
                .body(new ErrorResponse(estado.value(), mensaje, LocalDateTime.now()));
    }
}
