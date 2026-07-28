package com.modulo03.manejoexcepciones.model;


import java.util.LinkedHashMap;
import java.util.Map;

import com.modulo03.manejoexcepciones.model.enums.EstadoCitaMedica;

public class Paciente {

    private String pacienteID;
    private String pacienteNombre;
    private String fechaNacimiento;
    private Map<String,CitaMedica> citasMedicas;
    private static final int MAX_CITAS= 5;
   
    public Paciente(){};
   
    public Paciente(String pacienteID, String pacienteNombre, String fechaNacimiento) {
        this.pacienteID = pacienteID;
        this.pacienteNombre = pacienteNombre;
        this.fechaNacimiento = fechaNacimiento;
        this.citasMedicas = new LinkedHashMap<>();
    }

    public String getPacienteID() {
        return pacienteID;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public static int getMaxCitas() {
        return MAX_CITAS;
    }

    

    public Map<String, CitaMedica> getCitasMedicas() {
        return citasMedicas;
    }

    public void setPacienteID(String pacienteID) {
        this.pacienteID = pacienteID;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    /**
     * Valida que se pueda crear una cita al paciente si el array de citas del paciente no esta lleno.
     * @param citaId
     * @param citaMedica
     * @return
     */
    public boolean agregrCita(String citaId, CitaMedica citaMedica){
            citasMedicas.put(citaId, citaMedica);
            return true;}
       
    
    /**
     * Valida que exista una cita con el ID establecido
     * @param citaId
     * @return
     */
    public boolean existeCita(String citaId){
        return citasMedicas.containsKey(citaId);
    }

    public CitaMedica buscarCitaMedica(String citaId){
        return citasMedicas.get(citaId);
    }

    public int totalCitas(){
        return citasMedicas.size();
    }

    public EstadoCitaMedica actualizarEstadoCita(String citaId,EstadoCitaMedica estado){
        return citasMedicas.get(citaId).actualizarEstado(estado);
    }
    
    



    
}
