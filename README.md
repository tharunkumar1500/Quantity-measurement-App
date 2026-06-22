# Quantity Measurement App (UC1 - UC16)

The Quantity Measurement App is a robust, N-Tier Java application designed to cleanly and safely measure, compare, and perform arithmetic operations across multiple distinct quantities. It enforces rigid domain boundaries for Length, Weight, Volume, and Temperature, while allowing flexible conversions within a domain.

## Features

### UC1 - UC14: Core Domain Logic & Units
- **Length**: Supports Inches, Feet, Yards, and Centimeters.
- **Weight/Mass**: Supports Grams, Kilograms, and Tonnes.
- **Volume**: Supports Liters, Milliliters, and Gallons.
- **Temperature**: Supports Celsius and Fahrenheit (including complex formula-based bidirectional conversion).
- **Arithmetic Operations**: Addition, Subtraction, and Division are supported for compatible units.
- **Operational Safety**: Compile-time category safety prevents adding Length to Weight. Runtime validations prevent arithmetic on Temperature objects (which throws `UnsupportedOperationException`).

### UC15: N-Tier Architecture Refactoring
The application underwent a major structural refactor to adopt a professional, layered architecture:
- **Controller Layer (`QuantityMeasurementController`)**: Exposes methods for interaction and orchestration.
- **Service Layer (`IQuantityMeasurementService`)**: Implements business rules, cross-category validations, and mathematical delegations.
- **Repository Layer (`IQuantityMeasurementRepository`)**: Abstraction for data persistence.
- **Entity/Model Layer (`QuantityMeasurementEntity`)**: Dedicated POJOs and DTOs to encapsulate the state of a measurement operation.

### UC16: Database Integration & Maven Standardization
The application was fully modernized to industrial standards:
- **Standard Maven Structure**: All code relocated to `src/main/java/com/app/quantitymeasurement` and `src/test/java...`.
- **H2 Database Integration**: Implemented `QuantityMeasurementDatabaseRepository` utilizing a Singleton `ConnectionPool` to persist all operation history (operands, units, results, errors) using standard JDBC `PreparedStatement`s.
- **Application Configuration**: Driven by `application.properties`, allowing dynamic swapping between `CACHE` and `DATABASE` persistence modes.
- **SLF4J Logging**: Replaced raw `System.out.println` with robust, leveled logging.

## Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven 3.6 or higher

## Getting Started

### Running Tests
To run the full JUnit 5 and Mockito suite (74+ Unit and Integration Tests):
```bash
mvn clean test
```

### Building the Application
To compile and package the application into a Fat JAR using the Maven Shade plugin:
```bash
mvn clean package
```

### Running the Application
To run the main demonstration through Maven:
```bash
mvn exec:java -Dexec.mainClass="com.app.quantitymeasurement.QuantityMeasurementApp"
```

## Architectural Highlights

### Database Utilities
- **`ConnectionPool.java`**: Custom implementation managing a fixed number of JDBC connections, including timeout logic, validation checking, and graceful closure.
- **`schema.sql`**: Auto-creates the `quantity_measurement_history` table at runtime.

### Logging
All operational interactions, database saves, exceptions, and app states are logged cleanly using SLF4J and Logback.

### Generic Safety
The `Quantity<U extends IMeasurable>` class ensures that mathematical evaluations are evaluated securely without mixing logical domains at the repository or service level.
