package proyectoFinal.gestion.clientes.taller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import proyectoFinal.gestion.clientes.taller.model.enums.EstadoOrden;

public record OrdenServicioRequestDTO(
        @NotBlank(message = "La descripcion del trabajo es obligatoria")
        @Size(min = 5, max = 500, message = "La descripcion debe tener entre 5 y 500 caracteres")
        String descripcionTrabajo,

        @NotNull(message = "La fecha de entrada es obligatoria")
        LocalDateTime fechaEntrada,

        LocalDateTime fechaSalida,

        @NotNull(message = "El estado de la orden es obligatorio")
        EstadoOrden estado,

        @NotNull(message = "El costo total es obligatorio")
        @DecimalMin(value = "0.0", inclusive = true, message = "El costo total no puede ser negativo")
        BigDecimal costoTotal,

        @NotNull(message = "El vehiculo es obligatorio")
        Long vehiculoId) {
}