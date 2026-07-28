package com.modulo03.manejoexcepciones;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modulo03.manejoexcepciones.exceptions.CapacidadListaLlenaException;
import com.modulo03.manejoexcepciones.exceptions.IdAlreadyExistsException;
import com.modulo03.manejoexcepciones.exceptions.IdNotFoundException;
import com.modulo03.manejoexcepciones.exceptions.InvalidDataException;
import com.modulo03.manejoexcepciones.model.Consultorio;
import com.modulo03.manejoexcepciones.model.Paciente;
import com.modulo03.manejoexcepciones.model.enums.EstadoCitaMedica;
import com.modulo03.manejoexcepciones.service.ConsultorioServiceImpl;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Consultorio consultorio = new Consultorio();
        ConsultorioServiceImpl service = new ConsultorioServiceImpl(new Paciente(), consultorio);
        log.info("Iniciando sistema de gestión de citas médicas");

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();

            try {
                switch (opcion) {
                    case "1":
                        registrarPaciente(scanner, service);
                        break;
                    case "2":
                        agendarCita(scanner, service);
                        break;
                    case "3":
                        actualizarEstadoCita(scanner, service);
                        break;
                    case "4":
                        buscarCita(scanner, service);
                        break;
                    case "5":
                        mostrarCitasPaciente(scanner, service);
                        break;
                    case "6":
                        buscarPaciente(scanner, service);
                        break;
                    case "7":
                        mostrarTodosPacientes(scanner, service);
                        break;
                    case "0":
                        continuar = false;
                        log.info("Cierre del sistema solicitado por el usuario");
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (InvalidDataException | IdAlreadyExistsException | IdNotFoundException | CapacidadListaLlenaException ex) {
                log.warn("Excepción manejada en la interfaz: {}", ex.getMessage());
                System.out.println("Error: " + ex.getMessage());
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Menú de Gestión de Citas Médicas ===");
        System.out.println("1. Registrar nuevo paciente");
        System.out.println("2. Agendar cita para un paciente");
        System.out.println("3. Actualizar estado de una cita");
        System.out.println("4. Buscar una cita por ID");
        System.out.println("5. Mostrar citas de un paciente");
        System.out.println("6. Buscar un paciente por ID");
        System.out.println("7. Mostrar todos los pacientes y sus citas");
        System.out.println("0. Salir");
    }

    private static void registrarPaciente(Scanner scanner, ConsultorioServiceImpl service) {
        System.out.print("ID del paciente: ");
        String id = scanner.nextLine().trim();
        System.out.print("Nombre del paciente: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Fecha de nacimiento: ");
        String fecha = scanner.nextLine().trim();

        Paciente paciente = service.registrarPaciente(id, nombre, fecha, null);
        System.out.println("Paciente registrado correctamente: " + paciente.getPacienteID());
    }

    private static void agendarCita(Scanner scanner, ConsultorioServiceImpl service) {
        System.out.print("ID del paciente: ");
        String pacienteId = scanner.nextLine().trim();
        System.out.print("ID de la cita: ");
        String citaId = scanner.nextLine().trim();
        System.out.print("Descripción de la cita: ");
        String descripcion = scanner.nextLine().trim();

        service.agendarCitaPacienteExistente(pacienteId, citaId, descripcion);
        System.out.println("Cita agendada correctamente.");
    }

    private static void actualizarEstadoCita(Scanner scanner, ConsultorioServiceImpl service) {
        System.out.print("ID del paciente: ");
        String pacienteId = scanner.nextLine().trim();
        System.out.print("ID de la cita: ");
        String citaId = scanner.nextLine().trim();
        System.out.print("Nuevo estado (PENDING, CANCELLED, CONFIRMED, COMPLETED): ");
        String estadoTexto = scanner.nextLine().trim().toUpperCase();

        EstadoCitaMedica estado = EstadoCitaMedica.valueOf(estadoTexto);
        EstadoCitaMedica nuevoEstado = service.actualizarEstadoCitaPaciente(pacienteId, citaId, estado);
        System.out.println("Estado actualizado a: " + nuevoEstado);
    }

    private static void buscarCita(Scanner scanner, ConsultorioServiceImpl service) {
        System.out.print("ID del paciente: ");
        String pacienteId = scanner.nextLine().trim();
        System.out.print("ID de la cita: ");
        String citaId = scanner.nextLine().trim();

        System.out.println(service.buscarCitaMedicaPaciente(pacienteId, citaId));
    }

    private static void mostrarCitasPaciente(Scanner scanner, ConsultorioServiceImpl service) {
        System.out.print("ID del paciente: ");
        String pacienteId = scanner.nextLine().trim();
        service.mostrarCitasPaciente(pacienteId);
    }

    private static void buscarPaciente(Scanner scanner, ConsultorioServiceImpl service) {
        System.out.print("ID del paciente: ");
        String pacienteId = scanner.nextLine().trim();
        Paciente paciente = service.buscarPaciente(pacienteId);
        System.out.println(paciente);
    }

    private static void mostrarTodosPacientes(Scanner scanner, ConsultorioServiceImpl service) {
        service.mostrarPacientesConCitas();
    }
}