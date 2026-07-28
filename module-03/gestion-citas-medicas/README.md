# 🏥 Sistema de Gestión de Citas Médicas

## 📋 Descripción

Sistema desarrollado en **Java** para administrar pacientes y sus citas médicas, aplicando conceptos de **Programación Orientada a Objetos (POO)**, manejo de excepciones personalizadas, interfaces, enums y registro de eventos mediante **SLF4J** y **Log4j2**.

El sistema permite registrar pacientes, programar citas médicas, actualizar el estado de las citas y consultar la información almacenada, validando los datos de entrada y controlando los posibles errores mediante excepciones personalizadas.

## 🚀 Funcionalidades

### Gestión de Pacientes

- Registrar un nuevo paciente.
- Buscar un paciente por su identificador.
- Mostrar todos los pacientes registrados.
- Validar datos obligatorios.
- Evitar identificadores duplicados.
- Controlar la capacidad máxima del sistema.

### Gestión de Citas Médicas

Cada paciente puede almacenar múltiples citas médicas.

Permite:

- Registrar una nueva cita.
- Buscar una cita por su identificador.
- Actualizar el estado de una cita.
- Mostrar todas las citas del paciente.

Valida datos obligatorios, capacidad máxima, identificadores duplicados, existencia de la cita y estados válidos.

## 📌 Estados de una cita

- PENDING
- CONFIRMED
- COMPLETED
- CANCELLED

## 📂 Estructura del proyecto

```text
gestion-citas-medicas
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   │       └── log4j2.xml
│   └── test
├── logs
├── pom.xml
└── README.md
```

## 📦 Clases

### model
- Consultorio
- Paciente
- CitaMedica
- EstadoCitaMedica (Enum)

### service
- IConsultorioService
- ConsultorioServiceImpl
- IPacienteService
- PacienteServiceImpl

### exceptions
- InvalidDataException
- IdAlreadyExistsException
- IdNotFoundException
- CapacidadListaLlenaException

## 📝 Manejo de Logs

Se utiliza **SLF4J** como fachada de logging y **Log4j2** como implementación.

Se registran:

- Registro de pacientes.
- Registro de citas.
- Actualización de estados.
- Consultas.
- Advertencias.
- Excepciones.

La configuración se encuentra en:

```text
src/main/resources/log4j2.xml
```

Los archivos de log pueden organizarse así:

```text
logs/
├── main/application.log
└── exceptions/exceptions.log
```

## ✅ Validaciones

- Identificadores únicos.
- Capacidad máxima.
- Datos obligatorios.
- Estados válidos.
- Existencia de pacientes y citas.

## 🛠 Tecnologías

- Java
- Maven
- POO
- Interfaces
- Enums
- Excepciones personalizadas
- SLF4J
- Log4j2
- JUnit

## 👨‍💻 Autor

Proyecto académico para practicar Programación Orientada a Objetos, manejo de excepciones, logging y arquitectura por capas.
