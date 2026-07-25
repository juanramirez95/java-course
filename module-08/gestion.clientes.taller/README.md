# Sistema de Gestión de Taller Mecánico

## 1. ¿Qué hace el sistema?
Este sistema es una API REST desarrollada para digitalizar la gestión de un taller mecánico (basado en el contexto de Zipaquirá). El sistema elimina la dependencia de un cuaderno físico y previene la pérdida de información de las órdenes. Permite la gestión de tres entidades clave [cite: 2]:
* **Clientes:** Registro con datos básicos (cédula y teléfono).
* **Vehículos:** Registro de los carros (placa, marca, modelo, año). Soporta la regla donde un cliente puede traer múltiples carros.
* **Órdenes de Servicio:** Registro de trabajos a realizar, fecha de ingreso, estado actual y costo. Un carro puede tener múltiples órdenes a lo largo del tiempo.

## 2. ¿Cómo se corre?
El proyecto está construido bajo el framework **Spring Boot** utilizando **Maven** para la gestión de dependencias [cite: 2].

### Requisitos previos:
* Java (JDK 17 o superior recomendado).
* Maven (`mvn` instalado en el sistema).
* Base de datos configurada según el `application.properties`.

### Pasos para ejecutar:
1. Clonar el repositorio.
2. Asegurar la configuración de base de datos en `src/main/resources/application.properties` [cite: 2].
3. Ejecutar el proyecto usando el Maven Wrapper incluido o una instalación local de Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
4. El aplicativo se levantará (por defecto en `http://localhost:8080`).

## 3. Endpoints Principales
Manejados a través de los controladores REST del sistema [cite: 2]:

| Controlador | Endpoint (Ejemplo) | Descripción |
|---|---|---|
| `ClienteController` | `POST /clientes` | Registra un nuevo cliente a través de un `ClienteRequestDTO`. |
| `ClienteController` | `GET /clientes/{id}` | Retorna los detalles de un cliente mapeado a `ClienteResponseDTO`. |
| `VehiculoController` | `POST /vehiculos` | Registra un nuevo vehículo usando `VehiculoRequestDTO`. |
| `VehiculoController` | `GET /vehiculos/...` | Consulta información de vehículos (`VehiculoResponseDTO`). |
| `OrdenServicioController` | `POST /ordenes` | Crea una nueva orden de servicio (`OrdenServicioRequestDTO`). |
| `OrdenServicioController` | `PUT /ordenes/{id}/estado`| Actualiza el estado de la orden de servicio (`EstadoOrden`). |

## 4. Tabla de Validaciones (DTOs)
Las validaciones de entrada se manejan mediante los DTOs de Request [cite: 2]:

| Entidad (RequestDTO) | Campo | Regla de Validación Aplicada |
|---|---|---|
| `ClienteRequestDTO` | `cedula` | No nulo, único en el sistema. |
| `ClienteRequestDTO` | `telefono` | No nulo, numérico. |
| `VehiculoRequestDTO` | `placa` | No nulo, único. Identificador principal del vehículo. |
| `VehiculoRequestDTO` | `marca`, `modelo`, `año` | Datos obligatorios para identificar el vehículo. |
| `OrdenServicioRequestDTO` | `descripcion` | Detalle obligatorio de lo que se le hará al carro. |
| `OrdenServicioRequestDTO` | `vehiculoId` / `placa` | Debe estar asociado a un vehículo previamente registrado. |

## 5. Tabla de Manejo de Excepciones
El sistema implementa un manejo global de errores centralizado en la clase `ManejadorGlobalExcepciones` [cite: 2], que intercepta excepciones específicas y responde con códigos HTTP adecuados:

| Excepción (Clase) | Código HTTP | Descripción |
|---|---|---|
| `RecursoNoEncontradoException` | `404 Not Found` | Lanzada cuando se busca un Cliente, Vehículo u Orden que no existe en BD. |
| `RecursoDuplicadoException` | `409 Conflict` | Lanzada al intentar registrar una cédula o placa que ya se encuentra en uso. |
| `OperacionNoPermitidaException` | `400 Bad Request` | Lanzada al intentar realizar transiciones de estado inválidas en una orden o violar reglas de negocio. |
| `Exception` / Validaciones | `400 Bad Request` | Errores de validación capturados desde los DTOs. |

## 6. Tabla de Errores (Reglas de Negocio)
Basado en las excepciones personalizadas del proyecto [cite: 2], se controlan las siguientes lógicas:

| Error / Escenario | Excepción Disparada | Manejo Esperado |
|---|---|---|
| Cliente con cédula repetida | `RecursoDuplicadoException` | Se aborta la creación y se notifica la duplicidad. |
| Vehículo con placa repetida | `RecursoDuplicadoException` | Impide el registro para mantener la unicidad del vehículo. |
| Búsqueda de entidad inexistente | `RecursoNoEncontradoException` | Protege endpoints de lectura y actualización. |
| Flujo de estado inválido de la Orden | `OperacionNoPermitidaException` | Controla que la orden siga un flujo lógico (ej. según el enum `EstadoOrden`). |

## 7. Estructura Final del Proyecto
La arquitectura está estrictamente dividida en capas. A continuación se detallan todas las clases e interfaces de cada paquete:

```text
proyectoFinal/gestion/clientes/taller
 ┣ 📜 Application.java
 ┣ 📂 controller
 ┃  ┣ 📜 ClienteController.java
 ┃  ┣ 📜 OrdenServicioController.java
 ┃  ┗ 📜 VehiculoController.java
 ┣ 📂 dto
 ┃  ┣ 📜 ClienteRequestDTO.java
 ┃  ┣ 📜 ClienteResponseDTO.java
 ┃  ┣ 📜 OrdenServicioRequestDTO.java
 ┃  ┣ 📜 OrdenServicioResponseDTO.java
 ┃  ┣ 📜 VehiculoRequestDTO.java
 ┃  ┗ 📜 VehiculoResponseDTO.java
 ┣ 📂 exception
 ┃  ┣ 📜 ManejadorGlobalExcepciones.java
 ┃  ┣ 📜 OperacionNoPermitidaException.java
 ┃  ┣ 📜 RecursoDuplicadoException.java
 ┃  ┗ 📜 RecursoNoEncontradoException.java
 ┣ 📂 model
 ┃  ┣ 📜 Cliente.java
 ┃  ┣ 📜 OrdenServicio.java
 ┃  ┣ 📜 Vehiculo.java
 ┃  ┗ 📂 enums
 ┃     ┗ 📜 EstadoOrden.java
 ┣ 📂 repository
 ┃  ┣ 📜 ClienteRepository.java
 ┃  ┣ 📜 OrdenServicioRepository.java
 ┃  ┗ 📜 VehiculoRepository.java
 ┗ 📂 service
    ┣ 📜 ClienteService.java
    ┣ 📜 ClienteServiceImpl.java
    ┣ 📜 OrdenServicioService.java
    ┣ 📜 OrdenServicioServiceImpl.java
    ┣ 📜 VehiculoService.java
    ┗ 📜 VehiculoServiceImpl.java