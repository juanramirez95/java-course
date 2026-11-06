# 🐝 Colmena Coworking

Sistema de reservas para Colmena, un coworking en Chía (Cundinamarca).
Backend en Spring Boot y frontend en Angular, con roles y permisos.

## Qué es

Mariana Gómez administraba las reservas de su coworking en un Excel compartido.
Dos miembros llegaron a la misma sala a la misma hora, un miembro vio la facturación
y nadie sabía cuánto se había facturado en el mes.

Con Colmena, cada miembro reserva desde el celular y el sistema nunca cruza dos reservas.
Mariana ve la agenda del día, lo facturado del mes y qué espacio se usa más.

## Tecnologías

| Capa | Tecnología |
|---|---|
| Backend | Java 17+, Spring Boot 4, Spring Security, Spring Data JPA |
| Autenticación | JWT con los permisos dentro del token |
| Base de datos | PostgreSQL (H2 en memoria para las pruebas) |
| Frontend | Angular 22 con signals y rutas `loadComponent` |
| Documentación | Swagger (springdoc-openapi) |

## Cómo levantarlo

1. En pgAdmin, crea una base de datos vacía llamada `colmena_db`.
2. Backend, desde `colmena-api/` (queda en http://localhost:8080):

```bash
./mvnw spring-boot:run        # Git Bash, macOS o Linux
mvnw.cmd spring-boot:run      # CMD o PowerShell
```

3. Frontend, desde `colmena-web/` (queda en http://localhost:4200):

```bash
npm install
ng serve
```

La documentación de la API queda en http://localhost:8080/swagger-ui.html

## Variables de entorno

| Variable | Para qué | Si no existe |
|---|---|---|
| `JWT_SECRET` | Clave con la que se firman los tokens (mínimo 32 caracteres) | Usa una clave de desarrollo |
| `DB_USER` | Usuario de PostgreSQL | `postgres` |
| `DB_PASSWORD` | Contraseña de PostgreSQL | `postgres` |

En un servidor real, `JWT_SECRET` siempre se define: quien conozca la clave puede fabricar tokens.

## Usuarios de prueba

Clave de todos: `colmena123`

| Persona | Email | Rol |
|---|---|---|
| Mariana Gómez | `admin@colmena.co` | ADMIN |
| Laura Restrepo | `laura@correo.com` | USER |
| Andrés Castillo | `andres@correo.com` | USER |
| Camila Ortiz | `camila@correo.com` | USER |
| Sofía Duarte | `sofia@correo.com` | USER (cuenta desactivada) |

## Roles y permisos

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

## Decisiones técnicas

- **Cada endpoint pide un permiso, no un rol.** Un rol nuevo se arma con los permisos que ya existen.
- **El backend lee los permisos de la base de datos en cada petición.** Si Mariana desactiva una cuenta, su token deja de servir.
- **Las reglas viven en el backend y se repiten en el formulario.** El formulario orienta; el backend protege.
- **El panel lo calcula el backend.** La facturación es un dato sensible y una regla de negocio.
- **Controladores → servicios → repositorios.** Ningún controlador toca la base de datos.

## Pruebas

Desde `colmena-api/`: `./mvnw test`. Levantan la API con una base de datos en memoria y no tocan `colmena_db`.

## Demo

🎥 Video de 3 minutos: [pega aquí el enlace]