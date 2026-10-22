# Spring Boot Person API

**A small REST coursework project for creating, finding, updating and deleting person records.**

Built with Java 17 and Spring Boot 3.2.4, this exercise demonstrates annotated HTTP endpoints, ResponseEntity, a generic response wrapper and OpenAPI documentation. Records live in a controller-owned ArrayList; no database or external service is configured.

This is a learning implementation, not a production person-record service. Restarting the process clears all records and resets ID allocation.

## Run locally

Suggested commands from the original repository root, with JDK 17 available:

### Windows PowerShell

```powershell
.\mvnw.cmd spring-boot:run
```

### POSIX shell

```sh
sh mvnw spring-boot:run
```

The supplied wrapper selects Maven 3.9.14. Initial use may download Maven and dependencies. No environment variables, database credentials or separate backend setup are required by the supplied application configuration. These commands were not executed during this refresh.

The configured port is 9090. Open the [local Swagger UI](http://localhost:9090/swagger-ui.html) or [local OpenAPI JSON](http://localhost:9090/api-docs) after starting the app. These are configured local addresses, not verified live services.

## Endpoints

Base path: `/api/v1/person`. Collection mappings use that exact path without an additional trailing slash.

| Method | Path | Implemented result |
| --- | --- | --- |
| POST | `/api/v1/person` | Assign an ID and append a record; HTTP 201 |
| GET | `/api/v1/person` | Return the full list; HTTP 200 |
| GET | `/api/v1/person/{id}` | Return a record or HTTP 404 |
| GET | `/api/v1/person/search?name=Alex` | First case-insensitive full-name match or HTTP 404 |
| PUT | `/api/v1/person/{id}` | Replace name, gender and age on an existing record; HTTP 200 or 404 |
| DELETE | `/api/v1/person/{id}` | Remove and return the record; HTTP 200 or 404 |

Search is not substring matching, does not trim input and returns only the first matching record. Duplicate names are permitted. IDs start at 1; POST overrides any submitted ID, while PUT preserves the path-selected record's ID.

### Example request body

Synthetic example for POST or PUT:

```json
{
  "name": "Alex",
  "gender": "Not specified",
  "age": 20
}
```

This is illustrative input, not a captured request/result. No validation rules constrain these fields in the supplied model or controller. PUT copies all three values, including nulls when fields are omitted; it is not a partial-update endpoint.

## Response structure

Controller-created responses contain message, payload, status and time. The payload can be a person, a list or null for a missing record.

The status field is typed Object: create/update/delete use numeric values, while reads and missing-record responses use strings such as OK and NOT_FOUND. Clients should not assume a consistent JSON type for this field. The time field is LocalDateTime.now() at response construction, not execution duration or an explicitly zoned UTC timestamp.

The wrapper is used by these controller paths, not a supplied global error handler. Framework-level parsing/binding errors are not explicitly wrapped here.

## Source map

- [PersonController.java](src/main/java/com/example/springhomework001/controller/PersonController.java): endpoint handlers, list storage and lookup.
- [Person.java](src/main/java/com/example/springhomework001/model/Person.java): Lombok-backed ID, name, gender and age fields.
- [ApiResponse.java](src/main/java/com/example/springhomework001/response/ApiResponse.java): generic payload and response metadata.
- [application.properties](src/main/resources/application.properties): port and OpenAPI paths.
- [pom.xml](pom.xml): Java 17, Spring Boot 3.2.4, Spring Web, Lombok and Springdoc 2.3.0.

There is no separate service/repository layer in this exercise. AtomicInteger handles ID increments, but does not make the shared ArrayList or multi-step operations thread-safe.

## Checks and limitations

Suggested Windows checks:

```powershell
.\mvnw.cmd test
.\mvnw.cmd package
```

POSIX equivalents use `sh mvnw test` and `sh mvnw package`. The supplied test is a single contextLoads smoke test with no endpoint assertions. Its presence is not evidence that CRUD, validation or concurrency scenarios pass.

No authentication, persistence, pagination, field validation or central exception handler is supplied. A stored null name can fail the search path because it calls equalsIgnoreCase on that value. Use synthetic data and keep this as a local demonstration.

No Maven command, compilation, test, HTTP request or application startup was performed during this documentation refresh. Read [CONTRIBUTING.md](CONTRIBUTING.md), [SECURITY.md](SECURITY.md) and [refresh notes](docs/repository-refresh.md).

## Licence

No root project licence file is included in the export. The Maven wrapper carries its own Apache licence notices; those do not establish a licence for the coursework. This refresh assigns none.
