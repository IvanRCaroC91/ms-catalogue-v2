# Microservicio Catálogo de Libros – Relatos de Papel

Este microservicio gestiona el catálogo de libros de la plataforma Relatos de Papel.  
Desarrollado en Java 21 con Spring Boot, utiliza PostgreSQL como base de datos y Docker Compose para facilitar la configuración y el despliegue local.

---

## 🚀 Requisitos previos

- [Git](https://git-scm.com/) instalado
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado y en ejecución
- [Java 21](https://adoptium.net/) instalado
- [Maven](https://maven.apache.org/) instalado (opcional, recomendado si vas a compilar manualmente)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) o tu IDE favorito para Java

---

## 📝 Pasos para clonar y ejecutar el proyecto

### 1. Clonar el repositorio

git clone https://github.com/IvanRCaroC91/relatos-de-papel-BackEnd-Micro1.git
cd relatos-de-papel-BackEnd-Micro1


### 2. Configurar Docker y la base de datos

- Asegúrate de que Docker Desktop esté corriendo.
- Levanta los servicios de base de datos y pgAdmin con:


    docker-compose up -d


Esto creará el contenedor de PostgreSQL con la base de datos `libros_db`, el usuario `libreria` y la contraseña `catalogos_libreria`, y ejecutará el script de inicialización para crear la tabla `libros` y cargar datos de ejemplo.

### 3. Configurar el microservicio

No necesitas cambiar nada si usas la configuración por defecto.  
La conexión a la base de datos ya está definida en `src/main/resources/application.yml`:

    pring:
    datasource:
    url: jdbc:postgresql://localhost:5432/libros_db
    username: libreria
    password: catalogos_libreria
    driver-class-name: org.postgresql.Driver


### 4. Ejecutar el microservicio

Puedes hacerlo desde IntelliJ (botón "Run") o desde terminal:

    mvn spring-boot:run


La API REST estará disponible en:  
`http://localhost:8081/api/libros`

---

## 🧪 Probar los endpoints principales

- **GET /api/libros**  
  Lista todos los libros.

- **GET /api/libros/{id}**  
  Consulta un libro por ID.

- **GET /api/libros/buscar?titulo=...&autor=...**  
  Busca libros por filtros.

- **POST /api/libros**  
  Crea un nuevo libro (requiere JSON en el body).

- **PATCH /api/libros/{id}/stock?cantidad=2**  
  Actualiza el stock de un libro.

- **DELETE /api/libros/{id}**  
  Elimina un libro.

---

## ⚠️ Notas importantes

- **No uses IPs ni localhost en el código para consumir este microservicio desde otros microservicios.** Usa el nombre lógico del servicio (`ms-books-catalogue`) cuando integres con Eureka o un API Gateway.
- Si necesitas resetear la base de datos, ejecuta:


    docker-compose down -v
    docker-compose up -d

- Si cambias credenciales o puertos, actualiza también el `application.yml`.

