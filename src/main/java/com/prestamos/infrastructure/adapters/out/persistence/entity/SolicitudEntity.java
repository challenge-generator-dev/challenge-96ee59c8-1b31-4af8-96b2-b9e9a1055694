package com.prestamos.infrastructure.adapters.out.persistence.entity;

import com.prestamos.domain.model.Cliente;
import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.model.Solicitud.EstadoSolicitud;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "solicitudes", indexes = {
    @Index(name = "idx_solicitud_transaccion", columnList = "id_transaccion"),
    @Index(name = "idx_solicitud_estado", columnList = "estado"),
    @Index(name = "idx_solicitud_fecha", columnList = "fecha_creacion")
})
public class SolicitudEntity {

    private static final Logger logger = LoggerFactory.getLogger(SolicitudEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "id_transaccion", nullable = false, unique = true)
    private UUID idTransaccion;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    @Column(name = "monto", nullable = false, precision = 19, scale = 4)
    private BigDecimal monto;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "nombre", column = @Column(name = "cliente_nombre")),
        @AttributeOverride(name = "email", column = @Column(name = "cliente_email")),
        @AttributeOverride(name = "puntajeCrediticio", column = @Column(name = "cliente_puntaje")),
        @AttributeOverride(name = "canalOrigen", column = @Column(name = "cliente_canal"))
    })
    private ClienteEmbeddable cliente;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "version")
    @Version
    private Long version;

    public SolicitudEntity() {}

    public static SolicitudEntity fromDomain(Solicitud solicitud) {
        logger.debug("Convirtiendo Solicitud a SolicitudEntity - ID: {}", solicitud.id());
        
        SolicitudEntity entity = new SolicitudEntity();
        entity.id = solicitud.id();
        entity.idTransaccion = solicitud.idTransaccion();
        entity.estado = solicitud.estado();
        entity.monto = solicitud.monto();
        entity.cliente = ClienteEmbeddable.fromDomain(solicitud.cliente());
        entity.fechaCreacion = solicitud.fechaCreacion();
        entity.fechaActualizacion = solicitud.fechaActualizacion();
        
        return entity;
    }

    public Solicitud toDomain() {
        logger.debug("Convirtiendo SolicitudEntity a dominio - ID Transacción: {}", this.idTransaccion);
        
        Cliente cliente = this.cliente.toDomain();
        
        return new Solicitud(
                this.id,
                this.idTransaccion,
                this.estado,
                this.monto,
                cliente,
                this.fechaCreacion,
                this.fechaActualizacion
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(UUID idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public ClienteEmbeddable getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEmbeddable cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Embeddable
    public static class ClienteEmbeddable {
        
        @Column(name = "cliente_nombre")
        private String nombre;
        
        @Column(name = "cliente_email")
        private String email;
        
        @Column(name = "cliente_puntaje")
        private int puntajeCrediticio;
        
        @Column(name = "cliente_canal")
        private String canalOrigen;

        public ClienteEmbeddable() {}

        public static ClienteEmbeddable fromDomain(Cliente cliente) {
            ClienteEmbeddable embeddable = new ClienteEmbeddable();
            embeddable.nombre = cliente.nombre();
            embeddable.email = cliente.email();
            embeddable.puntajeCrediticio = cliente.puntajeCrediticio();
            embeddable.canalOrigen = cliente.canalOrigen();
            return embeddable;
        }

        public Cliente toDomain() {
            return new Cliente(
                    this.nombre,
                    this.email,
                    this.puntajeCrediticio,
                    this.canalOrigen
            );
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getPuntajeCrediticio() {
            return puntajeCrediticio;
        }

        public void setPuntajeCrediticio(int puntajeCrediticio) {
            this.puntajeCrediticio = puntajeCrediticio;
        }

        public String getCanalOrigen() {
            return canalOrigen;
        }

        public void setCanalOrigen(String canalOrigen) {
            this.canalOrigen = canalOrigen;
        }
    }
}