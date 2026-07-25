package proyectoFinal.gestion.clientes.taller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import proyectoFinal.gestion.clientes.taller.model.enums.EstadoOrden;

public record OrdenServicioResponseDTO(
        Long id,
        String descripcionTrabajo,
        LocalDateTime fechaEntrada,
        LocalDateTime fechaSalida,
        EstadoOrden estado,
        BigDecimal costoTotal,
        Long vehiculoId) {
}