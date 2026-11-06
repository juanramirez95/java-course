package com.devsenior.colmena.dto;

import com.devsenior.colmena.model.TipoEspacio;

public record EspacioResponse(
        Long id,
        String nombre,
        TipoEspacio tipo,
        int capacidad,
        int precioHora,
        String descripcion,
        String imagenUrl,
        boolean activo
) {
}
