# UC16: Database Integration and Maven Standardization

## Overview
This branch (`feature/uc16-database-integration`) focuses strictly on integrating a relational database to persist quantity measurements and standardizing the project into a professional Maven directory structure.

## Features Implemented in UC16

1. **Standard Maven Structure**: 
   - Relocated all source code to `src/main/java/com/app/quantitymeasurement`.
   - Relocated all test files to `src/test/java/com/app/quantitymeasurement`.

2. **H2 Database Integration**:
   - Implemented `QuantityMeasurementDatabaseRepository` which securely executes standard JDBC `PreparedStatement`s to insert and retrieve mathematical operations.
   - Designed a Singleton `ConnectionPool.java` to handle JDBC connections dynamically, including validation, timeout, and connection release logic.
   - Introduced `schema.sql` to automatically initialize the `quantity_measurement_history` table at runtime.

3. **Application Configuration**:
   - Added `application.properties` to manage the database connection details.
   - Included a toggle to dynamically switch between `DATABASE` persistence and `CACHE` persistence.

4. **SLF4J Logging Framework**:
   - Replaced all legacy `System.out.println()` logs with SLF4J (`logger.info`, `logger.error`, etc.) paired with `logback-classic`.

5. **Testing & Verification**:
   - Implemented `QuantityMeasurementDatabaseRepositoryTest` for isolated JDBC testing.
   - Implemented `QuantityMeasurementIntegrationTest` for end-to-end verifications.
   - All tests pass via Maven.

## Running the Code

**Build and Run Tests:**
```bash
mvn clean test
```

**Run the Application:**
```bash
mvn exec:java -Dexec.mainClass="com.app.quantitymeasurement.QuantityMeasurementApp"
```
