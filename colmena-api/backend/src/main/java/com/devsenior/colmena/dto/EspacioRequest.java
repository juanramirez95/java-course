package com.devsenior.colmena.dto;

import com.devsenior.colmena.model.TipoEspacio;
import jakarta.validation.constraints.*;

public record EspacioRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 80, message = "El nombre admite máximo 80 caracteres")
        String nombre,

        @NotNull(message = "El tipo es obligatorio")
        TipoEspacio tipo,

        @Min(value = 1, message = "La capacidad mínima es 1 persona")
        @Max(value = 200, message = "La capacidad máxima es 200 personas")
        int capacidad,

        @Min(value = 1000, message = "El precio por hora mínimo es $1.000")
        int precioHora,

        @Size(max = 500, message = "La descripción admite máximo 500 caracteres")
        String descripcion,

        @Size(max = 300, message = "La URL de la imagen admite máximo 300 caracteres")
        String imagenUrl
) {
}
