# Academix User Service

This is the User Service for the Academix platform. It manages registration and user data for students, teachers, staff, and other users.

## Features
- Unified registration endpoint for all user types
- Secure authentication and user management
- RESTful API with OpenAPI/Swagger documentation
- Docker and docker-compose support for easy setup

## API Documentation
- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI Spec:** [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

## Unified Registration Endpoint
- `POST /api/v1/auth/register`
- Accepts all user types (student, teacher, staff, etc.) via the `userType` field in the request body.

## Local Development

### Prerequisites
- Java 21
- Gradle (or use the provided wrapper)
- PostgreSQL (or use docker-compose)

### Build & Run
```bash
cd USER-SERVICE
./gradlew build
./gradlew bootRun
```

### Running with Docker
Build the Docker image:
```bash
docker build -t academix-user-service .
```
Run the container:
```bash
docker run -p 8080:8080 academix-user-service
```

### Using docker-compose
To start the service with PostgreSQL and Consul:
```bash
docker-compose up
```

## Configuration
- Application config: `USER-SERVICE/src/main/resources/application.yml`
- Default port: `8080`
- Database: PostgreSQL (see docker-compose for credentials)

## Useful Links
- [Swagger UI](http://localhost:8080/swagger-ui.html)
- [OpenAPI JSON](http://localhost:8080/api-docs)

---
For any issues, please contact the Academix team or open an issue in this repository.
