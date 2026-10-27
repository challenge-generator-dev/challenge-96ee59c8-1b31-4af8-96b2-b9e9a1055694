package com.prestamos.infrastructure.exception;

import java.util.UUID;

public class SolicitudDuplicadaException extends RuntimeException {

    private final UUID idTransaccion;
    private final String detalleAdicional;

    public SolicitudDuplicadaException(UUID idTransaccion) {
        super("Ya existe una solicitud registrada para la transacción: " + idTransaccion);
        this.idTransaccion = idTransaccion;
        this.detalleAdicional = null;
    }

    public SolicitudDuplicadaException(UUID idTransaccion, String mensajePersonalizado) {
        super(mensajePersonalizado);
        this.idTransaccion = idTransaccion;
        this.detalleAdicional = null;
    }

    public SolicitudDuplicadaException(UUID idTransaccion, String mensajePersonalizado, Throwable causa) {
        super(mensajePersonalizado, causa);
        this.idTransaccion = idTransaccion;
        this.detalleAdicional = null;
    }

    public SolicitudDuplicadaException(UUID idTransaccion, String mensajePersonalizado, String detalleAdicional) {
        super(mensajePersonalizado + ". Detalle: " + detalleAdicional);
        this.idTransaccion = idTransaccion;
        this.detalleAdicional = detalleAdicional;
    }

    public UUID getIdTransaccion() {
        return idTransaccion;
    }

    public String getDetalleAdicional() {
        return detalleAdicional;
    }

    public String getMensajeTecnico() {
        return String.format("Transacción duplicada: %s | Timestamp: %d", 
            idTransaccion != null ? idTransaccion.toString() : "N/A", 
            System.currentTimeMillis());
    }

    public boolean esReintento() {
        return detalleAdicional != null && detalleAdicional.contains("reintento");
    }

    public boolean puedeReintentar() {
        return esReintento();
    }

    @Override
    public String toString() {
        return "SolicitudDuplicadaException{" +
                "idTransaccion=" + idTransaccion +
                ", detalleAdicional='" + detalleAdicional + '\'' +
                ", mensaje='" + getMessage() + '\'' +
                '}';
    }
}