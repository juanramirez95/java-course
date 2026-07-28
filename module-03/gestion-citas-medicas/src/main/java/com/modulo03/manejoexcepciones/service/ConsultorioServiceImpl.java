package com.modulo03.manejoexcepciones.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modulo03.manejoexcepciones.exceptions.CapacidadListaLlenaException;
import com.modulo03.manejoexcepciones.exceptions.IdAlreadyExistsException;
import com.modulo03.manejoexcepciones.exceptions.IdNotFoundException;
import com.modulo03.manejoexcepciones.exceptions.InvalidDataException;
import com.modulo03.manejoexcepciones.model.CitaMedica;
import com.modulo03.manejoexcepciones.model.Consultorio;
import com.modulo03.manejoexcepciones.model.Paciente;
import com.modulo03.manejoexcepciones.model.enums.EstadoCitaMedica;

public class ConsultorioServiceImpl implements IConsultorioService {

    private static final Logger log = LoggerFactory.getLogger(ConsultorioServiceImpl.class);
    private final Consultorio consultorio;

    public ConsultorioServiceImpl(Paciente paciente, Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    @Override
    public Paciente registrarPaciente(String pacienteId, String pacienteNombre, String fechaNacimiento, Paciente paciente) {
        if (pacienteId == null || pacienteId.trim().isEmpty() || pacienteNombre == null || pacienteNombre.trim().isEmpty()
                || fechaNacimiento == null || fechaNacimiento.trim().isEmpty()) {
            log.warn("Intento de registrar Paciente con datos inválidos: id={}, Nombre={}, Fecha de Nacimiento={}", pacienteId, pacienteNombre, fechaNacimiento);
            throw new InvalidDataException("Los datos ingresados están vacíos o incorrectos");
        }

        if (consultorio.totalPacientes() >= Consultorio.getMaxPacientes()) {
            log.warn("Catálogo lleno ({} pacientes). No se pudo registrar id={}", Consultorio.getMaxPacientes(), pacienteId);
            throw new CapacidadListaLlenaException("No se pueden agregar más pacientes, lista llena");
        }

        if (consultorio.existePaciente(pacienteId)) {
            log.warn("Intento de registrar un paciente con un ID existente: pacienteId={}", pacienteId);
            throw new IdAlreadyExistsException("Ya existe un paciente con este ID: " + pacienteId);
        }

        Paciente nuevoPaciente = new Paciente(pacienteId, pacienteNombre, fechaNacimiento);
        consultorio.agregarPaciente(pacienteId, nuevoPaciente);
        log.info("Paciente registrado correctamente con ID {}", pacienteId);
        return nuevoPaciente;
    }

    @Override
    public CitaMedica agendarCitaPacienteExistente(String pacienteId, String citaId, String descripcion) {
        if (pacienteId == null || pacienteId.trim().isEmpty() || citaId == null || citaId.trim().isEmpty()
                || descripcion == null || descripcion.trim().isEmpty()) {
            log.warn("Intento de registrar cita con datos inválidos: pacienteId={}, citaId={}, descripcion={}", pacienteId, citaId, descripcion);
            throw new InvalidDataException("Los datos ingresados están vacíos o incorrectos");
        }

        Paciente paciente = buscarPaciente(pacienteId);
        CitaMedica citaMedica = new CitaMedica(citaId, descripcion);

        if (paciente.existeCita(citaId)) {
            log.warn("Intento de registrar una cita con un ID existente: citaId={}", citaId);
            throw new IdAlreadyExistsException("Ya existe una cita con este ID: " + citaId);
        }

        if (paciente.totalCitas() >= Paciente.getMaxCitas()) {
            log.warn("El paciente {} ya tiene el máximo de citas permitidas", pacienteId);
            throw new CapacidadListaLlenaException("El paciente ya tiene todas sus citas asignadas");
        }

        paciente.agregrCita(citaId, citaMedica);
        log.info("Cita {} agendada correctamente para el paciente {}", citaId, pacienteId);
        return citaMedica;
    }

    @Override
    public CitaMedica buscarCitaMedicaPaciente(String pacienteId, String citaId) {
        Paciente paciente = buscarPaciente(pacienteId);
        CitaMedica citaMedica = paciente.buscarCitaMedica(citaId);

        if (citaMedica == null) {
            log.warn("No existe una cita médica con este ID para el paciente {}: {}", pacienteId, citaId);
            throw new IdNotFoundException("No se encontró una cita con este ID: " + citaId);
        }

        log.info("Se consultó la cita {} del paciente {}", citaId, pacienteId);
        return citaMedica;
    }

    @Override
    public EstadoCitaMedica actualizarEstadoCita(String citaId, EstadoCitaMedica estadoCitaMedica) {
        if (citaId == null || citaId.trim().isEmpty()) {
            log.warn("Datos inválidos o vacíos: citaId={}", citaId);
            throw new InvalidDataException("Los datos ingresados están vacíos o incorrectos");
        }

        for (Paciente paciente : consultorio.getPacientes().values()) {
            if (paciente.existeCita(citaId)) {
                return paciente.actualizarEstadoCita(citaId, estadoCitaMedica);
            }
        }

        log.warn("No existe una cita médica con este ID: {}", citaId);
        throw new IdNotFoundException("No se ha encontrado una cita con este ID: " + citaId);
    }

    @Override
    public EstadoCitaMedica actualizarEstadoCitaPaciente(String pacienteId, String citaId, EstadoCitaMedica estadoCitaMedica) {
        if (pacienteId == null || pacienteId.trim().isEmpty() || citaId == null || citaId.trim().isEmpty()) {
            log.warn("Datos inválidos o vacíos: pacienteId={}, citaId={}", pacienteId, citaId);
            throw new InvalidDataException("Los datos ingresados están vacíos o incorrectos");
        }

        Paciente paciente = buscarPaciente(pacienteId);
        CitaMedica citaMedica = paciente.buscarCitaMedica(citaId);

        if (citaMedica == null) {
            log.warn("No existe una cita médica con este ID para el paciente {}: {}", pacienteId, citaId);
            throw new IdNotFoundException("No se encontró una cita con este ID: " + citaId);
        }

        EstadoCitaMedica estadoActualizado = paciente.actualizarEstadoCita(citaId, estadoCitaMedica);
        log.info("Estado de la cita {} actualizado a {} para el paciente {}", citaId, estadoActualizado, pacienteId);
        return estadoActualizado;
    }

    @Override
    public void mostrarCitasPaciente(String pacienteId) {
        Paciente paciente = buscarPaciente(pacienteId);
        if (paciente.totalCitas() == 0) {
            log.info("Se consultó el paciente {} y no tiene citas programadas", pacienteId);
            System.out.println("El paciente no tiene citas programadas.");
            return;
        }

        System.out.println("Citas de " + paciente.getPacienteNombre() + ":");
        for (CitaMedica cita : paciente.getCitasMedicas().values()) {
            System.out.println(cita);
        }
    }

    @Override
    public void mostrarPacientesConCitas() {
        if (consultorio.totalPacientes() == 0) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        for (Paciente paciente : consultorio.getPacientes().values()) {
            log.info("Mostrando pacientes registrados en el consultorio");
            System.out.println("Paciente: " + paciente.getPacienteID() + " - " + paciente.getPacienteNombre());
            if (paciente.totalCitas() == 0) {
                System.out.println("  Sin citas programadas");
                continue;
            }
            for (CitaMedica cita : paciente.getCitasMedicas().values()) {
                System.out.println("  - " + cita);
            }
        }
    }

    @Override
    public Paciente buscarPaciente(String pacienteId) {
        if (pacienteId == null || pacienteId.trim().isEmpty()) {
            log.warn("Datos inválidos o vacíos: pacienteId={}", pacienteId);
            throw new InvalidDataException("Los datos ingresados están vacíos o incorrectos");
        }

        if (!consultorio.existePaciente(pacienteId)) {
            log.warn("No existe un paciente con este ID={}", pacienteId);
            throw new IdNotFoundException("No existe un paciente con este ID: " + pacienteId);
        }

        return consultorio.buscarPaciente(pacienteId);
    }
}
