package com.prestamos.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidad de dominio que representa una solicitud de préstamo.
 * Cada solicitud tiene un estado, monto y un cliente asociado.
 */
public record Solicitud(
    @NotNull(message = "El ID de la solicitud no puede ser nulo")
    UUID id,

    @NotNull(message = "El ID de transacción no puede ser nulo")
    UUID idTransaccion,

    @NotNull(message = "El cliente asociado no puede ser nulo")
    Cliente cliente,

    @Positive(message = "El monto solicitado debe ser positivo")
    BigDecimal monto,

    @NotNull(message = "El estado de la solicitud no puede ser nulo")
    EstadoSolicitud estado,

    @NotNull(message = "La fecha de creación no puede ser nula")
    LocalDateTime fechaCreacion,

    LocalDateTime fechaActualizacion) {

    /**
     * Enum que representa los posibles estados de una solicitud.
     */
    public enum EstadoSolicitud {
        RECIBIDA,
        EN_PROCESO,
        APROBADA,
        RECHAZADA,
        FINALIZADA
    }

    /**
     * Valida que la solicitud tenga todos los campos requeridos.
     * @throws IllegalArgumentException si algún campo obligatorio es inválido.
     */
    public void validar() {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la solicitud no puede ser nulo");
        }
        if (idTransaccion == null) {
            throw new IllegalArgumentException("El ID de transacción no puede ser nulo");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente asociado no puede ser nulo");
        }
        cliente.validar();
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto solicitado debe ser positivo");
        }
        if (estado == null) {
            throw new IllegalArgumentException("El estado de la solicitud no puede ser nulo");
        }
        if (fechaCreacion == null) {
            throw new IllegalArgumentException("La fecha de creación no puede ser nula");
        }
    }

    /**
     * Crea una nueva solicitud con los datos proporcionados.
     * @param cliente Cliente asociado a la solicitud.
     * @param monto Monto solicitado.
     * @param idTransaccion ID único de la transacción.
     * @return Una nueva instancia de Solicitud con estado RECIBIDA y fechas inicializadas.
     */
    public static Solicitud crearNueva(Cliente cliente, BigDecimal monto, UUID idTransaccion) {
        return new Solicitud(
            UUID.randomUUID(),
            idTransaccion,
            cliente,
            monto,
            EstadoSolicitud.RECIBIDA,
            LocalDateTime.now(),
            null
        );
    }

    /**
     * Actualiza el estado de la solicitud.
     * @param nuevoEstado Nuevo estado de la solicitud.
     * @return Una nueva instancia de Solicitud con el estado actualizado.
     */
    public Solicitud actualizarEstado(EstadoSolicitud nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }
        return new Solicitud(
            this.id,
            this.idTransaccion,
            this.cliente,
            this.monto,
            nuevoEstado,
            this.fechaCreacion,
            LocalDateTime.now()
        );
    }
}