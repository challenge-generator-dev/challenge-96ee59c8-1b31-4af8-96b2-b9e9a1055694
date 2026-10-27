package com.prestamos.domain.ports.in;

import com.prestamos.domain.model.Solicitud;
import java.util.UUID;

/**
 * Puerto de entrada que define el contrato para el caso de uso de enrutamiento de solicitudes.
 * Este caso de uso se encarga de procesar las solicitudes de préstamo y determinar
 * el servicio correspondiente al que deben ser dirigidas.
 */
public interface EnrutarSolicitudUseCase {

    /**
     * Procesa una solicitud de préstamo y la enruta al servicio correspondiente.
     * @param solicitud La solicitud a procesar, con todos sus campos válidos.
     * @return La solicitud procesada con su estado actualizado.
     * @throws IllegalArgumentException si la solicitud no es válida.
     */
    Solicitud procesarSolicitud(Solicitud solicitud);

    /**
     * Verifica si una solicitud con el mismo ID de transacción ya ha sido procesada.
     * @param idTransaccion ID de la transacción a verificar.
     * @return true si ya existe una solicitud con ese ID de transacción, false en caso contrario.
     */
    boolean existeSolicitudPorTransaccion(UUID idTransaccion);
}