import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.modulo03.manejoexcepciones.model.CitaMedica;
import com.modulo03.manejoexcepciones.model.Consultorio;
import com.modulo03.manejoexcepciones.model.Paciente;
import com.modulo03.manejoexcepciones.model.enums.EstadoCitaMedica;
import com.modulo03.manejoexcepciones.service.ConsultorioServiceImpl;

class ConsultorioServiceImplTest {

    @Test
    void shouldRegisterPatientAndScheduleAppointment() {
        Consultorio consultorio = new Consultorio();
        Paciente pacienteBase = new Paciente();
        ConsultorioServiceImpl service = new ConsultorioServiceImpl(pacienteBase, consultorio);

        Paciente paciente = service.registrarPaciente("P001", "Ana", "1990-01-01", pacienteBase);
        assertEquals("P001", paciente.getPacienteID());

        CitaMedica cita = service.agendarCitaPacienteExistente("P001", "C001", "Consulta general");
        assertEquals("C001", cita.getCitaId());
        assertEquals(EstadoCitaMedica.PENDING, cita.getStatus());

        EstadoCitaMedica nuevoEstado = service.actualizarEstadoCita("C001", EstadoCitaMedica.CONFIRMED);
        assertEquals(EstadoCitaMedica.CONFIRMED, nuevoEstado);
    }
}
