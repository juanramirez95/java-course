package com.modulo03.manejoexcepciones.model.enums;

public enum EstadoCitaMedica {
PENDING("Pendiente"),
CANCELLED("Cancelada"),
CONFIRMED("Confirmada"),
COMPLETED("Completada");

private String displayName;

private EstadoCitaMedica(String displayName) {
    this.displayName = displayName;
}

public String getDisplayName() {
    return this.displayName;
}

public boolean isCompleted(){
    return this ==COMPLETED;
}
}
