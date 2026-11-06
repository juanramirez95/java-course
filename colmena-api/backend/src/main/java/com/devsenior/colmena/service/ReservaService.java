package com.devsenior.colmena.service;

import com.devsenior.colmena.dto.ReservaRequest;
import com.devsenior.colmena.dto.ReservaResponse;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {

    List<ReservaResponse> listarMias(String email);

    List<ReservaResponse> listarTodas(LocalDate fecha);

    ReservaResponse crear(ReservaRequest request, String email);

    ReservaResponse cancelar(Long id, Authentication autenticacion);
}
