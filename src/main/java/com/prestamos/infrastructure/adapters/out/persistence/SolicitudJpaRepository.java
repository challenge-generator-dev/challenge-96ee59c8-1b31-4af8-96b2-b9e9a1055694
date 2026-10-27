package com.prestamos.infrastructure.adapters.out.persistence;

import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.model.Solicitud.EstadoSolicitud;
import com.prestamos.domain.ports.out.SolicitudRepository;
import com.prestamos.infrastructure.adapters.out.persistence.entity.SolicitudEntity;
import com.prestamos.infrastructure.adapters.out.persistence.entity.SolicitudEntityMapper;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class SolicitudJpaRepository implements SolicitudRepository {

    private final SpringDataSolicitudRepository springDataRepository;
    private final SolicitudEntityMapper mapper;

    public SolicitudJpaRepository(SpringDataSolicitudRepository springDataRepository,
                                   SolicitudEntityMapper mapper) {
        this.springDataRepository = springDataRepository;
        this.mapper = mapper;
    }

    @Override
    public Solicitud guardar(Solicitud solicitud) {
        SolicitudEntity entity = mapper.toEntity(solicitud);
        SolicitudEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Solicitud> buscarPorId(UUID id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Solicitud> buscarPorIdTransaccion(UUID idTransaccion) {
        return springDataRepository.findByIdTransaccion(idTransaccion)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existePorIdTransaccion(UUID idTransaccion) {
        return springDataRepository.existsByIdTransaccion(idTransaccion);
    }
}