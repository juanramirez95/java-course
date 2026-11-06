package com.devsenior.colmena.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

// No trae usuario, ni valor, ni estado: eso lo decide el backend
public record ReservaRequest(
        @NotNull(message = "El espacio es obligatorio")
        Long espacioId,

        @NotNull(message = "La fecha es obligatoria")
        LocalDate fecha,

        @NotNull(message = "La hora de inicio es obligatoria")
        LocalTime horaInicio,

        @NotNull(message = "La hora de fin es obligatoria")
        LocalTime horaFin,

        @Min(value = 1, message = "Debe asistir al menos 1 persona")
        int asistentes,

        @Size(max = 200, message = "La nota admite máximo 200 caracteres")
        String nota
) {
}
