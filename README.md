# Quantity Measurement App

A Spring Boot Application that provides functionalities to compare and manipulate various measurements like length, volume, weight, and temperature.

## Architecture Evolution (Up to UC17)
The application has successfully migrated from a plain Java Maven project into a fully-fledged **Spring Boot Application**. This modernizes the backend, implements proper Dependency Injection, converts the core into a REST API, and integrates Spring Data JPA for robust, boilerplate-free database operations.

## Key Features

1. **Spring Boot Core Integration**:
   - Built on `spring-boot-starter-parent`.
   - Utilizes `spring-boot-starter-web` and `spring-boot-starter-data-jpa`.

2. **REST Controller Architecture**:
   - `QuantityMeasurementController` is a `@RestController` mapped to `/api/measurement`.
   - Exposed RESTful endpoints using `@PostMapping` (`/compare`, `/add`, `/subtract`, `/divide`, `/convert`) that parse JSON payloads natively.

3. **Service Layer**:
   - `QuantityMeasurementServiceImpl` annotated with `@Service`.

4. **Database (Spring Data JPA)**:
   - `QuantityMeasurementEntity` is a JPA `@Entity` mapped to the `quantity_measurement_history` table.
   - Leverages `JpaRepository` for automated CRUD operations.
   - Built-in `H2` in-memory database for rapid execution.

5. **Testing**:
   - Uses `@SpringBootTest` and `@WebMvcTest` environments.
   - Fully isolated Mockito layer testing (`@MockBean`).
   - Extensive unit test suite of 64+ automated tests.

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
