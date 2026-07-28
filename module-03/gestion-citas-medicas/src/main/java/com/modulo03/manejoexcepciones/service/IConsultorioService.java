package com.modulo03.manejoexcepciones.service;

import com.modulo03.manejoexcepciones.model.CitaMedica;
import com.modulo03.manejoexcepciones.model.Paciente;
import com.modulo03.manejoexcepciones.model.enums.EstadoCitaMedica;

public interface IConsultorioService {
    Paciente registrarPaciente(String pacienteId, String pacienteNombre, String fechaNacimiento, Paciente paciente);
    Paciente buscarPaciente(String pacienteId);
    CitaMedica agendarCitaPacienteExistente(String pacienteId, String citaId, String descripcion);
    CitaMedica buscarCitaMedicaPaciente(String pacienteId, String citaId);
    EstadoCitaMedica actualizarEstadoCita(String citaId, EstadoCitaMedica estadoCitaMedica);
    EstadoCitaMedica actualizarEstadoCitaPaciente(String pacienteId, String citaId, EstadoCitaMedica estadoCitaMedica);
    void mostrarCitasPaciente(String pacienteId);
    void mostrarPacientesConCitas();
}
