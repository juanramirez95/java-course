package com.devsenior.colmena.service;

import com.devsenior.colmena.dto.UsuarioRequest;
import com.devsenior.colmena.dto.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponse> listar();

    UsuarioResponse crear(UsuarioRequest request);

    UsuarioResponse cambiarEstado(Long id, boolean activo, String emailDeQuienPide);
}
