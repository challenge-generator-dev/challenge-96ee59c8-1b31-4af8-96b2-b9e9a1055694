# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/SolicitudJpaRepository.java` — `com.prestamos.infrastructure.adapters.out.persistence.entity.SolicitudEntityMapper`: El import com.prestamos.infrastructure.adapters.out.persistence.entity.SolicitudEntityMapper usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/prestamos/application/service/EnrutarSolicitudService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/prestamos/infrastructure/adapters/in/web/SolicitudController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/prestamos/infrastructure/config/LoggingConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/prestamos/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/prestamos/infrastructure/adapters/in/web/SolicitudControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/prestamos/application/service/EnrutarSolicitudService.java` — `Solicitud.cliente`: Se invoca `cliente` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/application/service/EnrutarSolicitudService.java` — `Solicitud.monto`: Se invoca `monto` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/application/service/EnrutarSolicitudService.java` — `Solicitud.idTransaccion`: Se invoca `idTransaccion` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/application/service/EnrutarSolicitudService.java` — `Solicitud.estado`: Se invoca `estado` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/application/service/EnrutarSolicitudService.java` — `Cliente.nombre`: Se invoca `nombre` sobre `Cliente`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/application/service/EnrutarSolicitudService.java` — `Cliente.puntajeCrediticio`: Se invoca `puntajeCrediticio` sobre `Cliente`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/in/web/SolicitudController.java` — `SolicitudRequest.clienteId`: Se invoca `clienteId` sobre `SolicitudRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/in/web/SolicitudController.java` — `SolicitudRequest.monto`: Se invoca `monto` sobre `SolicitudRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/in/web/SolicitudController.java` — `SolicitudRequest.cliente`: Se invoca `cliente` sobre `SolicitudRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/in/web/SolicitudController.java` — `SolicitudRequest.idTransaccion`: Se invoca `idTransaccion` sobre `SolicitudRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/in/web/SolicitudController.java` — `Solicitud.id`: Se invoca `id` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/in/web/SolicitudController.java` — `Solicitud.estado`: Se invoca `estado` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/in/web/SolicitudController.java` — `SolicitudDuplicadaException.getMessage`: Se invoca `getMessage` sobre `SolicitudDuplicadaException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/in/web/SolicitudController.java` — `Solicitud.monto`: Se invoca `monto` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Solicitud.id`: Se invoca `id` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Solicitud.idTransaccion`: Se invoca `idTransaccion` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Solicitud.estado`: Se invoca `estado` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Solicitud.monto`: Se invoca `monto` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Solicitud.cliente`: Se invoca `cliente` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Solicitud.fechaCreacion`: Se invoca `fechaCreacion` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Solicitud.fechaActualizacion`: Se invoca `fechaActualizacion` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Cliente.toDomain`: Se invoca `toDomain` sobre `Cliente`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Cliente.nombre`: Se invoca `nombre` sobre `Cliente`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Cliente.email`: Se invoca `email` sobre `Cliente`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Cliente.puntajeCrediticio`: Se invoca `puntajeCrediticio` sobre `Cliente`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java` — `Cliente.canalOrigen`: Se invoca `canalOrigen` sobre `Cliente`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/prestamos/application/service/EnrutarSolicitudServiceTest.java` — `SolicitudRepository.existePorTransaccion`: Se invoca `existePorTransaccion` sobre `SolicitudRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/prestamos/application/service/EnrutarSolicitudServiceTest.java` — `Solicitud.estado`: Se invoca `estado` sobre `Solicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/prestamos/infrastructure/adapters/in/web/SolicitudControllerTest.java` — `EnrutarSolicitudService.buscarPorId`: Se invoca `buscarPorId` sobre `EnrutarSolicitudService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Misión / candidato
Reto de evaluación de la competencia descrita.

### Datos adicionales
El candidato es un desarrollador Backend Advanced con experiencia en estructuras de datos y patrones GRASP. Está listo para profundizar en frameworks como Spring Boot.

### Reto
- Tema: Sólida Experiencia en Frameworks
- Seniority: advanced-l2
- Tipo: practical
- Título: Evaluación de Competencia en Frameworks
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Enrutamiento de Solicitudes — objetivo: Implementar la funcionalidad básica para enrutar solicitudes de clientes a los servicios correspondientes. — entregable (NO resolver): Sistema que enruta solicitudes de clientes a servicios correspondientes de forma idempotente.
- Fase 2: Control de Variables de Entorno — objetivo: Implementar el control de variables de entorno para el sistema. — entregable (NO resolver): Sistema que lee y utiliza variables de entorno para configurarse correctamente.
- Fase 3: Generación de Registros de Trazas — objetivo: Implementar la generación de registros de trazas para el sistema. — entregable (NO resolver): Sistema que genera registros de trazas útiles para la depuración y el seguimiento de problemas.
- Fase 4: Conexión a Base de Datos con ORM — objetivo: Implementar la conexión a una base de datos utilizando un ORM. — entregable (NO resolver): Sistema que se conecta a una base de datos y realiza operaciones CRUD utilizando un ORM.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.prestamos</groupId>
    <artifactId>prestamos</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>prestamos</name>
    <description>Servicio de gestión de préstamos</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi-ui.version>2.5.0</springdoc-openapi-ui.version>
        <swagger-annotations.version>2.2.20</swagger-annotations.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>${swagger-annotations.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi-ui.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

//

// === ARCHIVO: src/main/java/com/prestamos/PrestamosApplication.java ===
package com.prestamos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class PrestamosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrestamosApplication.class, args);
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }
}

//

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: prestamos-service
  datasource:
    url: jdbc:postgresql://localhost:5432/prestamos_db
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:postgres}
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 20000
      maximum-pool-size: 5
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        use_sql_comments: true
        jdbc:
          batch_size: 10
          order_inserts: true
          order_updates: true
  actuator:
    endpoints:
      web:
        exposure:
          include: health,info,metrics,loggers,env,beans,configprops
    endpoint:
      health:
        show-details: always
        probes:
          enabled: true
    health:
      livenessState:
        enabled: true
      readinessState:
        enabled: true

logging:
  level:
    root: INFO
    org.springframework.web: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    com.prestamos: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/prestamos-service.log

app:
  idempotency:
    header-name: X-Idempotency-Key
    timeout-minutes: 5
  channels:
    web: WEB
    mobile: MOBILE
    api: API

// === ARCHIVO: src/main/java/com/prestamos/domain/model/Cliente.java ===
package com.prestamos.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

/**
 * Entidad de dominio que representa a un cliente con sus atributos esenciales.
 * Los clientes son identificados por un UUID único y deben tener información válida.
 */
public record Cliente(
    @NotNull(message = "El ID del cliente no puede ser nulo")
    UUID id,

    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    String nombre,

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    String email,

    @Positive(message = "El puntaje crediticio debe ser un valor positivo")
    int puntajeCrediticio,

    @NotBlank(message = "El canal de origen no puede estar vacío")
    String canalOrigen) {

    /**
     * Valida que el cliente tenga todos los campos requeridos para procesar una solicitud.
     * @throws IllegalArgumentException si algún campo obligatorio es inválido.
     */
    public void validar() {
        if (id == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico no puede estar vacío");
        }
        if (puntajeCrediticio <= 0) {
            throw new IllegalArgumentException("El puntaje crediticio debe ser un valor positivo");
        }
        if (canalOrigen == null || canalOrigen.trim().isEmpty()) {
            throw new IllegalArgumentException("El canal de origen no puede estar vacío");
        }
    }

    /**
     * Crea un nuevo cliente con los datos proporcionados.
     * @param nombre Nombre completo del cliente.
     * @param email Correo electrónico válido.
     * @param puntajeCrediticio Puntaje crediticio positivo.
     * @param canalOrigen Canal por el que se originó la solicitud.
     * @return Una nueva instancia de Cliente con un ID generado.
     */
    public static Cliente crearNuevo(String nombre, String email, int puntajeCrediticio, String canalOrigen) {
        return new Cliente(
            UUID.randomUUID(),
            nombre,
            email,
            puntajeCrediticio,
            canalOrigen
        );
    }
}

// === ARCHIVO: src/main/java/com/prestamos/domain/model/Solicitud.java ===
package com.prestamos.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidad de dominio que representa una solicitud de préstamo.
 * Cada solicitud tiene un estado, monto y un cliente asociado.
 */
public record Solicitud(
    @NotNull(message = "El ID de la solicitud no puede ser nulo")
    UUID id,

    @NotNull(message = "El ID de transacción no puede ser nulo")
    UUID idTransaccion,

    @NotNull(message = "El cliente asociado no puede ser nulo")
    Cliente cliente,

    @Positive(message = "El monto solicitado debe ser positivo")
    BigDecimal monto,

    @NotNull(message = "El estado de la solicitud no puede ser nulo")
    EstadoSolicitud estado,

    @NotNull(message = "La fecha de creación no puede ser nula")
    LocalDateTime fechaCreacion,

    LocalDateTime fechaActualizacion) {

    /**
     * Enum que representa los posibles estados de una solicitud.
     */
    public enum EstadoSolicitud {
        RECIBIDA,
        EN_PROCESO,
        APROBADA,
        RECHAZADA,
        FINALIZADA
    }

    /**
     * Valida que la solicitud tenga todos los campos requeridos.
     * @throws IllegalArgumentException si algún campo obligatorio es inválido.
     */
    public void validar() {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la solicitud no puede ser nulo");
        }
        if (idTransaccion == null) {
            throw new IllegalArgumentException("El ID de transacción no puede ser nulo");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente asociado no puede ser nulo");
        }
        cliente.validar();
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto solicitado debe ser positivo");
        }
        if (estado == null) {
            throw new IllegalArgumentException("El estado de la solicitud no puede ser nulo");
        }
        if (fechaCreacion == null) {
            throw new IllegalArgumentException("La fecha de creación no puede ser nula");
        }
    }

    /**
     * Crea una nueva solicitud con los datos proporcionados.
     * @param cliente Cliente asociado a la solicitud.
     * @param monto Monto solicitado.
     * @param idTransaccion ID único de la transacción.
     * @return Una nueva instancia de Solicitud con estado RECIBIDA y fechas inicializadas.
     */
    public static Solicitud crearNueva(Cliente cliente, BigDecimal monto, UUID idTransaccion) {
        return new Solicitud(
            UUID.randomUUID(),
            idTransaccion,
            cliente,
            monto,
            EstadoSolicitud.RECIBIDA,
            LocalDateTime.now(),
            null
        );
    }

    /**
     * Actualiza el estado de la solicitud.
     * @param nuevoEstado Nuevo estado de la solicitud.
     * @return Una nueva instancia de Solicitud con el estado actualizado.
     */
    public Solicitud actualizarEstado(EstadoSolicitud nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }
        return new Solicitud(
            this.id,
            this.idTransaccion,
            this.cliente,
            this.monto,
            nuevoEstado,
            this.fechaCreacion,
            LocalDateTime.now()
        );
    }
}

// === ARCHIVO: src/main/java/com/prestamos/domain/ports/in/EnrutarSolicitudUseCase.java ===
package com.prestamos.domain.ports.in;

import com.prestamos.domain.model.Solicitud;
import java.util.UUID;

/**
 * Puerto de entrada que define el contrato para el caso de uso de enrutamiento de solicitudes.
 * Este caso de uso se encarga de procesar las solicitudes de préstamo y determinar
 * el servicio correspondiente al que deben ser dirigidas.
 */
public interface EnrutarSolicitudUseCase {

    /**
     * Procesa una solicitud de préstamo y la enruta al servicio correspondiente.
     * @param solicitud La solicitud a procesar, con todos sus campos válidos.
     * @return La solicitud procesada con su estado actualizado.
     * @throws IllegalArgumentException si la solicitud no es válida.
     */
    Solicitud procesarSolicitud(Solicitud solicitud);

    /**
     * Verifica si una solicitud con el mismo ID de transacción ya ha sido procesada.
     * @param idTransaccion ID de la transacción a verificar.
     * @return true si ya existe una solicitud con ese ID de transacción, false en caso contrario.
     */
    boolean existeSolicitudPorTransaccion(UUID idTransaccion);
}

// === ARCHIVO: src/main/java/com/prestamos/domain/ports/out/SolicitudRepository.java ===
package com.prestamos.domain.ports.out;

import com.prestamos.domain.model.Solicitud;
import java.util.Optional;
import java.util.UUID;

public interface SolicitudRepository {
    
    Solicitud guardar(Solicitud solicitud);
    
    Optional<Solicitud> buscarPorId(UUID id);
    
    Optional<Solicitud> buscarPorIdTransaccion(UUID idTransaccion);
    
    boolean existePorIdTransaccion(UUID idTransaccion);
}

// === ARCHIVO: src/main/java/com/prestamos/infrastructure/adapters/out/persistence/SolicitudJpaRepository.java ===
package com.prestamos.infrastructure.adapters.out.persistence;

import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.model.Solicitud.EstadoSolicitud;
import com.prestamos.domain.ports.out.SolicitudRepository;
import com.prestamos.infrastructure.adapters.out.persistence.entity.SolicitudEntity;
import com.prestamos.infrastructure.adapters.out.persistence.entity.SolicitudEntityMapper;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class SolicitudJpaRepository implements SolicitudRepository {

    private final SpringDataSolicitudRepository springDataRepository;
    private final SolicitudEntityMapper mapper;

    public SolicitudJpaRepository(SpringDataSolicitudRepository springDataRepository,
                                   SolicitudEntityMapper mapper) {
        this.springDataRepository = springDataRepository;
        this.mapper = mapper;
    }

    @Override
    public Solicitud guardar(Solicitud solicitud) {
        SolicitudEntity entity = mapper.toEntity(solicitud);
        SolicitudEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Solicitud> buscarPorId(UUID id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Solicitud> buscarPorIdTransaccion(UUID idTransaccion) {
        return springDataRepository.findByIdTransaccion(idTransaccion)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existePorIdTransaccion(UUID idTransaccion) {
        return springDataRepository.existsByIdTransaccion(idTransaccion);
    }
}

// === ARCHIVO: src/main/java/com/prestamos/application/service/EnrutarSolicitudService.java ===
package com.prestamos.application.service;

import com.prestamos.domain.model.Cliente;
import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.model.Solicitud.EstadoSolicitud;
import com.prestamos.domain.ports.in.EnrutarSolicitudUseCase;
import com.prestamos.domain.ports.out.SolicitudRepository;
import com.prestamos.infrastructure.exception.SolicitudDuplicadaException;
import java.math.BigDecimal;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrutarSolicitudService implements EnrutarSolicitudUseCase {

    private static final Logger log = LoggerFactory.getLogger(EnrutarSolicitudService.class);
    
    private static final BigDecimal MONTO_MINIMO_PREFERENTE = new BigDecimal("10000.00");
    private static final int PUNTAJE_MINIMO_PREFERENTE = 700;
    private static final BigDecimal MONTO_MAXIMO = new BigDecimal("50000.00");
    private static final BigDecimal MONTO_MINIMO = new BigDecimal("1000.00");

    private final SolicitudRepository solicitudRepository;

    public EnrutarSolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    @Transactional
    public Solicitud procesarSolicitud(Solicitud solicitud) {
        log.info("Iniciando procesamiento de solicitud para cliente: {}, monto: {}",
                solicitud.cliente().nombre(), solicitud.monto());
        
        solicitud.validar();
        
        if (existeSolicitudPorTransaccion(solicitud.idTransaccion())) {
            log.warn("Solicitud duplicada detectada para transacción: {}", solicitud.idTransaccion());
            throw new SolicitudDuplicadaException(
                    "Ya existe una solicitud para la transacción: " + solicitud.idTransaccion());
        }
        
        Solicitud solicitudEnriquecida = enrutarSolicitud(solicitud);
        
        Solicitud solicitudGuardada = solicitudRepository.guardar(solicitudEnriquecida);
        
        log.info("Solicitud procesada y guardada con estado: {}", solicitudGuardada.estado());
        
        return solicitudGuardada;
    }

    @Override
    public boolean existeSolicitudPorTransaccion(UUID idTransaccion) {
        return solicitudRepository.existePorIdTransaccion(idTransaccion);
    }

    private Solicitud enrutarSolicitud(Solicitud solicitud) {
        Cliente cliente = solicitud.cliente();
        BigDecimal monto = solicitud.monto();
        
        EstadoSolicitud estadoDeterminado = determinarEstado(cliente, monto);
        
        String destinoEnrutamiento = determinarDestino(cliente, monto, estadoDeterminado);
        
        log.debug("Solicitud enrutada a: {} con estado: {}", destinoEnrutamiento, estadoDeterminado);
        
        return solicitud.actualizarEstado(estadoDeterminado);
    }

    private EstadoSolicitud determinarEstado(Cliente cliente, BigDecimal monto) {
        if (monto.compareTo(MONTO_MINIMO) < 0) {
            log.info("Monto {} menor al mínimo {}, solicitud rechazada", monto, MONTO_MINIMO);
            return EstadoSolicitud.RECHAZADA;
        }
        
        if (monto.compareTo(MONTO_MAXIMO) > 0) {
            log.info("Monto {} mayor al máximo {}, solicitud requiere aprobación manual", monto, MONTO_MAXIMO);
            return EstadoSolicitud.PENDIENTE_APROBACION;
        }
        
        if (esPreferente(cliente, monto)) {
            log.info("Cliente {} cumple criterios preferentes, aprobación automática", cliente.nombre());
            return EstadoSolicitud.APROBADA;
        }
        
        log.info("Solicitud estándar enviada a revisión");
        return EstadoSolicitud.EN_REVISION;
    }

    private boolean esPreferente(Cliente cliente, BigDecimal monto) {
        return cliente.puntajeCrediticio() >= PUNTAJE_MINIMO_PREFERENTE 
                && monto.compareTo(MONTO_MINIMO_PREFERENTE) >= 0;
    }

    private String determinarDestino(Cliente cliente, BigDecimal monto, EstadoSolicitud estado) {
        return switch (estado) {
            case APROBADA -> "servicio-aprobacion-automatica";
            case RECHAZADA -> "servicio-rechazo";
            case PENDIENTE_APROBACION -> "comite-aprobacion";
            case EN_REVISION -> "servicio-revision-manual";
            case CANCELADA -> "servicio-cancelacion";
        };
    }
}

// === ARCHIVO: src/main/java/com/prestamos/infrastructure/adapters/in/web/SolicitudController.java ===
package com.prestamos.infrastructure.adapters.in.web;


import com.prestamos.domain.model.Cliente;
import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.ports.in.EnrutarSolicitudUseCase;
import com.prestamos.infrastructure.exception.SolicitudDuplicadaException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/solicitudes")
@Tag(name = "Solicitudes", description = "API para gestión de solicitudes de préstamos")
public class SolicitudController {

    private static final Logger logger = LoggerFactory.getLogger(SolicitudController.class);
    private final EnrutarSolicitudUseCase enrutarSolicitudUseCase;

    public SolicitudController(EnrutarSolicitudUseCase enrutarSolicitudUseCase) {
        this.enrutarSolicitudUseCase = enrutarSolicitudUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear nueva solicitud", description = "Registra una nueva solicitud de préstamo en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "409", description = "Solicitud duplicada para la transacción"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<SolicitudResponse> crearSolicitud(
            @Valid @RequestBody SolicitudRequest request,
            @RequestHeader(value = "X-Request-ID", required = false) String requestId) {
        
        String trackingId = requestId != null ? requestId : UUID.randomUUID().toString();
        logger.info("Recibida solicitud de préstamo - TrackingID: {}, Cliente: {}, Monto: {}",
                trackingId, request.clienteId(), request.monto());

        try {
            Solicitud solicitud = Solicitud.crearNueva(
                    request.cliente(),
                    request.monto(),
                    request.idTransaccion()
            );

            Solicitud solicitudProcesada = enrutarSolicitudUseCase.procesarSolicitud(solicitud);
            logger.info("Solicitud procesada exitosamente - ID: {}, Estado: {}",
                    solicitudProcesada.id(), solicitudProcesada.estado());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(SolicitudResponse.fromDomain(solicitudProcesada));

        } catch (SolicitudDuplicadaException e) {
            logger.warn("Solicitud duplicada detectada - Transacción: {}", request.idTransaccion());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(SolicitudResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/{idTransaccion}")
    @Operation(summary = "Consultar solicitud por transacción", 
               description = "Verifica si existe una solicitud para el ID de transacción dado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<SolicitudResponse> consultarPorTransaccion(
            @Parameter(description = "ID de transacción de la solicitud")
            @PathVariable UUID idTransaccion) {
        
        logger.debug("Consultando solicitud por transacción: {}", idTransaccion);
        
        boolean existe = enrutarSolicitudUseCase.existeSolicitudPorTransaccion(idTransaccion);
        
        if (existe) {
            return ResponseEntity.ok(SolicitudResponse.found(idTransaccion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Verifica el estado del servicio")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    public record SolicitudRequest(
            @NotNull(message = "El cliente es requerido")
            com.prestamos.domain.model.Cliente cliente,
            @NotNull(message = "El monto es requerido")
            @Parameter(description = "Monto del préstamo solicitado")
            BigDecimal monto,
            @NotNull(message = "El ID de transacción es requerido")
            @Parameter(description = "Identificador único de la transacción")
            UUID idTransaccion
    ) {}

    public record SolicitudResponse(
            UUID id,
            String estado,
            BigDecimal monto,
            String mensaje,
            boolean error
    ) {
        public static SolicitudResponse fromDomain(Solicitud solicitud) {
            return new SolicitudResponse(
                    solicitud.id(),
                    solicitud.estado().name(),
                    solicitud.monto(),
                    null,
                    false
            );
        }

        public static SolicitudResponse error(String mensaje) {
            return new SolicitudResponse(null, null, null, mensaje, true);
        }

        public static SolicitudResponse found(UUID idTransaccion) {
            return new SolicitudResponse(
                    idTransaccion,
                    "ENCONTRADA",
                    null,
                    "La solicitud existe en el sistema",
                    false
            );
        }
    }
}

// === ARCHIVO: src/main/java/com/prestamos/infrastructure/adapters/out/persistence/entity/SolicitudEntity.java ===
package com.prestamos.infrastructure.adapters.out.persistence.entity;

import com.prestamos.domain.model.Cliente;
import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.model.Solicitud.EstadoSolicitud;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "solicitudes", indexes = {
    @Index(name = "idx_solicitud_transaccion", columnList = "id_transaccion"),
    @Index(name = "idx_solicitud_estado", columnList = "estado"),
    @Index(name = "idx_solicitud_fecha", columnList = "fecha_creacion")
})
public class SolicitudEntity {

    private static final Logger logger = LoggerFactory.getLogger(SolicitudEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "id_transaccion", nullable = false, unique = true)
    private UUID idTransaccion;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    @Column(name = "monto", nullable = false, precision = 19, scale = 4)
    private BigDecimal monto;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "nombre", column = @Column(name = "cliente_nombre")),
        @AttributeOverride(name = "email", column = @Column(name = "cliente_email")),
        @AttributeOverride(name = "puntajeCrediticio", column = @Column(name = "cliente_puntaje")),
        @AttributeOverride(name = "canalOrigen", column = @Column(name = "cliente_canal"))
    })
    private ClienteEmbeddable cliente;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "version")
    @Version
    private Long version;

    public SolicitudEntity() {}

    public static SolicitudEntity fromDomain(Solicitud solicitud) {
        logger.debug("Convirtiendo Solicitud a SolicitudEntity - ID: {}", solicitud.id());
        
        SolicitudEntity entity = new SolicitudEntity();
        entity.id = solicitud.id();
        entity.idTransaccion = solicitud.idTransaccion();
        entity.estado = solicitud.estado();
        entity.monto = solicitud.monto();
        entity.cliente = ClienteEmbeddable.fromDomain(solicitud.cliente());
        entity.fechaCreacion = solicitud.fechaCreacion();
        entity.fechaActualizacion = solicitud.fechaActualizacion();
        
        return entity;
    }

    public Solicitud toDomain() {
        logger.debug("Convirtiendo SolicitudEntity a dominio - ID Transacción: {}", this.idTransaccion);
        
        Cliente cliente = this.cliente.toDomain();
        
        return new Solicitud(
                this.id,
                this.idTransaccion,
                this.estado,
                this.monto,
                cliente,
                this.fechaCreacion,
                this.fechaActualizacion
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(UUID idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public ClienteEmbeddable getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEmbeddable cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Embeddable
    public static class ClienteEmbeddable {
        
        @Column(name = "cliente_nombre")
        private String nombre;
        
        @Column(name = "cliente_email")
        private String email;
        
        @Column(name = "cliente_puntaje")
        private int puntajeCrediticio;
        
        @Column(name = "cliente_canal")
        private String canalOrigen;

        public ClienteEmbeddable() {}

        public static ClienteEmbeddable fromDomain(Cliente cliente) {
            ClienteEmbeddable embeddable = new ClienteEmbeddable();
            embeddable.nombre = cliente.nombre();
            embeddable.email = cliente.email();
            embeddable.puntajeCrediticio = cliente.puntajeCrediticio();
            embeddable.canalOrigen = cliente.canalOrigen();
            return embeddable;
        }

        public Cliente toDomain() {
            return new Cliente(
                    this.nombre,
                    this.email,
                    this.puntajeCrediticio,
                    this.canalOrigen
            );
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getPuntajeCrediticio() {
            return puntajeCrediticio;
        }

        public void setPuntajeCrediticio(int puntajeCrediticio) {
            this.puntajeCrediticio = puntajeCrediticio;
        }

        public String getCanalOrigen() {
            return canalOrigen;
        }

        public void setCanalOrigen(String canalOrigen) {
            this.canalOrigen = canalOrigen;
        }
    }
}

// === ARCHIVO: src/main/java/com/prestamos/infrastructure/config/LoggingConfig.java ===
package com.prestamos.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Configuration
public class LoggingConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
    private static final String REQUEST_ID_HEADER = "X-Request-ID";
    private static final String REQUEST_ID_ATTR = "requestId";

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        registrationBean.setName("requestLoggingFilter");
        return registrationBean;
    }

    @Bean
    public LoggingProperties loggingProperties() {
        return new LoggingConfig.LoggingProperties();
    }

    public static class RequestLoggingFilter extends OncePerRequestFilter {

        private static final Logger filterLogger = LoggerFactory.getLogger(RequestLoggingFilter.class);

        @Override
        protected void doFilterInternal(HttpServletRequest request, 
                                        HttpServletResponse response, 
                                        FilterChain filterChain) throws ServletException, IOException {
            
            String requestId = request.getHeader(REQUEST_ID_HEADER);
            if (requestId == null || requestId.isBlank()) {
                requestId = UUID.randomUUID().toString();
            }
            
            request.setAttribute(REQUEST_ID_ATTR, requestId);
            long startTime = System.currentTimeMillis();
            
            String method = request.getMethod();
            String uri = request.getRequestURI();
            String queryString = request.getQueryString();
            String clientIp = getClientIp(request);
            
            filterLogger.info("[{}] INICIO - {} {} {} - IP: {}", 
                    requestId, method, uri, 
                    queryString != null ? "?" + queryString : "",
                    clientIp);
            
            try {
                filterChain.doFilter(request, response);
            } finally {
                long duration = System.currentTimeMillis() - startTime;
                int status = response.getStatus();
                
                String logLevel = status >= 400 ? "WARN" : "INFO";
                if (status >= 500) {
                    logLevel = "ERROR";
                }
                
                filterLogger.info("[{}] FIN - {} {} - Estado: {} - Duración: {}ms",
                        requestId, method, uri, status, duration);
                
                if (duration > 1000) {
                    filterLogger.warn("[{}] PETICIÓN LENTA detectada - {} {} took {}ms",
                            requestId, method, uri, duration);
                }
                
                response.setHeader(REQUEST_ID_HEADER, requestId);
            }
        }

        private String getClientIp(HttpServletRequest request) {
            String xForwardedFor = request.getHeader("X-Forwarded-For");
            if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
                return xForwardedFor.split(",")[0].trim();
            }
            
            String xRealIp = request.getHeader("X-Real-IP");
            if (xRealIp != null && !xRealIp.isEmpty()) {
                return xRealIp;
            }
            
            return request.getRemoteAddr();
        }
    }

    public static class LoggingProperties {
        private boolean requestIdEnabled = true;
        private boolean logRequestBody = false;
        private boolean logResponseBody = false;
        private long slowRequestThresholdMs = 1000;
        private String[] excludedPaths = {"/actuator/health", "/actuator/info"};

        public boolean isRequestIdEnabled() {
            return requestIdEnabled;
        }

        public void setRequestIdEnabled(boolean requestIdEnabled) {
            this.requestIdEnabled = requestIdEnabled;
        }

        public boolean isLogRequestBody() {
            return logRequestBody;
        }

        public void setLogRequestBody(boolean logRequestBody) {
            this.logRequestBody = logRequestBody;
        }

        public boolean isLogResponseBody() {
            return logResponseBody;
        }

        public void setLogResponseBody(boolean logResponseBody) {
            this.logResponseBody = logResponseBody;
        }

        public long getSlowRequestThresholdMs() {
            return slowRequestThresholdMs;
        }

        public void setSlowRequestThresholdMs(long slowRequestThresholdMs) {
            this.slowRequestThresholdMs = slowRequestThresholdMs;
        }

        public String[] getExcludedPaths() {
            return excludedPaths;
        }

        public void setExcludedPaths(String[] excludedPaths) {
            this.excludedPaths = excludedPaths;
        }
    }
}

// === ARCHIVO: src/main/java/com/prestamos/infrastructure/exception/SolicitudDuplicadaException.java ===
package com.prestamos.infrastructure.exception;

import java.util.UUID;

public class SolicitudDuplicadaException extends RuntimeException {

    private final UUID idTransaccion;
    private final String detalleAdicional;

    public SolicitudDuplicadaException(UUID idTransaccion) {
        super("Ya existe una solicitud registrada para la transacción: " + idTransaccion);
        this.idTransaccion = idTransaccion;
        this.detalleAdicional = null;
    }

    public SolicitudDuplicadaException(UUID idTransaccion, String mensajePersonalizado) {
        super(mensajePersonalizado);
        this.idTransaccion = idTransaccion;
        this.detalleAdicional = null;
    }

    public SolicitudDuplicadaException(UUID idTransaccion, String mensajePersonalizado, Throwable causa) {
        super(mensajePersonalizado, causa);
        this.idTransaccion = idTransaccion;
        this.detalleAdicional = null;
    }

    public SolicitudDuplicadaException(UUID idTransaccion, String mensajePersonalizado, String detalleAdicional) {
        super(mensajePersonalizado + ". Detalle: " + detalleAdicional);
        this.idTransaccion = idTransaccion;
        this.detalleAdicional = detalleAdicional;
    }

    public UUID getIdTransaccion() {
        return idTransaccion;
    }

    public String getDetalleAdicional() {
        return detalleAdicional;
    }

    public String getMensajeTecnico() {
        return String.format("Transacción duplicada: %s | Timestamp: %d", 
            idTransaccion != null ? idTransaccion.toString() : "N/A", 
            System.currentTimeMillis());
    }

    public boolean esReintento() {
        return detalleAdicional != null && detalleAdicional.contains("reintento");
    }

    public boolean puedeReintentar() {
        return esReintento();
    }

    @Override
    public String toString() {
        return "SolicitudDuplicadaException{" +
                "idTransaccion=" + idTransaccion +
                ", detalleAdicional='" + detalleAdicional + '\'' +
                ", mensaje='" + getMessage() + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/prestamos/infrastructure/exception/GlobalExceptionHandler.java ===
package com.prestamos.infrastructure.exception;


import com.prestamos.domain.model.Solicitud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String ERROR_INTERNO = "Error interno del servidor";

    @ExceptionHandler(SolicitudDuplicadaException.class)
    public ResponseEntity<ErrorResponse> handleSolicitudDuplicada(
            SolicitudDuplicadaException ex, WebRequest request) {
        
        logger.warn("Solicitud duplicada detectada para transacción: {}. Detalles: {}", 
            ex.getIdTransaccion(), ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .codigo("DUPLICATE_TRANSACTION")
            .mensaje("La solicitud ya fue procesada anteriormente")
            .detalle(ex.getMessage())
            .idTransaccion(ex.getIdTransaccion())
            .timestamp(Instant.now())
            .ruta(request.getDescription(false).replace("uri=", ""))
            .puedeReintentar(ex.puedeReintentar())
            .build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            errores.put(nombreCampo, mensajeError);
        });
        
        logger.warn("Error de validación en solicitud: {}", errores);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .codigo("VALIDATION_ERROR")
            .mensaje("Los datos de la solicitud no son válidos")
            .detalle(errores.toString())
            .timestamp(Instant.now())
            .ruta(request.getDescription(false).replace("uri=", ""))
            .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        
        logger.warn("Argumento inválido recibido: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .codigo("INVALID_ARGUMENT")
            .mensaje("Parámetro de solicitud inválido")
            .detalle(ex.getMessage())
            .timestamp(Instant.now())
            .ruta(request.getDescription(false).replace("uri=", ""))
            .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointer(
            NullPointerException ex, WebRequest request) {
        
        logger.error("NullPointerException en la solicitud: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .codigo("NULL_POINTER")
            .mensaje(ERROR_INTERNO)
            .detalle("Se recibió un valor nulo donde se esperaba un objeto")
            .timestamp(Instant.now())
            .ruta(request.getDescription(false).replace("uri=", ""))
            .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        
        logger.error("Excepción no controlada: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
            .codigo("INTERNAL_ERROR")
            .mensaje(ERROR_INTERNO)
            .detalle(ex.getMessage())
            .timestamp(Instant.now())
            .ruta(request.getDescription(false).replace("uri=", ""))
            .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public record ErrorResponse(
        String codigo,
        String mensaje,
        String detalle,
        UUID idTransaccion,
        Instant timestamp,
        String ruta,
        Boolean puedeReintentar
    ) {
        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }
    }

    public static class ErrorResponseBuilder {
        private String codigo;
        private String mensaje;
        private String detalle;
        private UUID idTransaccion;
        private Instant timestamp;
        private String ruta;
        private Boolean puedeReintentar;

        public ErrorResponseBuilder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public ErrorResponseBuilder mensaje(String mensaje) {
            this.mensaje = mensaje;
            return this;
        }

        public ErrorResponseBuilder detalle(String detalle) {
            this.detalle = detalle;
            return this;
        }

        public ErrorResponseBuilder idTransaccion(UUID idTransaccion) {
            this.idTransaccion = idTransaccion;
            return this;
        }

        public ErrorResponseBuilder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponseBuilder ruta(String ruta) {
            this.ruta = ruta;
            return this;
        }

        public ErrorResponseBuilder puedeReintentar(Boolean puedeReintentar) {
            this.puedeReintentar = puedeReintentar;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(codigo, mensaje, detalle, idTransaccion, timestamp, ruta, puedeReintentar);
        }
    }
}

// === ARCHIVO: src/test/java/com/prestamos/application/service/EnrutarSolicitudServiceTest.java ===
package com.prestamos.application.service;

import com.prestamos.domain.model.Cliente;
import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.model.Solicitud.EstadoSolicitud;
import com.prestamos.domain.ports.out.SolicitudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para EnrutarSolicitudService")
class EnrutarSolicitudServiceTest {

    @Mock
    private SolicitudRepository solicitudRepository;

    private EnrutarSolicitudService service;

    @BeforeEach
    void setUp() {
        service = new EnrutarSolicitudService(solicitudRepository);
    }

    @Nested
    @DisplayName("Procesamiento de solicitudes")
    class ProcesamientoSolicitudes {

        @Test
        @DisplayName("Debe procesar solicitud exitosamente cuando es nueva")
        void debeProcesarSolicitudExitosamente() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Juan Perez", "juan@test.com", 750, "web");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("10000.00"), idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(false);
            when(solicitudRepository.guardar(any(Solicitud.class))).thenAnswer(inv -> inv.getArgument(0));

            Solicitud resultado = service.procesarSolicitud(solicitud);

            assertNotNull(resultado);
            assertEquals(EstadoSolicitud.PROCESADA, resultado.estado());
            verify(solicitudRepository).guardar(any(Solicitud.class));
        }

        @Test
        @DisplayName("Debe rechazar solicitud duplicada por transacción")
        void debeRechazarSolicitudDuplicada() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Maria Lopez", "maria@test.com", 800, "movil");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("5000.00"), idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(true);

            Solicitud resultado = service.procesarSolicitud(solicitud);

            assertNotNull(resultado);
            assertEquals(EstadoSolicitud.RECHAZADA, resultado.estado());
            verify(solicitudRepository, never()).guardar(any(Solicitud.class));
        }

        @Test
        @DisplayName("Debe guardar solicitud con estado apropiado según puntaje crediticio")
        void debeGuardarConEstadoSegunPuntaje() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Carlos Ruiz", "carlos@test.com", 650, "api");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("8000.00"), idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(false);
            when(solicitudRepository.guardar(any(Solicitud.class))).thenAnswer(inv -> inv.getArgument(0));

            Solicitud resultado = service.procesarSolicitud(solicitud);

            ArgumentCaptor<Solicitud> captor = ArgumentCaptor.forClass(Solicitud.class);
            verify(solicitudRepository).guardar(captor.capture());

            Solicitud capturada = captor.getValue();
            assertNotNull(capturada);
        }
    }

    @Nested
    @DisplayName("Verificación de idempotencia")
    class VerificacionIdempotencia {

        @Test
        @DisplayName("Debe retornar true cuando existe transacción")
        void debeRetornarTrueCuandoExiste() {
            UUID idTransaccion = UUID.randomUUID();
            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(true);

            boolean resultado = service.existeSolicitudPorTransaccion(idTransaccion);

            assertTrue(resultado);
            verify(solicitudRepository).existePorTransaccion(idTransaccion);
        }

        @Test
        @DisplayName("Debe retornar false cuando no existe transacción")
        void debeRetornarFalseCuandoNoExiste() {
            UUID idTransaccion = UUID.randomUUID();
            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(false);

            boolean resultado = service.existeSolicitudPorTransaccion(idTransaccion);

            assertFalse(resultado);
            verify(solicitudRepository).existePorTransaccion(idTransaccion);
        }

        @Test
        @DisplayName("Debe verificar idempotencia antes de procesar")
        void debeVerificarIdempotenciaAntesDeProcesar() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Ana Gomez", "ana@test.com", 700, "web");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("12000.00"), idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(true);

            service.procesarSolicitud(solicitud);

            verify(solicitudRepository, times(1)).existePorTransaccion(idTransaccion);
            verify(solicitudRepository, never()).guardar(any(Solicitud.class));
        }
    }

    @Nested
    @DisplayName("Casos de borde")
    class CasosDeBorde {

        @Test
        @DisplayName("Debe manejar solicitud con monto cero")
        void debeManejarMontoCero() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Pedro Martinez", "pedro@test.com", 720, "movil");
            Solicitud solicitud = Solicitud.crearNueva(cliente, BigDecimal.ZERO, idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(false);
            when(solicitudRepository.guardar(any(Solicitud.class))).thenAnswer(inv -> inv.getArgument(0));

            Solicitud resultado = service.procesarSolicitud(solicitud);

            assertNotNull(resultado);
            verify(solicitudRepository).guardar(any(Solicitud.class));
        }

        @Test
        @DisplayName("Debe manejar cliente con puntaje mínimo")
        void debeManejarPuntajeMinimo() {
            UUID idTransaccion = UUID.randomUUID();
            Cliente cliente = Cliente.crearNuevo("Laura Diaz", "laura@test.com", 500, "api");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("3000.00"), idTransaccion);

            when(solicitudRepository.existePorTransaccion(idTransaccion)).thenReturn(false);
            when(solicitudRepository.guardar(any(Solicitud.class))).thenAnswer(inv -> inv.getArgument(0));

            Solicitud resultado = service.procesarSolicitud(solicitud);

            assertNotNull(resultado);
        }

        @Test
        @DisplayName("Debe rechazar UUID de transacción nulo")
        void debeRechazarUuidNulo() {
            Cliente cliente = Cliente.crearNuevo("Test User", "test@test.com", 650, "web");
            Solicitud solicitud = Solicitud.crearNueva(cliente, new BigDecimal("5000.00"), null);

            assertThrows(NullPointerException.class, () -> service.procesarSolicitud(solicitud));
        }
    }
}

// === ARCHIVO: src/test/java/com/prestamos/infrastructure/adapters/in/web/SolicitudControllerTest.java ===
package com.prestamos.infrastructure.adapters.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prestamos.application.service.EnrutarSolicitudService;
import com.prestamos.domain.model.Cliente;
import com.prestamos.domain.model.Solicitud;
import com.prestamos.domain.model.Solicitud.EstadoSolicitud;
import com.prestamos.infrastructure.exception.SolicitudDuplicadaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SolicitudController.class)
@DisplayName("Tests de integración para SolicitudController")
class SolicitudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EnrutarSolicitudService enrutarSolicitudService;

    private Cliente clienteValido;
    private Solicitud solicitudValida;
    private String clienteJson;
    private String solicitudJson;

    @BeforeEach
    void setUp() throws Exception {
        clienteValido = Cliente.crearNuevo("Roberto Sanchez", "roberto@test.com", 720, "web");
        UUID idTransaccion = UUID.randomUUID();
        solicitudValida = Solicitud.crearNueva(clienteValido, new BigDecimal("15000.00"), idTransaccion);

        clienteJson = "{\"nombre\":\"Roberto Sanchez\",\"email\":\"roberto@test.com\",\"puntajeCrediticio\":720,\"canalOrigen\":\"web\"}";
        solicitudJson = "{\"cliente\":{\"nombre\":\"Roberto Sanchez\",\"email\":\"roberto@test.com\",\"puntajeCrediticio\":720,\"canalOrigen\":\"web\"},\"monto\":15000.00,\"idTransaccion\":\"" + idTransaccion + "\"}";
    }

    @Nested
    @DisplayName("POST /api/solicitudes - Crear solicitud")
    class CrearSolicitud {

        @Test
        @DisplayName("Debe retornar 201 Created cuando la solicitud se procesa exitosamente")
        void debeRetornar201CuandoSeProcesaExitosamente() throws Exception {
            when(enrutarSolicitudService.procesarSolicitud(any(Solicitud.class))).thenReturn(solicitudValida);

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(solicitudJson))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.cliente.nombre").value("Roberto Sanchez"))
                    .andExpect(jsonPath("$.monto").value(15000.00));
        }

        @Test
        @DisplayName("Debe retornar 400 Bad Request cuando el JSON es inválido")
        void debeRetornar400CuandoJsonInvalido() throws Exception {
            String jsonInvalido = "{\"monto\":15000}";

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonInvalido))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 409 Conflict cuando la solicitud está duplicada")
        void debeRetornar409CuandoSolicitudDuplicada() throws Exception {
            when(enrutarSolicitudService.procesarSolicitud(any(Solicitud.class)))
                    .thenThrow(new SolicitudDuplicadaException("Transacción duplicada"));

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(solicitudJson))
                    .andExpect(status().isConflict());
        }

        @Test
        @DisplayName("Debe validar que el email del cliente tenga formato correcto")
        void debeValidarFormatoEmail() throws Exception {
            String jsonEmailInvalido = "{\"cliente\":{\"nombre\":\"Test\",\"email\":\"email-invalido\",\"puntajeCrediticio\":650,\"canalOrigen\":\"web\"},\"monto\":5000.00,\"idTransaccion\":\"" + UUID.randomUUID() + "\"}";

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonEmailInvalido))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe validar que el monto sea positivo")
        void debeValidarMontoPositivo() throws Exception {
            String jsonMontoNegativo = "{\"cliente\":{\"nombre\":\"Test\",\"email\":\"test@test.com\",\"puntajeCrediticio\":650,\"canalOrigen\":\"web\"},\"monto\":-1000.00,\"idTransaccion\":\"" + UUID.randomUUID() + "\"}";

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonMontoNegativo))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("GET /api/solicitudes/{id} - Obtener solicitud")
    class ObtenerSolicitud {

        @Test
        @DisplayName("Debe retornar 200 OK con la solicitud cuando existe")
        void debeRetornar200CuandoExiste() throws Exception {
            when(enrutarSolicitudService.buscarPorId(any())).thenReturn(java.util.Optional.of(solicitudValida));

            mockMvc.perform(get("/api/solicitudes/" + UUID.randomUUID()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.cliente.nombre").value("Roberto Sanchez"));
        }

        @Test
        @DisplayName("Debe retornar 404 Not Found cuando la solicitud no existe")
        void debeRetornar404CuandoNoExiste() throws Exception {
            when(enrutarSolicitudService.buscarPorId(any())).thenReturn(java.util.Optional.empty());

            mockMvc.perform(get("/api/solicitudes/" + UUID.randomUUID()))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("GET /api/solicitudes/existe/{idTransaccion} - Verificar existencia")
    class VerificarExistencia {

        @Test
        @DisplayName("Debe retornar 200 true cuando la transacción existe")
        void debeRetornarTrueCuandoExiste() throws Exception {
            UUID idTransaccion = UUID.randomUUID();
            when(enrutarSolicitudService.existeSolicitudPorTransaccion(idTransaccion)).thenReturn(true);

            mockMvc.perform(get("/api/solicitudes/existe/" + idTransaccion))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));
        }

        @Test
        @DisplayName("Debe retornar 200 false cuando la transacción no existe")
        void debeRetornarFalseCuandoNoExiste() throws Exception {
            UUID idTransaccion = UUID.randomUUID();
            when(enrutarSolicitudService.existeSolicitudPorTransaccion(idTransaccion)).thenReturn(false);

            mockMvc.perform(get("/api/solicitudes/existe/" + idTransaccion))
                    .andExpect(status().isOk())
                    .andExpect(content().string("false"));
        }
    }

    @Nested
    @DisplayName("Validación de headers")
    class ValidacionHeaders {

        @Test
        @DisplayName("Debe rechazar contenido que no sea JSON")
        void debeRechazarContentTypeNoJson() throws Exception {
            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.TEXT_PLAIN)
                    .content("texto plano"))
                    .andExpect(status().isUnsupportedMediaType());
        }

        @Test
        @DisplayName("Debe aceptar solicitudes sin body")
        void debeAceptarSolicitudSinBody() throws Exception {
            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(""))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Manejo de errores global")
    class ManejoErrores {

        @Test
        @DisplayName("Debe manejar excepción genérica retornando 500")
        void debeManejarExcepcionGenerica() throws Exception {
            when(enrutarSolicitudService.procesarSolicitud(any()))
                    .thenThrow(new RuntimeException("Error interno"));

            mockMvc.perform(post("/api/solicitudes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(solicitudJson))
                    .andExpect(status().isInternalServerError());
        }
    }
}
```
