📚 Microservicio Catálogo de Libros – Relatos de Papel (v2)
Este microservicio gestiona el catálogo de libros de la plataforma Relatos de Papel, ahora con integración a Elasticsearch para búsquedas avanzadas.

🔄 Esta versión utiliza un motor de búsqueda distribuido para mejorar la eficiencia en consultas complejas.

🚀 Tecnologías utilizadas
Java 17

Spring Boot 2.7.18

Elasticsearch 7.17.x (compatible con Spring Data)

Maven

Spring Data Elasticsearch

📦 Requisitos previos
Java 17 instalado

Maven instalado

Elasticsearch 7.17 instalado y en ejecución (local o remoto)

Git instalado

IDE recomendado: IntelliJ IDEA (u otro con soporte Spring Boot)

📝 Pasos para clonar y ejecutar el proyecto
1. Clona el repositorio
   bash
   Copiar
   Editar
   git clone https://github.com/IvanRCaroC91/ms-catalogue-v2.git
   cd ms-catalogue-v2
2. Configura Elasticsearch
   Asegúrate de tener Elasticsearch 7.17.x corriendo en tu equipo o en un servidor accesible. Luego edita:

css
Copiar
Editar
src/main/resources/application.properties
Y coloca tus valores de conexión:

properties
Copiar
Editar
spring.elasticsearch.rest.uris=http://localhost:9200
spring.elasticsearch.rest.read-timeout=5s
spring.elasticsearch.username=elastic
spring.elasticsearch.password=tu_contraseña
3. Ejecuta el proyecto
   Desde terminal:

bash
Copiar
Editar
mvn spring-boot:run
O directamente desde tu IDE ejecutando MsCatalogueApplication.java.

🌐 Endpoints REST principales
bash
Copiar
Editar
GET     /api/libros
GET     /api/libros/{id}
GET     /api/libros/buscar?titulo=...&autor=...
POST    /api/libros
PATCH   /api/libros/{id}/stock?cantidad=2
DELETE  /api/libros/{id}
Todos los datos son indexados en Elasticsearch dentro del índice libros.

⚠️ Notas importantes
No se utiliza base de datos relacional en esta versión.

Elasticsearch debe estar corriendo y accesible para que el microservicio funcione correctamente.

Usa ms-books-catalogue como nombre lógico del servicio cuando integres con Eureka o un API Gateway.

Si cambias las credenciales o el puerto de Elasticsearch, recuerda actualizar application.properties.

¿Deseas que ahora te genere el archivo README.md con este contenido para que lo puedas agregar y subir a tu nuevo repositorio?








Dijiste:
recuerda que se usa bonsai


ChatGPT dijo:
Perfecto, gracias por el dato. Si estás usando Bonsai (https://bonsai.io/) como proveedor de Elasticsearch como servicio, entonces la configuración del application.properties y algunas notas deben ajustarse para reflejar:

Conexión remota mediante HTTPS.

Autenticación básica (usuario y clave en la URL).

Versión de Elasticsearch generalmente 7.x (según el plan Bonsai).

✅ Aquí tienes el README.md actualizado considerando que usas Bonsai:
markdown
Copiar
Editar
# 📚 Microservicio Catálogo de Libros – Relatos de Papel (v2)

Este microservicio gestiona el catálogo de libros de la plataforma **Relatos de Papel**, ahora con integración a **Elasticsearch** (vía Bonsai) para búsquedas avanzadas.

> 🔄 Esta versión elimina la base de datos relacional y persiste directamente en Elasticsearch.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 2.7.18
- Elasticsearch 7.x (vía [Bonsai.io](https://bonsai.io))
- Spring Data Elasticsearch
- Maven

---

## 🧰 Requisitos previos

- Tener una cuenta activa en [bonsai.io](https://bonsai.io)
- Tener configurada tu URL de conexión a Elasticsearch Bonsai
- Java 17 instalado
- Maven instalado
- Git
- IDE para Java (como IntelliJ IDEA)

---

## 📥 Clonar y ejecutar el proyecto

### 1. Clona este repositorio

```bash
git clone https://github.com/IvanRCaroC91/ms-catalogue-v2.git
cd ms-catalogue-v2
2. Configura Elasticsearch (Bonsai)
Ve al archivo:

css
Copiar
Editar
src/main/resources/application.properties
Y reemplaza las siguientes líneas por la configuración de tu Bonsai:

properties
Copiar
Editar
spring.elasticsearch.uris=https://<usuario>:<contraseña>@<tu-cluster>.bonsaisearch.net
spring.elasticsearch.username=<usuario> # opcional si va en la URI
spring.elasticsearch.password=<contraseña> # opcional si va en la URI
spring.elasticsearch.socket-timeout=10s
🔐 Importante: Nunca subas tu URI completa a un repositorio público. Usa variables de entorno en producción.

3. Ejecuta el proyecto
Con Maven desde la terminal:

bash
Copiar
Editar
mvn spring-boot:run
O desde tu IDE, ejecuta la clase MsCatalogueApplication.java.

🔗 API REST – Endpoints principales
bash
Copiar
Editar
GET     /api/libros
GET     /api/libros/{id}
GET     /api/libros/buscar?titulo=...&autor=...
POST    /api/libros
PATCH   /api/libros/{id}/stock?cantidad=2
DELETE  /api/libros/{id}
Toda la información se almacena e indexa directamente en Elasticsearch (índice: libros).

⚠️ Notas importantes
Esta versión no usa base de datos relacional (ni PostgreSQL ni Docker Compose).

Asegúrate de que tu cuenta Bonsai esté activa y que el índice libros se haya creado correctamente (se puede autogenerar en runtime).

Para consumir este microservicio desde otros, usa su nombre lógico (ms-books-catalogue) al integrarlo en Eureka o en un API Gateway.

Para ambientes productivos, usa variables de entorno para ocultar credenciales de Elasticsearch.

🧼 Resetear el índice en Bonsai
Para reiniciar la colección de libros en Bonsai puedes usar herramientas como Postman o curl:

bash
Copiar
Editar
DELETE https://<usuario>:<contraseña>@<tu-cluster>.bonsaisearch.net/libros
Ten cuidado: esto eliminará todos los datos.

