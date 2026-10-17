# Student Management REST API

A RESTful CRUD application built using **Java, Spring Boot, Spring Data JPA, and MySQL** to manage student records. This project demonstrates layered architecture, database integration, HTTP response handling, and soft deletion.

## Features

- Create, retrieve, update, and delete student records.
- Retrieve individual students by ID or fetch all active students.
- Implement **soft deletion** by marking records as deleted.
- Support permanent deletion through hard delete.
- Validate student age during creation.
- Handle HTTP responses using `ResponseEntity`.

## Tech Stack

- **Language:** Java
- **Framework:** Spring Boot, Spring Web
- **ORM:** Spring Data JPA, Hibernate
- **Database:** MySQL
- **API Testing:** Postman
- **Build Tool:** Maven

## Project Architecture

```text
StudentController
       ↓
StudentService
       ↓
StudentRepository
       ↓
Spring Data JPA / Hibernate
       ↓
MySQL Database
```

## API Endpoints

Base URL: `http://localhost:8080/api/students`

| Method | Endpoint | Operation |
|---|---|---|
| POST | `/create` | Create student |
| GET | `/get?id={id}` | Get student by ID |
| GET | `/` | Get all active students |
| PUT | `/{id}` | Update student |
| DELETE | `/{id}` | Permanently delete student |
| PATCH | `/soft-delete/{id}` | Soft-delete student |

## Getting Started

**1. Clone the repository**

```bash
git clone https://github.com/its-silver-lynx/spring-boot-student-crud-api.git
```

**2. Create the MySQL database**

```sql
CREATE DATABASE student_crud_db;
```

**3. Configure your database credentials** in `application.properties` using your local MySQL settings.

**4. Run the application**

```bash
mvn spring-boot:run
```

Use Postman to test the API endpoints.

## Key Learnings

- REST API development with Spring Boot.
- Layered application architecture.
- Database operations using Spring Data JPA.
- HTTP status handling and request processing.
- Soft deletion versus permanent deletion.
