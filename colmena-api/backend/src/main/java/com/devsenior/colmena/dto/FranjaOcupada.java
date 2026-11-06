package com.devsenior.colmena.dto;

import java.time.LocalTime;

// Solo el horario ocupado: NO dice quien reservo (privacidad de los miembros)
public record FranjaOcupada(LocalTime horaInicio, LocalTime horaFin) {
}
