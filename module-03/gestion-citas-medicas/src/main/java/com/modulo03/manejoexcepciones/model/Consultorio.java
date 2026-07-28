package com.modulo03.manejoexcepciones.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Consultorio {

    private Map<String,Paciente> pacientes;
    private static final int MAX_PACIENTES = 10;

    public static int getMaxPacientes() {
        return MAX_PACIENTES;
    }

    public Consultorio() {
        this.pacientes = new LinkedHashMap<>();
    }

    public Map<String, Paciente> getPacientes() {
        return pacientes;
    }

    public void agregarPaciente(String id, Paciente paciente){
        pacientes.put(id, paciente);
    }

    public boolean existePaciente(String id){
        return pacientes.containsKey(id);
    }
    public Paciente buscarPaciente(String id){
        return pacientes.get(id);
    }
    public int totalPacientes(){
        return pacientes.size();
    }

    

    
}
