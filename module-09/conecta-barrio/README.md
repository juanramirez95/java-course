# **ConectaBarrio - MVP Comunitario**

**- Problemática:**

La organización social RedManos, apoya la participación de los lideres comunitarios de cada barrio con la gestión de solicitudes de cada barrio. Actualmente tienen multiples funentes de solicitudes. Reciben estas solicitues por medio de llamadas, mensajes, correos y notas personales. Este modelo de negocio causa problemas para recibir, priorizar, gestionar y confirmar las solicitudes, además que se dificulta el poder visualizar las solicitudes de manera ordenada y estructurada para análisis de datos. 

## **- Objetivo**

Construir una aplicación web, desplegable en computador o celular que permita recibir,priorizar, gestionar y hacer consultas de solicitudes comunitarias y de los lideres comunitarios.


### **- Usuario Principal: Coordinador de la organización RedManos**

### **- Funciones impresindibles del MVP:**

    1. Consultar Lista de solicitudes registradas en la plataforma. 

    2. Buscar Solicitudes y Lideres comunitarios especificos.

    3. Marcar Prioridad en solicitudes especificas.

    4. Registrar nuevas solicitudes. 

    5. Visulaizar lista de solicitudes y lista de lideres comunitarios.

### **- Elementos fuera de alcance:**

     El propósito de un MVP es poder validar el  flujo, demostrar la arquitectura y preparar la futura integración de un backend propio. 
     Por lo tanto se excluyen: 

    1. Creación o modificación del backend

    2. Autenticación, JWT,guards, interceptores, roles, Spring Security.

    3. Pruebas automatizadas.

## **- Riesgo principal: Uso de API simulada**

En una API simulada se utiliza JSONPlaceholder, que es una API REST online que devuelve datos ficticios en formato JSON. 
Esto facilita el levantamiento de un backkend simulado con CRUDS no persistentes(los datos se reinician al recargar). Omitir el uso de esta API rest es un riesgo ya que vende la idea de que el programa cuenta con endpoints persistentes y un backend montado. 

## **Técnologías Utilizadas**
    1.**Frontend**: Angular,TypeScript,HTML y CSS.
    2.**Gestión de estados y datos**: Signals y computed,  HttpClient.
    3. **Backend/datos**: JsonPlaceholder como API REST simnulada.
    4. **Herramientas**: Angular CLI,npm,Git y GitHub (para control de versiones).

## **Mapa de rutas**##

| Ruta | Página | Propósito | Carga | Cómo se llega |
|:-----|:-------|:----------|:------|:--------------|
| `/` | Inicio | Dar contexto, mostrar indicadores (total de solicitudes y prioritarias) y ofrecer accesos rápidos. | Directa: es la puerta de entrada y la primera pantalla que se ve, por eso va en el paquete inicial. | Menú lateral y logo. |
| `/solicitudes` | Listado de solicitudes | Consultar, buscar y recorrer las solicitudes registradas. | Diferida (lazy loading): solo se descarga cuando se abre. | Menú lateral, botones de Inicio y botón "volver a listado" del detalle. |
| `/solicitudes/nueva` | Nueva solicitud | Registrar una solicitud mediante formulario (POST simulado). | Diferida. | Menú lateral y botones de Inicio. |
| `/solicitudes/prioritarias` | Solicitudes prioritarias | Ver solo las solicitudes marcadas como prioritarias. | Diferida. | Botón "Ver" del indicador de prioritarias en Inicio. |
| `/solicitudes/:id` | Detalle de solicitud | Abrir un caso por su id: el id llega como texto desde la URL y se convierte a número. Si no existe, muestra un mensaje y un enlace para volver al listado. | Diferida. | Clic en una tarjeta del listado o de prioritarias. |
| `/lideres` | Líderes | Consultar los líderes comunitarios y sus datos de contacto. | Diferida. | Menú lateral y botones de Inicio. |

### **Orden de las rutas**
El orden de las rutas establecidas influye en el comportamiento del MVP. Las rutas "nueva" y "prioritarias" van antes de ":id"  con el fin de el Router tome la primera ruta con la que coincide. De esta manera se cumple la regla de ir de lo más específico a los más general.



## **Arquitectura de carpetas y responsabilidades**

    conecta-barrio/
├── images/                          # Capturas y diagrama de flujo del README
├── public/
│   └── favicon.ico
├── src/
│   ├── app/
│   │   ├── assets/                  # Logos e íconos SVG
│   │   ├── components/
│   │   │   └── solicitud-card/      # Tarjeta reutilizable de solicitud
│   │   ├── models/
│   │   │   ├── lider.model.ts
│   │   │   └── solicitud.model.ts
│   │   ├── pages/
│   │   │   ├── inicio/
│   │   │   ├── solicitudes-listado/
│   │   │   ├── solicitudes-prioritario/
│   │   │   ├── solicitud-nueva/
│   │   │   ├── solicitud-detalle/
│   │   │   └── lideres-listado/
│   │   ├── services/
│   │   │   ├── solicitudes.service.ts
│   │   │   └── lideres.service.ts
│   │   ├── app.config.ts            # Proveedores globales: router y HttpClient
│   │   ├── app.routes.ts            # Tabla de rutas y carga diferida
│   │   ├── app.ts / app.html / app.css   # Cascarón, menú y router-outlet
│   ├── index.html
│   ├── main.ts
│   └── styles.css                   # Tokens visuales y estilos globales
├── angular.json
├── package.json
└── README.md

## **API utilizada, endpoints y limitación del POST simulado**

    1. API utilizada: https://jsonplaceholder.typicode.com
    2. endpoints: GET /users, GET /posts, POST /posts.
    3. Limitación del POST simulado: Al utilizar un POST simulado, los dato se pierden al recargar el navegador. Devuelve el mismo Id para todos los POST y los datos no se guardan.

## **Decisiones sobre Signals, estado compartido e Input/Output.**
    ## Signals y computed

### **Signals**

| Ubicación | Signal | Contiene |
|:----------|:-------|:---------|
| `SolicitudesService` | `listaSolicitudes` | Lista de solicitudes cargadas desde la API (privado) |
| `SolicitudesService` | `idsPrioritarios` | Ids de las solicitudes marcadas como prioritarias (privado) |
| `SolicitudesService` | `cargandoSignal` | Indica si la petición está en curso (privado) |
| `SolicitudesService` | `errorSignal` | Mensaje de error o `null` (privado) |
| `LideresService` | `listaLideres` | Lista de líderes cargados desde la API (privado) |
| `LideresService` | `cargandoSignal` | Indica si la petición está en curso (privado) |
| `LideresService` | `errorSignal` | Mensaje de error o `null` (privado) |
| `SolicitudesListado` | `busqueda` | Texto del buscador por asunto |
| `SolicitudesListado` | `busquedaBody` | Texto del buscador por descripción |
| `LideresListado` | `busquedaLider` | Texto del buscador de líderes |

Los servicios exponen sus signals a las páginas solo para lectura mediante `asReadonly()`: `solicitudes`, `asuntosMarcados`, `cargando` y `error` en `SolicitudesService`; `lideres`, `cargando` y `error` en `LideresService`.

### **Computed**

| Ubicación | Computed | Qué calcula |
|:----------|:---------|:------------|
| `SolicitudesService` | `totalPrioritarios` | Cantidad de solicitudes prioritarias; alimenta el contador global y el tablero de Inicio |
| `Inicio` | `solicitudesTotales` | Total de solicitudes registradas |
| `SolicitudesListado` | `solicitudesFiltradas` | Solicitudes que coinciden con la búsqueda |
| `SolicitudesPrioritario` | `asuntosPrioritarios` | Solicitudes cuyo id está marcado como prioritario |
| `LideresListado` | `lideresFiltrados` | Líderes cuyo nombre coincide con la búsqueda |
| `SolicitudDetalle` | `solicitud` | Solicitud que corresponde al id de la URL |
| `SolicitudDetalle` | `userId` | Id del líder de esa solicitud |
| `SolicitudDetalle` | `lideresFiltrados` | Líder relacionado con la solicitud |
| `SolicitudDetalle` | `esPrioritario` | Indica si la solicitud está marcada como prioritaria |

### **Matriz de Traducción**


## **Campos de la solicitud**

| Campo (API) | Etiqueta visible | Comportamiento |
|:------------|:-------------|:---------------|
| `title`  | Asunto | Ancho máximo de `30ch`. Si el texto excede ese límite, se oculta el sobrante. |
| `body`   | Descripción | No aparece en el listado. Se delimita a `30ch` por línea y hace salto de línea. |
| `userId` | Lider relacionado | userId es un campo de la solicitud y corresponde al id del líder comunitario. Se deriva del computed solicitud. Con ese valor se busca en la lista de líderes el líder relacionado y se muestra su nombre. Si la solicitud o el líder no existen, el valor queda vacío.  |
| `id`     | Caso # | Maneja el estado de carga del caso (ver tabla siguiente). |

## **Estados de carga del caso**

| Estado | Qué se muestra |
|:-------|:---------------|
| Cargando | Mensaje "Cargando solicitudes desde el servidor" |
| Error | Mensaje: "No pudimos cargar el servidor de ConectaBarrio. Verifica que haya conexión a internet" |
| Con datos, pero el id no aparece | Mensaje "No se encontró la solicitud bajo este ID"|


## **Campos de Líder**

| Campo (API) | Etiqueta visible|Comportamiento |
|:------------|:-------------|:---------------|
| `name` | Nombre | Aparece como líder relacionado en las solicitudes y en el listado de líderes. |
| `email` | Email | Se muestra como dato de contacto. |
| `phone` | Teléfono | Se muestra como dato de contacto. |
| `address.city` | Barrio | Se muestra como dato de contacto. |

Los estados de solicitudes y lideres viven en los service de cada uno, con el fin de que solo exista una fuente de información respectiva y así evitar datos duplicados o desactualizados. Sus datos se encuentran en signals privadas con el fin de que no puedan ser modificadas de manera externa, y solo se exponen para lectura asReadonly(). Las páginas pueden leerlos, y solo pueden hacer modificaciones a través de los métodos de los servicios. 

Los buscadores son locales porque los datos que consultan solo importan en las páginas en las que se encuentran. Por los Input viajan valores simples; El padre lee los estados y entrega los valores al hijo. El Output viaja del hijo al padre y solo emite una intención del usuario. Por ejemplo, el usuario decide qué solicitudes son prioritarias al marcarlas. Se implementa "alClicPrioritario(evento: Event)" en el componente mascota ya que corta el evento bubbling; esta implementación incluye stopPropagation()(evita que el clic suba hasta el enlace) y preventDefault()(cancela la acción por defecto del enlace) que aseguran que el clic se quede en el botón de acción y no suba hasta el enlace que contiene la tarjeta.

Las tarjetas "solicitud-card" son componentes, pues están pensadas para ser reutilizadas en diferentes páginas, manteniendo su propia lógica y estilo. Cada página que las invoque, puede decidir que datos mostrar. En la página "solicitudes-listado", la descripción de las solicitudes no se muestra por completo, mientras que en la página "solicitudes-prioritario" el asunto muestra la descripción por completo.

## **Pruebas manuales ejecutadas.**

### **Diagrama de Flujo:**

![Diagrama de flujo Consulta de Solicitudes RedManos, atribuido a Juan Camilo Ramirez Ruiz y fechado October 13, 2026. Sobre fondo blanco, formas blancas y naranjas conectadas por flechas muestran el proceso del coordinador. Desde Inicio, el COORDINADOR elige Registrar o consultar solicitudes. Para registrar, llena Nombre del líder, Asunto y Descripción; si los campos no están completos, vuelve al formulario, y si están completos, confirma y agrega la solicitud a la lista. Para consultar, abre la Lista de solicitudes y consulta detalles de la solicitud; decide ¿Es Prioridad? y, si la respuesta es Sí, la marca como prioridad. Después puede consultar otras solicitudes y llegar al Fin. Las etiquetas visibles incluyen Registrar, Consultar, ¿Campos de datos completos?, Sí, No, Marcar como prioridad y Confirmar y agregar solicitud a la lista. A la derecha se lee Descripción: Este diagrama de flujo es un ejemplo de cómo es el proceso de creación y consulta de solicitudes en un MVP comunitario. El diseño despejado y organizado tiene un tono instructivo.](images/diagrama-flujo.jpeg)


### **Página Responsive en diferentes tamaños**

La barra lateral se inicializa cerrada, al hacer clic en el icono del menu, se despliega hacia la derecha mostrando los botones de "inicio", Solicitudes","lideres". 

Cuando el ancho de la pantalla es 500 o menos, el comportamiento de la barra cambia. Primero, la visibilidad de la barra desaparece, mostrando una pantalla limpia, solo conservando la barra horizontal que se mantiene visible cuando se hace scroll. Segundo, al desplegar el menu vertical, los iconos de los botones desaparecen y solo se muestran los nombres.

**Navegador web:**
![Desktop web application interface with a dark blue sidebar on the left, a RedManos logo and user icon at the top, and the text Hola, RedManos. Three blue navigation buttons are labeled Inicio, Solicitudes, and Líderes. The main content area is a large light gray canvas with no visible content, creating a clean empty dashboard layout.](images/tamaño_NavegadorWeb.png)

**Tamaño 390x1280:**
![Mobile phone mockup showing the RedManos app interface in portrait format. At the top left, a menu icon sits beside the RedManos logo and the word RedManos, with a circular user icon at the top right. The main screen area is a large light gray panel with no visible content. The phone is centered on a dark gray background, and a simple bottom navigation area with interface icons appears near the lower edge. The overall mood is clean, minimal, and professional, suitable for a responsive dashboard for community request management.](images/inicio_390x1280.png)

**Tamaño 390x1280 menu desplegado:**
![RedManos mobile app interface in a narrow portrait layout with a dark blue sidebar, a left-side menu, and a large light gray content area. At the top of the sidebar, the RedManos logo and a circular user icon are visible. The app displays the greeting Hola, RedManos with three blue navigation buttons labeled Inicio, Solicitudes, and Líderes. The design is clean, minimal, and professional, set on a dark gray background that simulates a mobile device frame. Visible text includes RedManos, Hola, RedManos, Inicio, Solicitudes, and Líderes.](images/390x1280.png)




### **Página de inicio reactiva**

## **Tablero de Inicio**
![Panel de inicio de ConectaBarrio en un navegador. En la barra superior aparecen un icono de regreso, el logotipo ConectaBarrio y un icono de usuario. La barra lateral azul oscuro muestra Hola, ConectaBarrio y los botones Inicio, Solicitudes, Nueva solicitud, Lideres y Cerrar sesión. En el área principal se lee Bienvenido a ConectaBarrio y El sistema que puede gestionar solicitudes y consultas de las comunidades de su ciudad. Más abajo, un texto explica que se pueden gestionar solicitudes existentes, priorizar las más importantes, registrar nuevas y consultar datos de contacto de líderes comunitarios. Otra tarjeta ofrece Registrar una nueva solicitud y Lista de Lideres. Dos indicadores muestran Total de Solicitudes, 8, con el botón Ver, y Solicitudes prioritarias, 0, también con el botón Ver. La interfaz clara, ordenada y en tonos azul y blanco transmite un tono acogedor y profesional.](images/Pagina-Inicio.png)

## **Tablero de Inicio conectado al estado**
![Panel de inicio de ConectaBarrio conectado y abierto en localhost:4200. En la parte superior aparecen el logotipo ConectaBarrio, el indicador Solicitudes Prioritarias: 3 y un icono de usuario. La barra lateral azul oscuro muestra Hola, ConectaBarrio y los botones Inicio, Solicitudes, Nueva solicitud, Lideres y Cerrar sesión. En el área principal se lee Bienvenido a ConectaBarrio y El sistema que puede gestionar solicitudes y consultas de las comunidades de su ciudad. Una tarjeta explica que se puede gestionar solicitudes, priorizarlas, registrar nuevas y consultar datos de líderes comunitarios. Otra ofrece los botones Registrar una nueva solicitud y Lista de Lideres. Dos tarjetas indican Total de Solicitudes: 8 y Solicitudes prioritarias: 3, ambas con un botón Ver. La interfaz despejada, de fondo claro y detalles azules, transmite un tono profesional y acogedor.](images/Pagina-inicio-conectado.png)


### **Implementación de HttpClient, GETTERS y POST**

## **Mensaje de carga**

![Pantalla de ConectaBarrio mientras se cargan los datos. En la aplicación, el encabezado muestra el logotipo ConectaBarrio y la barra lateral azul oscuro contiene cinco botones de navegación con iconos. El área principal presenta el título Listado de Líderes, un campo con el texto Buscar Lider por nombre y un aviso azul que dice Cargando solicitudes desde el servidor. A la derecha, las herramientas de desarrollo del navegador muestran la pestaña Network, con solicitudes como posts y users, y varios estados 200; debajo, la consola dice Angular is running in development mode. El diseño es claro y ordenado, y la captura documenta la espera de datos y las solicitudes de red.](images/cargando.png)
## **Interfaz de error y recuperación**##
![Pantalla de ConectaBarrio en localhost:4200/solicitudes/1000. En el área principal aparece un aviso rojo con el texto No se encontró la solicitud bajo este ID. y, debajo a la izquierda, un botón que dice volver a listado. La barra lateral azul oscuro muestra cinco botones azules con iconos de inicio, solicitudes, nueva solicitud, líderes y regreso; arriba aparecen el logotipo ConectaBarrio y un icono de usuario. El entorno es una interfaz web clara y despejada, y el mensaje presenta el error de forma directa e informativa.](images/error-recuperacion.png)
## **Network para una petición exitosa**##
![ConectaBarrio muestra el formulario Registrar nueva solicitud en una vista responsive. Incluye los campos Asunto, Descripción y Líder..., y el botón Registrar Solicitud. La barra lateral dice Hola, ConectaBarrio y ofrece Inicio, Solicitudes, Nueva solicitud, Líderes y Cerrar sesión; en el encabezado aparecen ConectaBarrio y Solicitudes Prioritarias: 3. A la derecha, las herramientas de desarrollo del navegador están abiertas en Network y muestran dos solicitudes posts, con estados 201 y 204. El entorno de escritorio documenta un envío exitoso en una interfaz clara y ordenada, de tono profesional.](images/solicitud-network.png)



## **URL de despliegue**


