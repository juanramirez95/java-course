package com.devsenior.colmena.config;

import com.devsenior.colmena.model.*;
import com.devsenior.colmena.repository.EspacioRepository;
import com.devsenior.colmena.repository.ReservaRepository;
import com.devsenior.colmena.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

// Siembra datos de ejemplo SOLO si la base esta vacia.
// Las reservas usan fechas relativas a HOY: siempre hay agenda reciente.
@Component
public class DataInitializer implements CommandLineRunner {

    private static final String CLAVE_DEMO = "colmena123";

    private final UsuarioRepository usuarioRepository;
    private final EspacioRepository espacioRepository;
    private final ReservaRepository reservaRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository,
                           EspacioRepository espacioRepository,
                           ReservaRepository reservaRepository,
                           PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.espacioRepository = espacioRepository;
        this.reservaRepository = reservaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() > 0) {
            return;
        }

        // ---------- Personas ----------
        usuario("Mariana Gómez", "admin@colmena.co", Rol.ADMIN, true);
        Usuario laura = usuario("Laura Restrepo", "laura@correo.com", Rol.USER, true);
        Usuario andres = usuario("Andrés Castillo", "andres@correo.com", Rol.USER, true);
        Usuario camila = usuario("Camila Ortiz", "camila@correo.com", Rol.USER, true);
        Usuario sofia = usuario("Sofía Duarte", "sofia@correo.com", Rol.USER, false);

        // ---------- Espacios ----------
        Espacio panal = espacio("Sala Panal", TipoEspacio.SALA_REUNIONES, 8, 45000,
                "Pantalla de 65\", tablero de vidrio y equipo de videoconferencia.",
                "https://loremflickr.com/800/500/meeting,room?lock=11", true);
        Espacio polen = espacio("Sala Polen", TipoEspacio.SALA_REUNIONES, 4, 30000,
                "Sala pequeña con luz natural, ideal para entrevistas y reuniones rápidas.",
                "https://loremflickr.com/800/500/meeting,room?lock=27", true);
        Espacio miel = espacio("Oficina Miel", TipoEspacio.OFICINA_PRIVADA, 4, 35000,
                "Oficina cerrada para equipos pequeños, con escritorios y archivador.",
                "https://loremflickr.com/800/500/office,desk?lock=8", true);
        Espacio nectar = espacio("Oficina Néctar", TipoEspacio.OFICINA_PRIVADA, 6, 40000,
                "Oficina con vista a la montaña, tablero y café incluido.",
                "https://loremflickr.com/800/500/office,desk?lock=19", true);
        Espacio zumbido = espacio("Cabina Zumbido", TipoEspacio.CABINA, 1, 12000,
                "Cabina insonorizada para llamadas y videollamadas privadas.",
                "https://loremflickr.com/800/500/office,phone?lock=5", true);
        espacio("Auditorio Colmena", TipoEspacio.AUDITORIO, 40, 150000,
                "Auditorio con sonido y proyector para eventos y talleres. En mantenimiento.",
                "https://loremflickr.com/800/500/conference,auditorium?lock=3", false);

        // ---------- Reservas (fechas relativas a hoy) ----------
        LocalDate hoy = LocalDate.now();

        reserva(panal, laura, hoy, "09:00", "11:00", 5, "Revisión de diseño con cliente", EstadoReserva.CONFIRMADA);
        reserva(miel, andres, hoy, "14:00", "18:00", 3, "Jornada de trabajo del equipo", EstadoReserva.CONFIRMADA);
        reserva(zumbido, laura, hoy, "16:00", "16:30", 1, null, EstadoReserva.CONFIRMADA);
        reserva(polen, andres, hoy.plusDays(1), "10:00", "11:30", 3, "Entrevista de selección", EstadoReserva.CONFIRMADA);
        reserva(panal, camila, hoy.plusDays(1), "15:00", "17:00", 6, "Taller de ventas", EstadoReserva.CONFIRMADA);
        reserva(nectar, laura, hoy.plusDays(2), "08:00", "12:00", 2, null, EstadoReserva.CONFIRMADA);
        reserva(panal, camila, hoy.minusDays(1), "10:00", "12:00", 7, "Planeación trimestral", EstadoReserva.CONFIRMADA);
        reserva(nectar, andres, hoy.minusDays(3), "09:00", "13:00", 4, null, EstadoReserva.CONFIRMADA);
        reserva(polen, laura, hoy.minusDays(5), "11:00", "12:00", 2, null, EstadoReserva.CANCELADA);
        reserva(zumbido, sofia, hoy.minusDays(10), "08:00", "09:00", 1, null, EstadoReserva.CONFIRMADA);
    }

    private Usuario usuario(String nombre, String email, Rol rol, boolean activo) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(CLAVE_DEMO));
        usuario.setRol(rol);
        usuario.setActivo(activo);
        return usuarioRepository.save(usuario);
    }

    private Espacio espacio(String nombre, TipoEspacio tipo, int capacidad, int precioHora,
                            String descripcion, String imagenUrl, boolean activo) {
        Espacio espacio = new Espacio();
        espacio.setNombre(nombre);
        espacio.setTipo(tipo);
        espacio.setCapacidad(capacidad);
        espacio.setPrecioHora(precioHora);
        espacio.setDescripcion(descripcion);
        espacio.setImagenUrl(imagenUrl);
        espacio.setActivo(activo);
        return espacioRepository.save(espacio);
    }

    private void reserva(Espacio espacio, Usuario usuario, LocalDate fecha, String inicio, String fin,
                         int asistentes, String nota, EstadoReserva estado) {
        LocalTime horaInicio = LocalTime.parse(inicio);
        LocalTime horaFin = LocalTime.parse(fin);
        long minutos = Duration.between(horaInicio, horaFin).toMinutes();

        Reserva reserva = new Reserva();
        reserva.setEspacio(espacio);
        reserva.setUsuario(usuario);
        reserva.setFecha(fecha);
        reserva.setHoraInicio(horaInicio);
        reserva.setHoraFin(horaFin);
        reserva.setAsistentes(asistentes);
        reserva.setNota(nota);
        reserva.setValorTotal((int) (espacio.getPrecioHora() * minutos / 60));
        reserva.setEstado(estado);
        reservaRepository.save(reserva);
    }
}
