# Colmena Coworking · API

API de reservas de espacios de coworking con **roles y permisos**.
Spring Boot 4 · Spring Security + JWT · PostgreSQL · Swagger.

> Proyecto académico del Módulo 3 · Java Full Stack Developer · Dev Senior Code.

## Requisitos

- Java 17 o superior
- PostgreSQL (pgAdmin opcional)
- No hace falta instalar Maven: el proyecto trae `mvnw` / `mvnw.cmd`

## Cómo arrancar

1. En pgAdmin, crea una base de datos vacía llamada `colmena_db`.
2. Si tu usuario o clave de PostgreSQL no son `postgres`, cámbialos en `src/main/resources/application.properties` (lo que va después de los dos puntos en `${DB_USER:postgres}` y `${DB_PASSWORD:postgres}`), o define las variables de entorno `DB_USER` y `DB_PASSWORD`.
3. Desde esta carpeta:

```bash
mvnw.cmd spring-boot:run      # Windows
./mvnw spring-boot:run        # macOS / Linux
```

4. Abre `http://localhost:8080/swagger-ui.html`.

Al primer arranque se crean las tablas y se siembran datos de ejemplo (las reservas usan fechas relativas a ese día).

## Usuarios de prueba (clave: `colmena123`)

| Email | Rol | Nombre |
|---|---|---|
| `admin@colmena.co` | ADMIN | Mariana Gómez |
| `laura@correo.com` | USER | Laura Restrepo |
| `andres@correo.com` | USER | Andrés Castillo |
| `camila@correo.com` | USER | Camila Ortiz |
| `sofia@correo.com` | USER (cuenta desactivada) | Sofía Duarte |

## Roles y permisos

Los permisos viven en `model/Permiso.java`; cada rol es un paquete de permisos en `model/Rol.java`.

| Permiso | ADMIN | USER |
|---|---|---|
| `ESPACIOS_VER` | ✅ | ✅ |
| `ESPACIOS_GESTIONAR` | ✅ | |
| `RESERVAS_CREAR` | | ✅ |
| `RESERVAS_VER_PROPIAS` | | ✅ |
| `RESERVAS_CANCELAR_PROPIAS` | | ✅ |
| `RESERVAS_VER_TODAS` | ✅ | |
| `RESERVAS_CANCELAR_TODAS` | ✅ | |
| `USUARIOS_GESTIONAR` | ✅ | |
| `REPORTES_VER` | ✅ | |

El JWT incluye `nombre`, `rol` y `permisos` para que el frontend decida qué mostrar. El backend **no** confía en esa lista: en cada petición vuelve a cargar los permisos desde la base de datos.

## Endpoints

| Método | Ruta | Permiso |
|---|---|---|
| POST | `/api/auth/login` | Público |
| POST | `/api/auth/registro` | Público (siempre crea `USER`) |
| GET | `/api/espacios` | `ESPACIOS_VER` (ADMIN ve todos; USER, solo activos) |
| GET | `/api/espacios/{id}` | `ESPACIOS_VER` |
| GET | `/api/espacios/{id}/ocupacion?fecha=AAAA-MM-DD` | `ESPACIOS_VER` |
| POST | `/api/espacios` | `ESPACIOS_GESTIONAR` |
| PUT | `/api/espacios/{id}` | `ESPACIOS_GESTIONAR` |
| PATCH | `/api/espacios/{id}/estado` | `ESPACIOS_GESTIONAR` |
| GET | `/api/reservas?fecha=AAAA-MM-DD` | `RESERVAS_VER_TODAS` |
| GET | `/api/reservas/mias` | `RESERVAS_VER_PROPIAS` |
| POST | `/api/reservas` | `RESERVAS_CREAR` |
| PATCH | `/api/reservas/{id}/cancelar` | `RESERVAS_CANCELAR_PROPIAS` (solo las suyas) o `RESERVAS_CANCELAR_TODAS` |
| GET | `/api/usuarios` | `USUARIOS_GESTIONAR` |
| POST | `/api/usuarios` | `USUARIOS_GESTIONAR` |
| PATCH | `/api/usuarios/{id}/estado` | `USUARIOS_GESTIONAR` (nadie se desactiva a sí mismo) |
| GET | `/api/reportes/resumen` | `REPORTES_VER` |

## Reglas de una reserva

- El espacio existe (404) y está activo (409).
- Hora de fin posterior a la de inicio (400).
- Horario 7:00 a. m. – 9:00 p. m. (400) y bloques de 30 minutos (400).
- No se reserva en el pasado (400) ni por encima de la capacidad (400).
- No se cruza con otra reserva confirmada del mismo espacio (409).
- El dueño sale del token y el valor lo calcula el backend.

## Errores

Todas las respuestas de error tienen el formato `{ "codigo", "mensaje", "fecha" }`:
`400` datos inválidos · `401` sin sesión o credenciales incorrectas · `403` sin permiso · `404` no existe · `409` regla de negocio.

## Estructura

```text
src/main/java/com/devsenior/colmena/
├── config/        SecurityConfig, JwtFilter, OpenApiConfig, DataInitializer
├── controller/    Auth, Espacio, Reserva, Usuario, Reporte
├── dto/           Requests, responses y ErrorResponse
├── exception/     ManejadorGlobalExcepciones y excepciones propias
├── model/         Usuario, Espacio, Reserva, Rol, Permiso, TipoEspacio, EstadoReserva
├── repository/    JpaRepository por entidad
└── service/       Interfaces, JwtService, UsuarioDetailsService e impl/
```

---

© 2026 Ana Alvarado · Educadora Tech & Desarrolladora Full Stack · Todos los derechos reservados · linkedin.com/in/ana-alvarado-instructora-full-stack
