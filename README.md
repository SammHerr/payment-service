# Payment Service

## Descripción

**payment-service** es un microservicio desarrollado con **Spring Boot 4.0.6** que forma parte de la Plataforma de Reservaciones Hoteleras basada en Arquitectura Orientada a Servicios (SOA).

Este microservicio es responsable de administrar los pagos asociados a las reservaciones de hoteles, permitiendo registrar, consultar, actualizar y desactivar pagos mediante una API REST.

---

# Tecnologías utilizadas

- Java 21
- Spring Boot 4.0.6
- Spring Web
- Spring Data JPA
- PostgreSQL
- Jakarta Validation
- Lombok
- Swagger / OpenAPI
- Maven
- Docker

---

# Arquitectura

El proyecto está organizado siguiendo una arquitectura por capas.

```
Controller
     │
Service
     │
Repository
     │
PostgreSQL
```

La estructura del proyecto es la siguiente:

```
payment-service
│
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
│
├── resources
│      └── application.properties
│
├── Dockerfile
├── pom.xml
└── README.md
```

---

# Base de datos

Base de datos utilizada:

```
payment_service_db
```

Tabla principal:

```
payments
```

Campos de la tabla:

| Campo | Tipo |
|---------|------|
| id | Long |
| reservation_id | Long |
| amount | Decimal |
| payment_method | String |
| status | String |
| transaction_reference | String |
| payment_date | LocalDateTime |
| active | Boolean |

---

# Configuración

Puerto utilizado:

```
8084
```

Archivo:

```
src/main/resources/application.properties
```

Configuración:

```properties
spring.application.name=payment-service

server.port=8084

spring.datasource.url=jdbc:postgresql://localhost:5432/payment_service_db
spring.datasource.username=postgres
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

# API REST

## Health Check

```
GET /api/payments/health
```

Respuesta:

```
payment-service is running
```

---

## Obtener todos los pagos

```
GET /api/payments
```

---

## Obtener pago por ID

```
GET /api/payments/{id}
```

---

## Registrar pago

```
POST /api/payments
```

Ejemplo:

```json
{
    "reservationId": 1,
    "amount": 2500.00,
    "paymentMethod": "CARD",
    "transactionReference": "TXN-001"
}
```

---

## Actualizar pago

```
PUT /api/payments/{id}
```

Ejemplo:

```json
{
    "reservationId": 1,
    "amount": 2800.00,
    "paymentMethod": "CARD",
    "status": "APPROVED",
    "transactionReference": "TXN-001-UPDATED",
    "paymentDate": "2026-07-08T16:30:00",
    "active": true
}
```

---

## Eliminar (lógico)

```
DELETE /api/payments/{id}
```

El registro permanece almacenado en la base de datos, pero el campo:

```
active
```

se establece en:

```
false
```

---

# Validaciones

El servicio implementa validaciones mediante Jakarta Validation.

Algunas reglas son:

- reservationId obligatorio.
- amount obligatorio.
- amount mayor que cero.
- paymentMethod obligatorio.

---

# Manejo de excepciones

El proyecto implementa un manejador global de excepciones mediante:

```
@RestControllerAdvice
```

Errores controlados:

- Recurso inexistente.
- Errores de validación.
- Solicitudes inválidas.

Ejemplo:

```json
{
    "timestamp": "2026-07-08T18:40:00",
    "status": 404,
    "error": "Not Found",
    "message": "Pago no encontrado con ID: 100",
    "path": "/api/payments/100"
}
```

---

# Swagger / OpenAPI

Documentación disponible en:

```
http://localhost:8084/swagger-ui/index.html
```

OpenAPI:

```
http://localhost:8084/v3/api-docs
```

---

# Docker

Construcción de la imagen:

```bash
mvn clean package -DskipTests

docker build -t payment-service:latest .
```

Ejecución:

```bash
docker run -p 8084:8084 payment-service:latest
```

Ver imágenes:

```bash
docker images
```

---

# Compilar el proyecto

```bash
mvn clean install
```

Ejecutar:

```bash
mvn spring-boot:run
```

---

# Pruebas

Las pruebas del CRUD fueron realizadas utilizando:

- Postman
- PostgreSQL
- Swagger UI

Operaciones verificadas:

- Crear pago
- Consultar todos los pagos
- Consultar por ID
- Actualizar pago
- Eliminación lógica
- Validaciones
- Manejo de excepciones

---

# Estado del microservicio

✔ CRUD completo

✔ DTO

✔ Validaciones

✔ Manejo global de excepciones

✔ Swagger/OpenAPI

✔ PostgreSQL

✔ Docker

✔ README

---

# Integración con la plataforma

Este microservicio forma parte del proyecto:

```
Hotel Reservation Platform
```

Arquitectura prevista:

```
API Gateway
        │
        ├──────── hotel-service
        ├──────── availability-service
        ├──────── reservation-service
        ├──────── payment-service
        ├──────── confirmation-service
        └──────── calendar-service
```

---

# Autor

Proyecto desarrollado como parte del proyecto académico de Arquitectura Orientada a Servicios (SOA).

Ingeniería en Software.

Universidad Politécnica de Tapachula.