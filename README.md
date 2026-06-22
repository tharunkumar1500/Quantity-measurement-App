# UC17: Spring Boot Migration

## Overview
This branch (`feature/uc17-layer-testing`) focuses on a massive architectural upgrade: migrating the application from a plain Java Maven project into a fully-fledged **Spring Boot Application**. This modernizes the backend, implements proper Dependency Injection, converts the core into a REST API, and integrates Spring Data JPA for robust, boilerplate-free database operations.

## Features Implemented in UC17

1. **Spring Boot Core Integration**:
   - Upgraded `pom.xml` to use `spring-boot-starter-parent`.
   - Added Spring Boot starters: `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, and `spring-boot-starter-test`.
   - Transformed the `QuantityMeasurementApp` entry point into a `@SpringBootApplication`.

2. **REST Controller Architecture**:
   - Refactored `QuantityMeasurementController` into a `@RestController` mapped to `/api/measurement`.
   - Exposed RESTful endpoints using `@PostMapping` (`/compare`, `/add`, `/subtract`, `/divide`, `/convert`) that parse JSON payloads natively.
   - Handled JSON mappings natively utilizing Jackson serialization.

3. **Service Layer Evolution**:
   - Upgraded the `QuantityMeasurementServiceImpl` by annotating it with `@Service` to make it a Spring-managed Bean.
   - Simplified dependency injection by using `@Autowired` to automatically inject the repository.

4. **Database Overhaul (Spring Data JPA)**:
   - Removed all manual JDBC boilerplate logic, including manual query mappings and custom `ConnectionPool` utilities.
   - Converted `QuantityMeasurementEntity` into a true JPA `@Entity` mapped to the `quantity_measurement_history` table.
   - Upgraded `IQuantityMeasurementRepository` to extend `JpaRepository`, granting powerful automated CRUD operations out of the box.

5. **Advanced Layer Testing Contexts**:
   - Adapted tests to use the `@SpringBootTest` and `@WebMvcTest` environments.
   - Implemented `@MockBean` within tests to dynamically swap dependencies at runtime for isolation testing.
   - 64 tests successfully executed natively within the Spring Test Context.

## Running the Application

**Build and Run Tests:**
```bash
mvn clean test
```

**Run the Spring Boot Server:**
```bash
mvn spring-boot:run
```

Once running, the in-memory H2 Database Console can be accessed locally in your browser at:
`http://localhost:8080/h2-console`
*(Make sure to use JDBC URL `jdbc:h2:mem:quantity_db` to log in)*
