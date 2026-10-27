# Evaluación de Competencia en Frameworks

El sistema debe gestionar solicitudes de clientes para un servicio de préstamo. Los clientes pueden enviar solicitudes a través de múltiples canales (web, móvil, API). El sistema debe enrutar las solicitudes al servicio correspondiente, controlar las variables de entorno, generar registros de trazas y conectarse a una base de datos utilizando un ORM. Los clientes se identifican por un ID único y cada solicitud tiene un ID de transacción. El sistema debe manejar errores de forma idempotente y asegurar la consistencia de los datos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Sólida Experiencia en Frameworks |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Enrutamiento de Solicitudes

**Objetivo:** Implementar la funcionalidad básica para enrutar solicitudes de clientes a los servicios correspondientes.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar los canales de entrada de solicitudes.
- Implementar el enrutamiento de solicitudes al servicio correspondiente.
- Asegurar que las solicitudes se manejan de forma idempotente.

**Entregable:** Sistema que enruta solicitudes de clientes a servicios correspondientes de forma idempotente.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo manejar solicitudes duplicadas.
- Piensa en cómo asegurar que el enrutamiento sea eficiente y escalable.

</details>

### Fase 2: Control de Variables de Entorno

**Objetivo:** Implementar el control de variables de entorno para el sistema.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar las variables de entorno necesarias para el sistema.
- Implementar la lectura y uso de variables de entorno en el sistema.
- Asegurar que el sistema se comporte correctamente bajo diferentes configuraciones de entorno.

**Entregable:** Sistema que lee y utiliza variables de entorno para configurarse correctamente.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo manejar diferentes configuraciones de entorno.
- Piensa en cómo asegurar que el sistema se comporte correctamente bajo cualquier configuración.

</details>

### Fase 3: Generación de Registros de Trazas

**Objetivo:** Implementar la generación de registros de trazas para el sistema.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar los puntos clave del sistema donde se deben generar registros de trazas.
- Implementar la generación de registros de trazas en estos puntos.
- Asegurar que los registros de trazas sean útiles para la depuración y el seguimiento de problemas.

**Entregable:** Sistema que genera registros de trazas útiles para la depuración y el seguimiento de problemas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera qué información es importante incluir en los registros de trazas.
- Piensa en cómo asegurar que los registros de trazas sean legibles y útiles.

</details>

### Fase 4: Conexión a Base de Datos con ORM

**Objetivo:** Implementar la conexión a una base de datos utilizando un ORM.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar la base de datos y el ORM a utilizar.
- Implementar la conexión a la base de datos utilizando el ORM.
- Asegurar que el sistema pueda realizar operaciones CRUD en la base de datos.

**Entregable:** Sistema que se conecta a una base de datos y realiza operaciones CRUD utilizando un ORM.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo asegurar que las operaciones CRUD sean eficientes y seguras.
- Piensa en cómo manejar errores de conexión y operaciones de base de datos.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un framework y por qué se utiliza en el desarrollo de aplicaciones?
- **paraQueSirve**: ¿Para qué sirve el enrutamiento de solicitudes en un sistema backend?
- **comoSeUsa**: ¿Cómo se utiliza un ORM para conectarse a una base de datos en un sistema backend?
- **erroresComunes**: ¿Cuáles son los errores comunes al manejar variables de entorno en un sistema backend?
- **queDecisionesImplica**: ¿Qué decisiones implica la elección de un framework para el desarrollo de una aplicación backend?

## Criterios de Evaluacion

- Implementar la funcionalidad básica para enrutar solicitudes de clientes a los servicios correspondientes.
- Implementar el control de variables de entorno para el sistema.
- Implementar la generación de registros de trazas para el sistema.
- Implementar la conexión a una base de datos utilizando un ORM.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
