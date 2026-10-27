package com.prestamos.domain.ports.out;

import com.prestamos.domain.model.Solicitud;
import java.util.Optional;
import java.util.UUID;

public interface SolicitudRepository {
    
    Solicitud guardar(Solicitud solicitud);
    
    Optional<Solicitud> buscarPorId(UUID id);
    
    Optional<Solicitud> buscarPorIdTransaccion(UUID idTransaccion);
    
    boolean existePorIdTransaccion(UUID idTransaccion);
}