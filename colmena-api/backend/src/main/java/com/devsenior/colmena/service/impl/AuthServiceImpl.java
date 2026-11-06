package com.devsenior.colmena.service.impl;

import com.devsenior.colmena.dto.AuthResponse;
import com.devsenior.colmena.dto.LoginRequest;
import com.devsenior.colmena.dto.RegistroRequest;
import com.devsenior.colmena.exception.ReglaNegocioException;
import com.devsenior.colmena.model.Rol;
import com.devsenior.colmena.model.Usuario;
import com.devsenior.colmena.repository.UsuarioRepository;
import com.devsenior.colmena.service.AuthService;
import com.devsenior.colmena.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        String email = request.email().trim().toLowerCase();

        // Si el email o la contraseña no coinciden, o la cuenta esta desactivada,
        // esta linea lanza una AuthenticationException (401)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, request.password()));

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario " + email));

        return new AuthResponse(jwtService.generarToken(usuario));
    }

    @Override
    public AuthResponse registrar(RegistroRequest request) {
        String email = request.email().trim().toLowerCase();

        if (usuarioRepository.existsByEmail(email)) {
            throw new ReglaNegocioException("Ya existe una cuenta con el email " + email);
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.nombre().trim());
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setRol(Rol.USER);   // el registro publico SIEMPRE crea miembros, nunca ADMIN

        return new AuthResponse(jwtService.generarToken(usuarioRepository.save(usuario)));
    }

}
