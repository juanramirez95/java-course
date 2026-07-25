package proyectoFinal.gestion.clientes.taller.dto;

public record VehiculoResponseDTO(
        Long id,
        String placa,
        String marca,
        String modelo,
        Integer anio,
        Long clienteId) {
}