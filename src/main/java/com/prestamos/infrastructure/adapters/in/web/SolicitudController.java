package com.prestamos.infrastructure.adapters.in.web;


import com.prestamos.domain.model.Cliente;
import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.ports.in.EnrutarSolicitudUseCase;
import com.prestamos.infrastructure.exception.SolicitudDuplicadaException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/solicitudes")
@Tag(name = "Solicitudes", description = "API para gestión de solicitudes de préstamos")
public class SolicitudController {

    private static final Logger logger = LoggerFactory.getLogger(SolicitudController.class);
    private final EnrutarSolicitudUseCase enrutarSolicitudUseCase;

    public SolicitudController(EnrutarSolicitudUseCase enrutarSolicitudUseCase) {
        this.enrutarSolicitudUseCase = enrutarSolicitudUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear nueva solicitud", description = "Registra una nueva solicitud de préstamo en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "409", description = "Solicitud duplicada para la transacción"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<SolicitudResponse> crearSolicitud(
            @Valid @RequestBody SolicitudRequest request,
            @RequestHeader(value = "X-Request-ID", required = false) String requestId) {
        
        String trackingId = requestId != null ? requestId : UUID.randomUUID().toString();
        logger.info("Recibida solicitud de préstamo - TrackingID: {}, Cliente: {}, Monto: {}",
                trackingId, request.clienteId(), request.monto());

        try {
            Solicitud solicitud = Solicitud.crearNueva(
                    request.cliente(),
                    request.monto(),
                    request.idTransaccion()
            );

            Solicitud solicitudProcesada = enrutarSolicitudUseCase.procesarSolicitud(solicitud);
            logger.info("Solicitud procesada exitosamente - ID: {}, Estado: {}",
                    solicitudProcesada.id(), solicitudProcesada.estado());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(SolicitudResponse.fromDomain(solicitudProcesada));

        } catch (SolicitudDuplicadaException e) {
            logger.warn("Solicitud duplicada detectada - Transacción: {}", request.idTransaccion());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(SolicitudResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/{idTransaccion}")
    @Operation(summary = "Consultar solicitud por transacción", 
               description = "Verifica si existe una solicitud para el ID de transacción dado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<SolicitudResponse> consultarPorTransaccion(
            @Parameter(description = "ID de transacción de la solicitud")
            @PathVariable UUID idTransaccion) {
        
        logger.debug("Consultando solicitud por transacción: {}", idTransaccion);
        
        boolean existe = enrutarSolicitudUseCase.existeSolicitudPorTransaccion(idTransaccion);
        
        if (existe) {
            return ResponseEntity.ok(SolicitudResponse.found(idTransaccion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Verifica el estado del servicio")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    public record SolicitudRequest(
            @NotNull(message = "El cliente es requerido")
            com.prestamos.domain.model.Cliente cliente,
            @NotNull(message = "El monto es requerido")
            @Parameter(description = "Monto del préstamo solicitado")
            BigDecimal monto,
            @NotNull(message = "El ID de transacción es requerido")
            @Parameter(description = "Identificador único de la transacción")
            UUID idTransaccion
    ) {}

    public record SolicitudResponse(
            UUID id,
            String estado,
            BigDecimal monto,
            String mensaje,
            boolean error
    ) {
        public static SolicitudResponse fromDomain(Solicitud solicitud) {
            return new SolicitudResponse(
                    solicitud.id(),
                    solicitud.estado().name(),
                    solicitud.monto(),
                    null,
                    false
            );
        }

        public static SolicitudResponse error(String mensaje) {
            return new SolicitudResponse(null, null, null, mensaje, true);
        }

        public static SolicitudResponse found(UUID idTransaccion) {
            return new SolicitudResponse(
                    idTransaccion,
                    "ENCONTRADA",
                    null,
                    "La solicitud existe en el sistema",
                    false
            );
        }
    }
}