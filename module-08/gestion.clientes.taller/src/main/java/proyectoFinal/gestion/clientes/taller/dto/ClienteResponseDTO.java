package proyectoFinal.gestion.clientes.taller.dto;

public record ClienteResponseDTO(
        Long id,
        String nombre,
        String cedula,
        String telefono,
        String email) {
}