ConectaBarrio - MVP Comunitario

- Problemática:

La organización social RedManos, apoya la participación de los lideres comunitarios de cada barrio con la gestión de solicitudes de cada barrio. Actualmente tienen multiples funentes de solicitudes. Reciben estas solicitues por medio de llamadas, mensajes, correos y notas personales. Este modelo de negocio causa problemas para recibir, priorizar, gestionar y confirmar las solicitudes, además que se dificulta el poder visualizar las solicitudes de manera ordenada y estructurada para análisis de datos. 

- Objetivo

Construir una aplicación web, desplegable en computador o celular que permita hacer consultas de solicitudes comunitarias y de los lideres comunitarios.


- Usuario Principal: Coordinador de la organización RedManos

- Funciones impresindibles del MVP:

    1. Consultar Lista de solicitudes registradas en la plataforma. 

    2. Buscar Solicitudes y Lideres comunitarios especificos.

    3. Marcar Prioridad en solicitudes especificas.

    4. Registrar nuevas solicitudes. 

    5. Visulaizar lista de solicitudes y lista de lideres comunitarios.

- Elementos fuera de alcance:

     El propósito de un MVP es poder validar el  flujo, demostrar la arquitectura y preparar la futura integración de un backend propio. 
     Por lo tanto se excluyen: 

    1. Creación o modificación del backend

    2. Autenticación, JWT,guards, interceptores, roles, Spring Security.

    3. Pruebas automatizadas.

- Riesgo principal: Uso de API simulada

En una API simulada se utiliza JSONPlaceholder, que es una API REST online que devuelve datos ficticios en formato JSON. 
Esto facilita el levantamiento de un backkend simulado con CRUDS no persistentes(los datos se reinician al recargar). Omitir el uso de esta API rest es un riesgo ya que vende la idea de que el programa cuenta con endpoints persistentes y un backend montado. 