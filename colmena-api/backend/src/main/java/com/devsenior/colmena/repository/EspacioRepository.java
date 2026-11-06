package com.devsenior.colmena.repository;

import com.devsenior.colmena.model.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspacioRepository extends JpaRepository<Espacio, Long> {

    List<Espacio> findAllByOrderByNombreAsc();

    List<Espacio> findByActivoTrueOrderByNombreAsc();

    boolean existsByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);

    long countByActivoTrue();
}
