package proyectoFinal.gestion.clientes.taller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record VehiculoRequestDTO(
        @NotBlank(message = "La placa es obligatoria")
        @Pattern(regexp = "^[A-Z]{3}[0-9]{2}[0-9A-Z]$", message = "La placa debe tener un formato valido")
        String placa,

        @NotBlank(message = "La marca es obligatoria")
        @Size(min = 2, max = 50, message = "La marca debe tener entre 2 y 50 caracteres")
        String marca,

        @NotBlank(message = "El modelo es obligatorio")
        @Size(min = 1, max = 50, message = "El modelo debe tener entre 1 y 50 caracteres")
        String modelo,

        @NotNull(message = "El anio es obligatorio")
        @Min(value = 1950, message = "El anio debe ser igual o mayor a 1950")
        @Max(value = 2100, message = "El anio debe ser igual o menor a 2100")
        Integer anio,

        @NotNull(message = "El cliente es obligatorio")
        Long clienteId) {
}