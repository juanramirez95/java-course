# Gestión de Inventario - Tienda con Múltiples Almacenes

Sistema de consola en Java para gestionar el inventario de una tienda que opera con varios almacenes. Permite registrar productos en un catálogo general, dar de alta almacenes, asignar productos a almacenes específicos y controlar la cantidad de existencias de cada producto de forma independiente por almacén.

## Índice

- [Descripción general](#descripción-general)
- [Requisitos funcionales cubiertos](#requisitos-funcionales-cubiertos)
- [Arquitectura del proyecto](#arquitectura-del-proyecto)
- [Modelo de datos](#modelo-de-datos)
- [Manejo de errores](#manejo-de-errores)
- [Sistema de logging](#sistema-de-logging)
- [Requisitos previos](#requisitos-previos)
- [Cómo compilar y ejecutar](#cómo-compilar-y-ejecutar)
- [Uso del menú](#uso-del-menú)
- [Decisiones de diseño y limitaciones conocidas](#decisiones-de-diseño-y-limitaciones-conocidas)

## Descripción general

Cada **almacén** tiene un identificador único, nombre, ubicación, y su propia colección de productos con cantidades independientes. Cada **producto** en el catálogo general tiene un identificador único, nombre, precio y una cantidad de referencia. Cuando un producto se asigna a un almacén, se crea una copia independiente de ese producto (con su propio contador de cantidad), de modo que actualizar el stock en un almacén no afecta el stock de ese mismo producto en otro almacén.

## Requisitos funcionales cubiertos

**Gestión de productos (dentro de un almacén):**
- Registrar un nuevo producto en el catálogo general, validando datos (ID, nombre, precio y cantidad no negativos), que el catálogo no esté lleno y que el ID no esté duplicado.
- Asignar un producto existente del catálogo a un almacén específico, validando que el producto exista y que no esté ya asignado a ese almacén.
- Actualizar la cantidad de existencias de un producto dentro de un almacén, validando que el almacén y el producto existan y que la cantidad no sea negativa.
- Buscar un producto por su ID (a nivel de catálogo).
- Mostrar todos los productos de un almacén (como parte de "Mostrar todos los almacenes").

**Gestión de almacenes (a nivel de sistema):**
- Registrar un nuevo almacén, validando datos, que el sistema no exceda el máximo de almacenes permitidos y que el ID no esté duplicado.
- Buscar un almacén por su ID.
- Asignar un producto a un almacén específico (localiza el almacén y delega la asignación).
- Actualizar la cantidad de un producto en un almacén específico (localiza el almacén y delega la actualización).
- Mostrar todos los almacenes registrados junto con sus productos y cantidades.

## Arquitectura del proyecto

```
src/main/java/com/juan/
├── SistemaGeneral.java                # Capa de interacción con el usuario (menú, Scanner, try/catch)
└── ejercicio/
    ├── Main.java                      # Punto de entrada, arma las dependencias y corre el menú
    ├── model/
    │   ├── Producto.java              # Entidad producto (id, nombre, precio, cantidad)
    │   ├── Almacen.java                # Entidad almacén (id, nombre, ubicación, mapa de productos)
    │   └── TiendaGeneral.java          # Contenedor del mapa de almacenes del sistema
    ├── service/
    │   ├── IAlmacenService.java        # Contrato para operaciones del catálogo de productos
    │   ├── AlmacenServiceImpl.java      # Implementación: catálogo general de productos
    │   ├── ITiendaGeneralService.java  # Contrato para operaciones de almacenes
    │   └── TiendaGeneralServiceImpl.java # Implementación: gestión de almacenes y asignación de productos
    └── exceptions/
        ├── checked/
        │   └── CapacidadListaLlenaException.java  # Checked: catálogo o sistema de almacenes lleno
        └── uncheked/
            ├── InvalidDataException.java      # Datos de entrada inválidos o vacíos
            ├── IdAlreadyExistsException.java  # ID duplicado (producto o almacén)
            ├── ProductoNotFoundException.java # Producto no encontrado
            ├── AlmacenNotFoundException.java  # Almacén no encontrado
            └── ValorNegativoException.java    # Cantidad negativa no permitida
```

**Capas y responsabilidades:**
- **`model`**: clases de datos puras, sin lógica de negocio ni validaciones. Encapsulan su propio estado interno mediante métodos propios (`agregarProducto`, `existeProducto`, `buscarAlmacen`, etc.) en vez de exponer directamente sus colecciones internas para modificación externa.
- **`service`**: contiene toda la lógica de negocio y las validaciones (datos válidos, duplicados, capacidad máxima, existencia). Programado contra interfaces (`IAlmacenService`, `ITiendaGeneralService`) para desacoplar la lógica de negocio de su implementación concreta.
- **`SistemaGeneral`**: única capa que interactúa con el usuario por consola (`Scanner`, `System.out`). Traduce las excepciones de negocio en mensajes legibles para el usuario.
- **`exceptions`**: excepciones de negocio propias, separadas en *checked* (errores que obligan a decidir cómo manejarlos, como capacidad llena) y *unchecked* (errores de validación o de estado que se propagan como `RuntimeException`).

## Modelo de datos

| Entidad | Atributos |
|---|---|
| **Producto** | `productoId`, `nombre`, `precio`, `cantidad` |
| **Almacén** | `almacenId`, `nombreAlmacen`, `ubicacion`, `productos` (mapa de productos propios) |

Cada `Almacen` mantiene copias independientes de `Producto` (no la misma referencia del catálogo), para que la cantidad de un producto en un almacén sea completamente independiente de su cantidad en cualquier otro almacén.

**Límites de capacidad:**
- Catálogo general de productos: máximo 10 productos (`MAX_PRODUCTOS` en `AlmacenServiceImpl`).
- Sistema de almacenes: máximo 3 almacenes (`MAX_ALMACENES` en `TiendaGeneralServiceImpl`).

## Manejo de errores

El sistema define excepciones de negocio propias en vez de dejar propagar excepciones genéricas de Java, para que cada error comunique con precisión qué salió mal:

| Excepción | Tipo | Cuándo se lanza |
|---|---|---|
| `InvalidDataException` | Unchecked | Datos vacíos, nulos o con formato inválido |
| `IdAlreadyExistsException` | Unchecked | ID de producto o almacén ya registrado |
| `ProductoNotFoundException` | Unchecked | Producto no encontrado (catálogo o almacén) |
| `AlmacenNotFoundException` | Unchecked | Almacén no encontrado |
| `ValorNegativoException` | Unchecked | Cantidad negativa al actualizar stock |
| `CapacidadListaLlenaException` | Checked | Catálogo de productos o sistema de almacenes al máximo de su capacidad |

Adicionalmente, la interacción por consola captura `InputMismatchException` cuando el usuario ingresa un valor no numérico donde se esperaba un número.

## Sistema de logging

El proyecto usa **SLF4J** como fachada de logging, con **Log4j2** como implementación real (configurado en `pom.xml` y `src/main/resources/log4j2.xml`). El código de negocio programa contra la API de SLF4J (`org.slf4j.Logger`), no directamente contra Log4j2, para poder cambiar de implementación sin modificar las clases de servicio.

**Salidas configuradas:**
- **Consola**: todos los eventos de nivel `INFO` o superior, útil durante el desarrollo.
- **Archivo** (`logs/application.log`): mismo nivel, con **rotación automática por tamaño**. Cuando el archivo activo alcanza **10 MB**, se comprime y archiva en `logs/archive/` (hasta un máximo de 10 archivos históricos, después de lo cual se descartan los más antiguos), y `application.log` comienza vacío de nuevo.

**Criterio de niveles usado en el código:**

| Nivel | Cuándo se usa |
|---|---|
| `INFO` | Operación de negocio completada con éxito (producto registrado, almacén creado, stock actualizado) |
| `WARN` | Error esperado por validación de negocio (datos inválidos, ID duplicado, capacidad llena, entidad no encontrada) |
| — | Los mensajes de error dirigidos al usuario (`System.out`) se mantienen en la capa de interacción (`SistemaGeneral`); el detalle técnico de cada fallo se registra una sola vez, en la capa de `service` donde ocurre, para no duplicar el mismo evento en el log |

## Requisitos previos

- JDK 21 o superior
- Apache Maven

## Cómo compilar y ejecutar

**Compilar el proyecto** (descarga las dependencias de SLF4J/Log4j2 y compila las clases):
```bash
mvn clean compile
```

**Ejecutar desde el IDE (recomendado durante el desarrollo):**
Ejecuta la clase `com.juan.ejercicio.Main` directamente desde tu editor (por ejemplo, con el botón "Run" de VS Code o IntelliJ, teniendo el proyecto importado como proyecto Maven). El IDE arma automáticamente el classpath con las dependencias necesarias.

**Ejecutar desde terminal como jar autónomo:**
Requiere tener configurado un plugin de empaquetado con dependencias (por ejemplo, `maven-shade-plugin`) en el `pom.xml`, ya que por defecto el jar generado por `mvn package` no incluye las dependencias de SLF4J/Log4j2. Una vez configurado:
```bash
mvn clean package
java -jar target/gestion-inventario-tienda-1.0-SNAPSHOT.jar
```

Al iniciar, el sistema carga automáticamente datos de demostración (8 productos y 3 almacenes con productos ya asignados) mediante `cargarDatosDemo()`, para no tener que registrar todo manualmente antes de probar el sistema.

## Uso del menú

```
--- MENU INVENTARIO GENERAL TIENDA---
1. Registrar producto (catálogo general)
2. Registrar almacén
3. Asignar producto existente a un almacén
4. Agregar stock a producto en un almacén
5. Buscar producto por ID (catálogo)
6. Buscar almacén por ID
7. Mostrar todos los almacenes y su stock
0. Salir
```

Cada opción valida sus datos de entrada y muestra un mensaje de error descriptivo si algo falla (dato inválido, ID duplicado, entidad no encontrada, capacidad llena, o valor no numérico ingresado).

## Decisiones de diseño y limitaciones conocidas

- **Catálogo global vs. stock por almacén**: el ID de un producto es único a nivel de todo el sistema (catálogo general en `AlmacenServiceImpl`), no solo dentro de un almacén. Lo que sí es completamente independiente por almacén es la **cantidad** de ese producto, gracias a que cada `Almacen` guarda su propia copia (`new Producto(productoCatalogo)`) en lugar de una referencia compartida.
- **Cantidad en el catálogo**: el valor de `cantidad` que se ingresa al registrar un producto en el catálogo general es solo un dato de referencia inicial; no refleja el stock real de ningún almacén una vez que el producto ha sido asignado y su cantidad actualizada en uno o más almacenes.
- **Encapsulamiento de colecciones**: los getters de colecciones (`getAlmacenes()`, `getProductos()`) devuelven copias defensivas y se usan únicamente para lectura/iteración. Toda modificación de estado se realiza a través de métodos dedicados de la propia clase (`agregarAlmacen`, `agregarProducto`, `existeProducto`, etc.), nunca operando directamente sobre el resultado de un getter.
