package com.devsenior.colmena.service;

import com.devsenior.colmena.model.Permiso;
import com.devsenior.colmena.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // El token lleva quien es (sub), como se llama, su rol y la lista de permisos.
    // Angular lee rol y permisos para decidir QUE MOSTRAR. El backend no confia
    // en esa lista: en cada peticion vuelve a leer los permisos de la base de datos.
    public String generarToken(Usuario usuario) {
        List<String> permisos = usuario.getRol().getPermisos().stream()
                .map(Permiso::name)
                .sorted()
                .toList();

        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("nombre", usuario.getNombre())
                .claim("rol", usuario.getRol().name())
                .claim("permisos", permisos)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey())
                .compact();
    }

    public String extraerEmail(String token) {
        return extraerClaims(token).getSubject();
    }

    private Claims extraerClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean esValido(String token, UserDetails userDetails) {
        String email = extraerEmail(token);
        boolean vencido = extraerClaims(token).getExpiration().before(new Date());
        return email.equals(userDetails.getUsername()) && !vencido;
    }
}
