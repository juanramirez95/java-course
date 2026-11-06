package com.devsenior.colmena.model;

import java.util.Set;

import static com.devsenior.colmena.model.Permiso.*;

// Un rol es un paquete de permisos.
// Para cambiar lo que puede hacer un rol, se cambia SOLO esta lista.
public enum Rol {

    ADMIN(Set.of(
            ESPACIOS_VER, ESPACIOS_GESTIONAR,
            RESERVAS_VER_TODAS, RESERVAS_CANCELAR_TODAS,
            USUARIOS_GESTIONAR, REPORTES_VER
    )),

    USER(Set.of(
            ESPACIOS_VER,
            RESERVAS_CREAR, RESERVAS_VER_PROPIAS, RESERVAS_CANCELAR_PROPIAS
    ));

    private final Set<Permiso> permisos;

    Rol(Set<Permiso> permisos) {
        this.permisos = permisos;
    }

    public Set<Permiso> getPermisos() {
        return permisos;
    }
}
