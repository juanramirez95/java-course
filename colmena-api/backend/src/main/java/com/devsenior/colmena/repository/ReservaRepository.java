package com.devsenior.colmena.repository;

import com.devsenior.colmena.model.EstadoReserva;
import com.devsenior.colmena.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // "Mis reservas": las de un usuario, de la mas reciente a la mas antigua
    List<Reserva> findByUsuarioEmailOrderByFechaDescHoraInicioDesc(String email);

    // Todas las reservas (solo para quien tiene RESERVAS_VER_TODAS)
    List<Reserva> findAllByOrderByFechaDescHoraInicioDesc();

    // La agenda de un dia (todas las reservas de esa fecha)
    List<Reserva> findByFechaOrderByHoraInicioAsc(LocalDate fecha);

    // Las franjas ocupadas de un espacio en un dia
    List<Reserva> findByEspacioIdAndFechaAndEstadoOrderByHoraInicioAsc(
            Long espacioId, LocalDate fecha, EstadoReserva estado);

    // ¿Hay otra reserva confirmada que se cruce con [inicio, fin)?
    // Se cruzan si la existente empieza antes de que termine la nueva
    // y termina despues de que empiece la nueva.
    boolean existsByEspacioIdAndFechaAndEstadoAndHoraInicioLessThanAndHoraFinGreaterThan(
            Long espacioId, LocalDate fecha, EstadoReserva estado,
            LocalTime finNueva, LocalTime inicioNueva);

    List<Reserva> findByFechaBetweenAndEstado(LocalDate desde, LocalDate hasta, EstadoReserva estado);
}
