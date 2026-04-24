# [>_] SPRING-HOMEWORK-001 : BASIC REST API

**AUTHOR:** SRUN-Sochettra  
**MODULE:** Person Management API  

## // SYSTEM_SPECS

* **FRAMEWORK:** Spring Boot 3.2.4
* **LANGUAGE:** Java 17
* **DEPENDENCIES:** Spring Web, Lombok, Springdoc OpenAPI (v2.3.0)
* **PORT:** 9090
* **PERSISTENCE:** In-Memory List (Volatile)

## // ARCHITECTURE

Implements a standard RESTful controller architecture. All endpoint responses are uniformly wrapped in an `ApiResponse<T>` structure containing execution time, operational status, system message, and the generic data payload.

### DATA_MODEL : PERSON
```json
{
  "id": "Integer (Auto-incremented)",
  "name": "String",
  "gender": "String",
  "age": "Integer"
}
```

## // ENDPOINTS

**BASE_URL:** `http://localhost:9090/api/v1/person`

| METHOD | ROUTE | OPERATION | STATUS CODES |
| :--- | :--- | :--- | :--- |
| `POST` | `/` | Insert a new person record | 201 |
| `GET` | `/` | Retrieve all records | 200 |
| `GET` | `/{id}` | Retrieve specific record by ID | 200, 404 |
| `GET` | `/search?name=` | Retrieve record by exact name match | 200, 404 |
| `PUT` | `/{id}` | Update existing record by ID | 200, 404 |
| `DELETE` | `/{id}` | Remove record by ID | 200, 404 |

## // DOCUMENTATION_INTERFACE

* **Swagger UI:** `http://localhost:9090/swagger-ui.html`
* **OpenAPI JSON:** `http://localhost:9090/api-docs`

## // EXECUTION_PROTOCOL

Initialize the application via Maven Wrapper:

```bash
# Windows
mvnw.cmd spring-boot:run

# Unix/Linux
./mvnw spring-boot:run
```
