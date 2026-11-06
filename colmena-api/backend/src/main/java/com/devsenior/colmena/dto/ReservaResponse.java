package com.devsenior.colmena.dto;

import com.devsenior.colmena.model.EstadoReserva;
import com.devsenior.colmena.model.TipoEspacio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ReservaResponse(
        Long id,
        Long espacioId,
        String espacioNombre,
        TipoEspacio espacioTipo,
        Long usuarioId,
        String usuarioNombre,
        LocalDate fecha,
        LocalTime horaInicio,
        LocalTime horaFin,
        int asistentes,
        String nota,
        int valorTotal,
        EstadoReserva estado,
        LocalDateTime creadaEn
) {
}
