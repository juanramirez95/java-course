package com.modulo03.manejoexcepciones.service;

import com.modulo03.manejoexcepciones.model.CitaMedica;


public interface IPacienteService {
    CitaMedica agregarCitaMedica(String citaId,String descripcion);
    CitaMedica buscarCitaMedica(String citaId);
    // EstadoCitaMedica actualizarEstadoCita(String citaId, EstadoCitaMedica estadoCitaMedica);
    
}
