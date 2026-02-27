 Backend - Sistema de Gestión "Colectivo Cultural Ikuna"

Este repositorio contiene la API REST desarrollada para la gestión integral del Colectivo Cultural Ikuna. El sistema está diseñado para digitalizar, centralizar y optimizar la administración operativa y financiera de la organización, brindando herramientas robustas para el control de proyectos, presupuestos y equipos de trabajo.

 Funcionalidades Principales
El sistema expone servicios para los siguientes módulos de negocio:

-Gestión de Proyectos Culturales:  Permite la administración completa del ciclo de vida de los proyectos. Incluye la creación de iniciativas, categorización (Festivales, Talleres, Resignificación de Espacios), seguimiento de fechas y control automatizado de estados (*Pendiente, En Progreso, Completado*).
-Control Presupuestal y Financiero: Motor de seguimiento de gastos asociado a cada proyecto. Calcula automáticamente saldos disponibles, actualiza el progreso de ejecución financiera en tiempo real y cuenta con validaciones de negocio que impiden el registro de gastos cuando el presupuesto ha sido agotado al 100%.
-Coordinación de Equipo y Tareas:Gestión de cronogramas y talento humano. Facilita la asignación de colaboradores a proyectos específicos con roles determinados, y la creación de tareas con fechas límite y responsables.
-Autenticación y Control de Accesos: Sistema de seguridad basado en roles (Super Administrador y Colaborador). Incluye flujos de solicitud de registro, aprobación/rechazo de usuarios, inhabilitación de cuentas y protección de endpoints.

Tecnologías, Frameworks y Librerías
El desarrollo se apoya en un ecosistema moderno y robusto de Java:

-Lenguaje: Java 21
-Framework Principal: Spring Boot 3.x
-Spring Web: Para la creación de la API REST.
-Spring Data JPA / Hibernate:* Para la persistencia de datos (ORM).
-Spring Security: Para la protección de rutas y endpoints.
-Spring Validation: Para la validación estricta de datos de entrada.
-Base de Datos: MySQL 8
-Librerías Clave:
MapStruct (v1.5.5.Final): Generador de código para el mapeo seguro y de alto rendimiento entre DTOs y Entidades en tiempo de compilación.
-Lombok: Reducción de código boilerplate (Getters, Setters, Constructores).
Gestión de Dependencias: Maven

Arquitectura del Software

El backend fue construido aplicando los principios de Clean Architecture y Arquitectura Hexagonal (Puertos y Adaptadores). Esto garantiza un código altamente escalable, mantenible, testeable y con un bajo nivel de acoplamiento. El proyecto se estructura en tres capas fundamentales:

1. `Domain` (Capa de Dominio)
Es el núcleo del sistema. Es completamente agnóstica a frameworks externos (como Spring o MySQL) y contiene:
`Model` (Entidades): Clases puras de Java que representan los objetos reales del negocio (`CulturalProject`, `Budget`, `IkunaUser`, `Task`, etc.).
`Port.out` (Puertos de Salida): Interfaces que definen los contratos para comunicarse con la base de datos (Ej: `IkunaUserRepositoryPort`). Dictan qué se necesita guardar o buscar, pero no cómo se hace.

2. `Application` (Capa de Aplicación)
Orquesta las reglas lógicas del negocio y el flujo de la información. Sus responsabilidades se dividen en:
UseCases` (Casos de Uso):Contienen la lógica de negocio pura. Aquí se aplican reglas como impedir que un presupuesto quede en negativo o validar que un usuario no apruebe proyectos inactivos (Ej: `BudgetUseCase`, `IkunaManagerUseCase`).
`DTOs` (Data Transfer Objects): Objetos planos utilizados para recibir datos desde el Frontend (Request) o enviar respuestas (Response). Aseguran que las entidades reales de la base de datos nunca se expongan al exterior.
`Mappers`: Interfaces que utilizan MapStruct para traducir automáticamente los datos de un `DTO` a un `Model` y viceversa.

3. `Infrastructure` (Capa de Infraestructura)
Es la capa más externa, encargada de comunicarse con las herramientas de software (React, MySQL, etc.). Se compone de:
`input.rest` (Controladores REST): Los adaptadores de entrada. Reciben las peticiones HTTP (GET, POST, PUT, DELETE) desde el Frontend, validan la solicitud y delegan el trabajo a los `UseCases`.
`output.persistence` (Adaptadores de Salida): Implementan los puertos definidos en el Dominio. Aquí es donde los `Repository` de Spring Data JPA ejecutan las consultas reales en MySQL (Ej: `CulturalProjectRepositoryAdapter`).
`exception` (Manejo de Errores): Un `GlobalExceptionHandler` (`@RestControllerAdvice`) que intercepta cualquier error del sistema y devuelve respuestas HTTP (Ej. 400 Bad Request) limpias y legibles para el cliente.
`configuration`: Archivos de inyección de dependencias (`Beans`), configuración de base de datos, CORS y filtros de seguridad.
