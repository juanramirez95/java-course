package com.modulo03.manejoexcepciones.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modulo03.manejoexcepciones.exceptions.CapacidadListaLlenaException;
import com.modulo03.manejoexcepciones.exceptions.IdAlreadyExistsException;
import com.modulo03.manejoexcepciones.exceptions.IdNotFoundException;
import com.modulo03.manejoexcepciones.exceptions.InvalidDataException;
import com.modulo03.manejoexcepciones.model.CitaMedica;
import com.modulo03.manejoexcepciones.model.Paciente;
// import com.modulo03.manejoexcepciones.model.enums.EstadoCitaMedica;

public class PacienteServiceImpl implements IPacienteService{

    private final Paciente pacientes;
    private static final Logger log = LoggerFactory.getLogger(PacienteServiceImpl.class);

    public PacienteServiceImpl(Paciente pacientes) {
        this.pacientes = pacientes;
    }

    
    
    @Override
    public CitaMedica agregarCitaMedica(String citaId, String descripcion) {
        if (citaId==null||citaId.trim().isEmpty()||descripcion==null||descripcion.trim().isEmpty()) {
            log.warn("Intento de registrar Cita Médica con datos inválidos: id={}, descripcion={}",citaId,descripcion);
            throw new InvalidDataException("Los datos de ingresados estan vacios, incorrectos o ingesaste valores negativos");
        }
        if (pacientes.totalCitas()>=Paciente.getMaxCitas()) {
            log.warn("Catálogo lleno ({} productos). No se pudo registrar id={}", Paciente.getMaxCitas(), citaId);
            throw new CapacidadListaLlenaException("No se pueden agregar más productos al Inventario, Inventario LLeno");
        }

        CitaMedica citaMedica = new CitaMedica(citaId, descripcion);
        
        if (pacientes.existeCita(citaId)) {
            log.warn("Intento de registrar una cita con un Id existente: citaId={}", citaId);
            throw new IdAlreadyExistsException("ya existe un almacen con este ID: " + citaId);
        }

         pacientes.agregrCita(citaId, citaMedica);

         return citaMedica;
    }

    @Override
    public CitaMedica buscarCitaMedica(String citaId) {
       if (citaId==null||citaId.trim().isEmpty()) {
            log.warn("Datos invalidos o vacios: id={} ",citaId);
            throw new InvalidDataException("Los datos de ingresados estan vacios, incorrectos o ingesaste valores negativos");
        }
            
            CitaMedica citaMedica =  pacientes.buscarCitaMedica(citaId);

            if (citaMedica==null) {
                log.warn("No existe una cita medica con Este Id: {}",citaId);
                throw new IdNotFoundException("No se ha encontrado una cita con este Id: "+citaId);
            }
            return citaMedica; 
    }

    // @Override
    // public EstadoCitaMedica actualizarEstadoCita(String citaId, EstadoCitaMedica estadoCitaMedica) {
    //     if (citaId==null||citaId.trim().isEmpty()) {
    //         log.warn("Datos invalidos o vacios: id={} ",citaId);
    //         throw new InvalidDataException("Los datos de ingresados estan vacios, incorrectos o ingesaste valores negativos");
    //     }

    //      CitaMedica citaMedica =  pacientes.buscarCitaMedica(citaId);

    //     if (citaMedica==null) {
    //     log.warn("No existe una cita medica con Este Id: {}",citaId);
    //     throw new IdNotFoundException("No se ha encontrado una cita con este Id: "+citaId);
    //     }

    //     EstadoCitaMedica estadoCita = pacientes.actualizarEstadoCita(citaId, estadoCitaMedica);

    //     return estadoCita;
    // }

}
