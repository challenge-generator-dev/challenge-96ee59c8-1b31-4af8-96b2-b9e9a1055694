package com.prestamos.application.service;

import com.prestamos.domain.model.Cliente;
import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.model.Solicitud.EstadoSolicitud;
import com.prestamos.domain.ports.in.EnrutarSolicitudUseCase;
import com.prestamos.domain.ports.out.SolicitudRepository;
import com.prestamos.infrastructure.exception.SolicitudDuplicadaException;
import java.math.BigDecimal;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrutarSolicitudService implements EnrutarSolicitudUseCase {

    private static final Logger log = LoggerFactory.getLogger(EnrutarSolicitudService.class);
    
    private static final BigDecimal MONTO_MINIMO_PREFERENTE = new BigDecimal("10000.00");
    private static final int PUNTAJE_MINIMO_PREFERENTE = 700;
    private static final BigDecimal MONTO_MAXIMO = new BigDecimal("50000.00");
    private static final BigDecimal MONTO_MINIMO = new BigDecimal("1000.00");

    private final SolicitudRepository solicitudRepository;

    public EnrutarSolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    @Transactional
    public Solicitud procesarSolicitud(Solicitud solicitud) {
        log.info("Iniciando procesamiento de solicitud para cliente: {}, monto: {}",
                solicitud.cliente().nombre(), solicitud.monto());
        
        solicitud.validar();
        
        if (existeSolicitudPorTransaccion(solicitud.idTransaccion())) {
            log.warn("Solicitud duplicada detectada para transacción: {}", solicitud.idTransaccion());
            throw new SolicitudDuplicadaException(
                    "Ya existe una solicitud para la transacción: " + solicitud.idTransaccion());
        }
        
        Solicitud solicitudEnriquecida = enrutarSolicitud(solicitud);
        
        Solicitud solicitudGuardada = solicitudRepository.guardar(solicitudEnriquecida);
        
        log.info("Solicitud procesada y guardada con estado: {}", solicitudGuardada.estado());
        
        return solicitudGuardada;
    }

    @Override
    public boolean existeSolicitudPorTransaccion(UUID idTransaccion) {
        return solicitudRepository.existePorIdTransaccion(idTransaccion);
    }

    private Solicitud enrutarSolicitud(Solicitud solicitud) {
        Cliente cliente = solicitud.cliente();
        BigDecimal monto = solicitud.monto();
        
        EstadoSolicitud estadoDeterminado = determinarEstado(cliente, monto);
        
        String destinoEnrutamiento = determinarDestino(cliente, monto, estadoDeterminado);
        
        log.debug("Solicitud enrutada a: {} con estado: {}", destinoEnrutamiento, estadoDeterminado);
        
        return solicitud.actualizarEstado(estadoDeterminado);
    }

    private EstadoSolicitud determinarEstado(Cliente cliente, BigDecimal monto) {
        if (monto.compareTo(MONTO_MINIMO) < 0) {
            log.info("Monto {} menor al mínimo {}, solicitud rechazada", monto, MONTO_MINIMO);
            return EstadoSolicitud.RECHAZADA;
        }
        
        if (monto.compareTo(MONTO_MAXIMO) > 0) {
            log.info("Monto {} mayor al máximo {}, solicitud requiere aprobación manual", monto, MONTO_MAXIMO);
            return EstadoSolicitud.PENDIENTE_APROBACION;
        }
        
        if (esPreferente(cliente, monto)) {
            log.info("Cliente {} cumple criterios preferentes, aprobación automática", cliente.nombre());
            return EstadoSolicitud.APROBADA;
        }
        
        log.info("Solicitud estándar enviada a revisión");
        return EstadoSolicitud.EN_REVISION;
    }

    private boolean esPreferente(Cliente cliente, BigDecimal monto) {
        return cliente.puntajeCrediticio() >= PUNTAJE_MINIMO_PREFERENTE 
                && monto.compareTo(MONTO_MINIMO_PREFERENTE) >= 0;
    }

    private String determinarDestino(Cliente cliente, BigDecimal monto, EstadoSolicitud estado) {
        return switch (estado) {
            case APROBADA -> "servicio-aprobacion-automatica";
            case RECHAZADA -> "servicio-rechazo";
            case PENDIENTE_APROBACION -> "comite-aprobacion";
            case EN_REVISION -> "servicio-revision-manual";
            case CANCELADA -> "servicio-cancelacion";
        };
    }
}