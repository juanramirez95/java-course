package com.devsenior.colmena.service;

import com.devsenior.colmena.dto.AuthResponse;
import com.devsenior.colmena.dto.LoginRequest;
import com.devsenior.colmena.dto.RegistroRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse registrar(RegistroRequest request);
}
