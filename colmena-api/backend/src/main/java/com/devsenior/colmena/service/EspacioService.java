package com.devsenior.colmena.service;

import com.devsenior.colmena.dto.EspacioRequest;
import com.devsenior.colmena.dto.EspacioResponse;
import com.devsenior.colmena.dto.FranjaOcupada;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface EspacioService {

    List<EspacioResponse> listar(Authentication autenticacion);

    EspacioResponse buscarPorId(Long id, Authentication autenticacion);

    EspacioResponse crear(EspacioRequest request);

    EspacioResponse actualizar(Long id, EspacioRequest request);

    EspacioResponse cambiarEstado(Long id, boolean activo);

    List<FranjaOcupada> ocupacion(Long id, LocalDate fecha);
}
