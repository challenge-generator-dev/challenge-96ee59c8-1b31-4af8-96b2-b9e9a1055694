package com.prestamos.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

/**
 * Entidad de dominio que representa a un cliente con sus atributos esenciales.
 * Los clientes son identificados por un UUID único y deben tener información válida.
 */
public record Cliente(
    @NotNull(message = "El ID del cliente no puede ser nulo")
    UUID id,

    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    String nombre,

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    String email,

    @Positive(message = "El puntaje crediticio debe ser un valor positivo")
    int puntajeCrediticio,

    @NotBlank(message = "El canal de origen no puede estar vacío")
    String canalOrigen) {

    /**
     * Valida que el cliente tenga todos los campos requeridos para procesar una solicitud.
     * @throws IllegalArgumentException si algún campo obligatorio es inválido.
     */
    public void validar() {
        if (id == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico no puede estar vacío");
        }
        if (puntajeCrediticio <= 0) {
            throw new IllegalArgumentException("El puntaje crediticio debe ser un valor positivo");
        }
        if (canalOrigen == null || canalOrigen.trim().isEmpty()) {
            throw new IllegalArgumentException("El canal de origen no puede estar vacío");
        }
    }

    /**
     * Crea un nuevo cliente con los datos proporcionados.
     * @param nombre Nombre completo del cliente.
     * @param email Correo electrónico válido.
     * @param puntajeCrediticio Puntaje crediticio positivo.
     * @param canalOrigen Canal por el que se originó la solicitud.
     * @return Una nueva instancia de Cliente con un ID generado.
     */
    public static Cliente crearNuevo(String nombre, String email, int puntajeCrediticio, String canalOrigen) {
        return new Cliente(
            UUID.randomUUID(),
            nombre,
            email,
            puntajeCrediticio,
            canalOrigen
        );
    }
}