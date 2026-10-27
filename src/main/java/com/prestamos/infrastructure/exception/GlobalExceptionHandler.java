package com.prestamos.infrastructure.exception;


import com.prestamos.domain.model.Solicitud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String ERROR_INTERNO = "Error interno del servidor";

    @ExceptionHandler(SolicitudDuplicadaException.class)
    public ResponseEntity<ErrorResponse> handleSolicitudDuplicada(
            SolicitudDuplicadaException ex, WebRequest request) {
        
        logger.warn("Solicitud duplicada detectada para transacción: {}. Detalles: {}", 
            ex.getIdTransaccion(), ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .codigo("DUPLICATE_TRANSACTION")
            .mensaje("La solicitud ya fue procesada anteriormente")
            .detalle(ex.getMessage())
            .idTransaccion(ex.getIdTransaccion())
            .timestamp(Instant.now())
            .ruta(request.getDescription(false).replace("uri=", ""))
            .puedeReintentar(ex.puedeReintentar())
            .build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            errores.put(nombreCampo, mensajeError);
        });
        
        logger.warn("Error de validación en solicitud: {}", errores);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .codigo("VALIDATION_ERROR")
            .mensaje("Los datos de la solicitud no son válidos")
            .detalle(errores.toString())
            .timestamp(Instant.now())
            .ruta(request.getDescription(false).replace("uri=", ""))
            .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        
        logger.warn("Argumento inválido recibido: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .codigo("INVALID_ARGUMENT")
            .mensaje("Parámetro de solicitud inválido")
            .detalle(ex.getMessage())
            .timestamp(Instant.now())
            .ruta(request.getDescription(false).replace("uri=", ""))
            .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointer(
            NullPointerException ex, WebRequest request) {
        
        logger.error("NullPointerException en la solicitud: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .codigo("NULL_POINTER")
            .mensaje(ERROR_INTERNO)
            .detalle("Se recibió un valor nulo donde se esperaba un objeto")
            .timestamp(Instant.now())
            .ruta(request.getDescription(false).replace("uri=", ""))
            .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        
        logger.error("Excepción no controlada: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .codigo("INTERNAL_ERROR")
            .mensaje(ERROR_INTERNO)
            .detalle(ex.getMessage())
            .timestamp(Instant.now())
            .ruta(request.getDescription(false).replace("uri=", ""))
            .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public record ErrorResponse(
        String codigo,
        String mensaje,
        String detalle,
        UUID idTransaccion,
        Instant timestamp,
        String ruta,
        Boolean puedeReintentar
    ) {
        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }
    }

    public static class ErrorResponseBuilder {
        private String codigo;
        private String mensaje;
        private String detalle;
        private UUID idTransaccion;
        private Instant timestamp;
        private String ruta;
        private Boolean puedeReintentar;

        public ErrorResponseBuilder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public ErrorResponseBuilder mensaje(String mensaje) {
            this.mensaje = mensaje;
            return this;
        }

        public ErrorResponseBuilder detalle(String detalle) {
            this.detalle = detalle;
            return this;
        }

        public ErrorResponseBuilder idTransaccion(UUID idTransaccion) {
            this.idTransaccion = idTransaccion;
            return this;
        }

        public ErrorResponseBuilder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponseBuilder ruta(String ruta) {
            this.ruta = ruta;
            return this;
        }

        public ErrorResponseBuilder puedeReintentar(Boolean puedeReintentar) {
            this.puedeReintentar = puedeReintentar;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(codigo, mensaje, detalle, idTransaccion, timestamp, ruta, puedeReintentar);
        }
    }
}