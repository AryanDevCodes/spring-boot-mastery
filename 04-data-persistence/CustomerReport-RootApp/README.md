# Customer Report Application

## Overview
This is a Spring Boot application that demonstrates customer management functionality using Spring Data JPA.

## Features
- Customer entity management
- Repository pattern implementation
- Service layer with business logic
- Data persistence with H2 database

## Technologies Used
- Spring Boot 3.4.4
- Spring Data JPA
- Spring Web
- Thymeleaf
- H2 Database
- Java 21

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── org/practice/customerreport/
│   │       ├── modal/          # Customer entity and data models
│   │       ├── repo/           # Repository interfaces
│   │       └── service/        # Business logic services
│   └── resources/              # Application properties and templates
└── test/                       # Test classes
```

## Annotations Demonstrated
- `@Service` - Service layer components
- `@Repository` - Data access layer
- `@Entity` - JPA entities
- `@Autowired` - Dependency injection
- `@Component` - Spring-managed components

## How to Run
```bash
./mvnw spring-boot:run
```

## Note
This project was originally at the root level of the Annotations workspace but has been moved here to maintain clean project structure and avoid confusion with the learning materials.
