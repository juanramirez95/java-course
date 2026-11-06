package com.devsenior.colmena.model;

import org.springframework.security.core.Authentication;

// Cada permiso es UNA accion concreta del negocio.
// Los roles (Rol.java) solo agrupan permisos.
public enum Permiso {

    ESPACIOS_VER,
    ESPACIOS_GESTIONAR,

    RESERVAS_CREAR,
    RESERVAS_VER_PROPIAS,
    RESERVAS_CANCELAR_PROPIAS,
    RESERVAS_VER_TODAS,
    RESERVAS_CANCELAR_TODAS,

    USUARIOS_GESTIONAR,
    REPORTES_VER;

    // ¿La persona autenticada tiene este permiso?
    public boolean estaEn(Authentication autenticacion) {
        return autenticacion.getAuthorities().stream()
                .anyMatch(autoridad -> autoridad.getAuthority().equals(name()));
    }
}
