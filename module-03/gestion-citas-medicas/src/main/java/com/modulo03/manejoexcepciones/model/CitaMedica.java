package com.modulo03.manejoexcepciones.model;

import java.time.LocalDate;
import java.time.LocalTime;


import com.modulo03.manejoexcepciones.model.enums.EstadoCitaMedica;

public class CitaMedica {

private String citaId;
private LocalDate fecha;
private LocalTime hora;
private String descripcion;
private EstadoCitaMedica  status;


// Constructores
public CitaMedica() {}



public CitaMedica(String citaId, String descripcion) {
    this.citaId = citaId;
    this.fecha = LocalDate.now();
    this.hora = LocalTime.now();
    this.descripcion = descripcion;
    this.status = EstadoCitaMedica.PENDING;
}





// Getters


public String getCitaId() {
    return citaId;
}



public LocalDate getFecha() {
    return fecha;
}



public LocalTime getHora() {
    return hora;
}



public String getDescripcion() {
    return descripcion;
}

public EstadoCitaMedica getStatus() {
    return status;
}
//  Setters
public void setCitaId(String citaId) {
    this.citaId = citaId;
}


public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}

public void setStatus(EstadoCitaMedica status) {
    this.status = status;
}

public EstadoCitaMedica actualizarEstado(EstadoCitaMedica status){
    this.setStatus(status);
    return status;
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((citaId == null) ? 0 : citaId.hashCode());
    result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
    result = prime * result + ((hora == null) ? 0 : hora.hashCode());
    result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    return result;
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    CitaMedica other = (CitaMedica) obj;
    if (citaId == null) {
        if (other.citaId != null)
            return false;
    } else if (!citaId.equals(other.citaId))
        return false;
    if (fecha == null) {
        if (other.fecha != null)
            return false;
    } else if (!fecha.equals(other.fecha))
        return false;
    if (hora == null) {
        if (other.hora != null)
            return false;
    } else if (!hora.equals(other.hora))
        return false;
    if (descripcion == null) {
        if (other.descripcion != null)
            return false;
    } else if (!descripcion.equals(other.descripcion))
        return false;
    if (status != other.status)
        return false;
    return true;
}

@Override
public String toString() {
    return "CitaMedica\n [citaId=" + citaId + ", fecha=" + fecha + ", hora=" + hora + ", descripcion=" + descripcion
            + ", status=" + status + "]\n";
}
    



}
