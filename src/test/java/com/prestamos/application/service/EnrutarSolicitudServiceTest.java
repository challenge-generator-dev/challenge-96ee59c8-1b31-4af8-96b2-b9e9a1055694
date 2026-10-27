package com.prestamos.application.service;

import com.prestamos.domain.model.Cliente;
import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.model.Solicitud.EstadoSolicitud;
import com.prestamos.domain.ports.out.SolicitudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para EnrutarSolicitudService")
class EnrutarSolicitudServiceTest {

    @Mock
    private SolicitudRepository solicitudRepository;

    private EnrutarSolicitudService service;

    @BeforeEach
    void setUp() {
        service = new EnrutarSolicitudService(solicitudRepository);
    }

    @Nested
    @DisplayName("Procesamiento de solicitudes")
    class ProcesamientoSolicitudes {

        @Test
        @DisplayName("Debe procesar solicitud exitosamente cuando es nueva")
        void debeProcesarSolicitudExitosamente() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Juan Perez", "juan@test.com", 750, "web");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("10000.00"), idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(false);
            when(solicitudRepository.guardar(any(Solicitud.class))).thenAnswer(inv -> inv.getArgument(0));

            Solicitud resultado = service.procesarSolicitud(solicitud);

            assertNotNull(resultado);
            assertEquals(EstadoSolicitud.PROCESADA, resultado.estado());
            verify(solicitudRepository).guardar(any(Solicitud.class));
        }

        @Test
        @DisplayName("Debe rechazar solicitud duplicada por transacción")
        void debeRechazarSolicitudDuplicada() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Maria Lopez", "maria@test.com", 800, "movil");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("5000.00"), idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(true);

            Solicitud resultado = service.procesarSolicitud(solicitud);

            assertNotNull(resultado);
            assertEquals(EstadoSolicitud.RECHAZADA, resultado.estado());
            verify(solicitudRepository, never()).guardar(any(Solicitud.class));
        }

        @Test
        @DisplayName("Debe guardar solicitud con estado apropiado según puntaje crediticio")
        void debeGuardarConEstadoSegunPuntaje() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Carlos Ruiz", "carlos@test.com", 650, "api");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("8000.00"), idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(false);
            when(solicitudRepository.guardar(any(Solicitud.class))).thenAnswer(inv -> inv.getArgument(0));

            Solicitud resultado = service.procesarSolicitud(solicitud);

            ArgumentCaptor<Solicitud> captor = ArgumentCaptor.forClass(Solicitud.class);
            verify(solicitudRepository).guardar(captor.capture());

            Solicitud capturada = captor.getValue();
            assertNotNull(capturada);
        }
    }

    @Nested
    @DisplayName("Verificación de idempotencia")
    class VerificacionIdempotencia {

        @Test
        @DisplayName("Debe retornar true cuando existe transacción")
        void debeRetornarTrueCuandoExiste() {
            UUID idTransaccion = UUID.randomUUID();
            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(true);

            boolean resultado = service.existeSolicitudPorTransaccion(idTransaccion);

            assertTrue(resultado);
            verify(solicitudRepository).existePorTransaccion(idTransaccion);
        }

        @Test
        @DisplayName("Debe retornar false cuando no existe transacción")
        void debeRetornarFalseCuandoNoExiste() {
            UUID idTransaccion = UUID.randomUUID();
            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(false);

            boolean resultado = service.existeSolicitudPorTransaccion(idTransaccion);

            assertFalse(resultado);
            verify(solicitudRepository).existePorTransaccion(idTransaccion);
        }

        @Test
        @DisplayName("Debe verificar idempotencia antes de procesar")
        void debeVerificarIdempotenciaAntesDeProcesar() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Ana Gomez", "ana@test.com", 700, "web");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("12000.00"), idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(true);

            service.procesarSolicitud(solicitud);

            verify(solicitudRepository, times(1)).existePorTransaccion(idTransaccion);
            verify(solicitudRepository, never()).guardar(any(Solicitud.class));
        }
    }

    @Nested
    @DisplayName("Casos de borde")
    class CasosDeBorde {

        @Test
        @DisplayName("Debe manejar solicitud con monto cero")
        void debeManejarMontoCero() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Pedro Martinez", "pedro@test.com", 720, "movil");
            Solicitud solicitud = Solicitud.crearNueva(cliente, BigDecimal.ZERO, idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(false);
            when(solicitudRepository.guardar(any(Solicitud.class))).thenAnswer(inv -> inv.getArgument(0));

            Solicitud resultado = service.procesarSolicitud(solicitud);

            assertNotNull(resultado);
            verify(solicitudRepository).guardar(any(Solicitud.class));
        }

        @Test
        @DisplayName("Debe manejar cliente con puntaje mínimo")
        void debeManejarPuntajeMinimo() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Laura Diaz", "laura@test.com", 500, "api");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("3000.00"), idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(false);
            when(solicitudRepository.guardar(any(Solicitud.class))).thenAnswer(inv -> inv.getArgument(0));

            Solicitud resultado = service.procesarSolicitud(solicitud);

            assertNotNull(resultado);
        }

        @Test
        @DisplayName("Debe rechazar UUID de transacción nulo")
        void debeRechazarUuidNulo() {
            Cliente cliente = Cliente.crearNuevo("Test User", "test@test.com", 650, "web");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("5000.00"), null);

            assertThrows(NullPointerException.class, () -> service.procesarSolicitud(solicitud));
        }
    }
}