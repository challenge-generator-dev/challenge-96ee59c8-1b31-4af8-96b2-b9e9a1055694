package com.prestamos.infrastructure.adapters.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prestamos.application.service.EnrutarSolicitudService;
import com.prestamos.domain.model.Cliente;
import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.model.Solicitud.EstadoSolicitud;
import com.prestamos.infrastructure.exception.SolicitudDuplicadaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SolicitudController.class)
@DisplayName("Tests de integración para SolicitudController")
class SolicitudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EnrutarSolicitudService enrutarSolicitudService;

    private Cliente clienteValido;
    private Solicitud solicitudValida;
    private String clienteJson;
    private String solicitudJson;

    @BeforeEach
    void setUp() throws Exception {
        clienteValido = Cliente.crearNuevo("Roberto Sanchez", "roberto@test.com", 720, "web");
        UUID idTransaccion = UUID.randomUUID();
        solicitudValida = Solicitud.crearNueva(clienteValido, new BigDecimal("15000.00"), idTransaccion);

        clienteJson = "{\"nombre\":\"Roberto Sanchez\",\"email\":\"roberto@test.com\",\"puntajeCrediticio\":720,\"canalOrigen\":\"web\"}";
        solicitudJson = "{\"cliente\":{\"nombre\":\"Roberto Sanchez\",\"email\":\"roberto@test.com\",\"puntajeCrediticio\":720,\"canalOrigen\":\"web\"},\"monto\":15000.00,\"idTransaccion\":\"" + idTransaccion + "\"}";
    }

    @Nested
    @DisplayName("POST /api/solicitudes - Crear solicitud")
    class CrearSolicitud {

        @Test
        @DisplayName("Debe retornar 201 Created cuando la solicitud se procesa exitosamente")
        void debeRetornar201CuandoSeProcesaExitosamente() throws Exception {
            when(enrutarSolicitudService.procesarSolicitud(any(Solicitud.class))).thenReturn(solicitudValida);

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(solicitudJson))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.cliente.nombre").value("Roberto Sanchez"))
                    .andExpect(jsonPath("$.monto").value(15000.00));
        }

        @Test
        @DisplayName("Debe retornar 400 Bad Request cuando el JSON es inválido")
        void debeRetornar400CuandoJsonInvalido() throws Exception {
            String jsonInvalido = "{\"monto\":15000}";

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonInvalido))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 409 Conflict cuando la solicitud está duplicada")
        void debeRetornar409CuandoSolicitudDuplicada() throws Exception {
            when(enrutarSolicitudService.procesarSolicitud(any(Solicitud.class)))
                    .thenThrow(new SolicitudDuplicadaException("Transacción duplicada"));

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(solicitudJson))
                    .andExpect(status().isConflict());
        }

        @Test
        @DisplayName("Debe validar que el email del cliente tenga formato correcto")
        void debeValidarFormatoEmail() throws Exception {
            String jsonEmailInvalido = "{\"cliente\":{\"nombre\":\"Test\",\"email\":\"email-invalido\",\"puntajeCrediticio\":650,\"canalOrigen\":\"web\"},\"monto\":5000.00,\"idTransaccion\":\"" + UUID.randomUUID() + "\"}";

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonEmailInvalido))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe validar que el monto sea positivo")
        void debeValidarMontoPositivo() throws Exception {
            String jsonMontoNegativo = "{\"cliente\":{\"nombre\":\"Test\",\"email\":\"test@test.com\",\"puntajeCrediticio\":650,\"canalOrigen\":\"web\"},\"monto\":-1000.00,\"idTransaccion\":\"" + UUID.randomUUID() + "\"}";

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonMontoNegativo))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("GET /api/solicitudes/{id} - Obtener solicitud")
    class ObtenerSolicitud {

        @Test
        @DisplayName("Debe retornar 200 OK con la solicitud cuando existe")
        void debeRetornar200CuandoExiste() throws Exception {
            when(enrutarSolicitudService.buscarPorId(any())).thenReturn(java.util.Optional.of(solicitudValida));

            mockMvc.perform(get("/api/solicitudes/" + UUID.randomUUID()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.cliente.nombre").value("Roberto Sanchez"));
        }

        @Test
        @DisplayName("Debe retornar 404 Not Found cuando la solicitud no existe")
        void debeRetornar404CuandoNoExiste() throws Exception {
            when(enrutarSolicitudService.buscarPorId(any())).thenReturn(java.util.Optional.empty());

            mockMvc.perform(get("/api/solicitudes/" + UUID.randomUUID()))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("GET /api/solicitudes/existe/{idTransaccion} - Verificar existencia")
    class VerificarExistencia {

        @Test
        @DisplayName("Debe retornar 200 true cuando la transacción existe")
        void debeRetornarTrueCuandoExiste() throws Exception {
            UUID idTransaccion = UUID.randomUUID();
            when(enrutarSolicitudService.existeSolicitudPorTransaccion(idTransaccion)).thenReturn(true);

            mockMvc.perform(get("/api/solicitudes/existe/" + idTransaccion))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));
        }

        @Test
        @DisplayName("Debe retornar 200 false cuando la transacción no existe")
        void debeRetornarFalseCuandoNoExiste() throws Exception {
            UUID idTransaccion = UUID.randomUUID();
            when(enrutarSolicitudService.existeSolicitudPorTransaccion(idTransaccion)).thenReturn(false);

            mockMvc.perform(get("/api/solicitudes/existe/" + idTransaccion))
                    .andExpect(status().isOk())
                    .andExpect(content().string("false"));
        }
    }

    @Nested
    @DisplayName("Validación de headers")
    class ValidacionHeaders {

        @Test
        @DisplayName("Debe rechazar contenido que no sea JSON")
        void debeRechazarContentTypeNoJson() throws Exception {
            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.TEXT_PLAIN)
                    .content("texto plano"))
                    .andExpect(status().isUnsupportedMediaType());
        }

        @Test
        @DisplayName("Debe aceptar solicitudes sin body")
        void debeAceptarSolicitudSinBody() throws Exception {
            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(""))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Manejo de errores global")
    class ManejoErrores {

        @Test
        @DisplayName("Debe manejar excepción genérica retornando 500")
        void debeManejarExcepcionGenerica() throws Exception {
            when(enrutarSolicitudService.procesarSolicitud(any()))
                    .thenThrow(new RuntimeException("Error interno"));

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(solicitudJson))
                    .andExpect(status().isInternalServerError());
        }
    }
}