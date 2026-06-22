# UC15: N-Tier Architecture Refactoring

## Overview
This branch (`feature/uc15-ntier-architecture`) focuses entirely on refactoring the monolithic codebase into a professional, scalable, and decoupled N-Tier architecture pattern.

## Features Implemented in UC15

1. **Controller Layer (`QuantityMeasurementController.java`)**: 
   - Acts as the presentation entry-point.
   - Delegates all business requests to the underlying Service layer and prints out the results cleanly.

2. **Service Layer (`IQuantityMeasurementService` & `QuantityMeasurementServiceImpl`)**:
   - Encapsulates core business logic and logic-flow operations.
   - Explicitly ensures that arithmetic or comparisons are only executed after validating that the two distinct units belong to compatible measurement domains (e.g., Length and Length).

3. **Repository Layer (`IQuantityMeasurementRepository` & `QuantityMeasurementCacheRepository`)**:
   - Completely decouples the data access layer.
   - Currently implemented as an in-memory cache repository (`List<QuantityMeasurementEntity> cache`) allowing for seamless replacement with a persistent DB in the future.

4. **Entity/Model Layer**:
   - **`QuantityDTO`**: A robust Data Transfer Object representing the value and unit of a measurement being passed from the frontend/Controller.
   - **`QuantityModel`**: The core logic model containing the generic type validation rules, conversions, and math logic.
   - **`QuantityMeasurementEntity`**: A specialized POJO designed to store the operation history and result states of every calculation securely.

5. **Testing & Verification**:
   - Re-aligned the existing JUnit 5 test suite to run against the new architecture cleanly. 
   - The refactored N-Tier architecture continues to securely pass all 67 Unit Tests without breaking previously established constraints.

## Running the Code

**Build and Run Tests:**
```bash
mvn clean test
```

**Run the Application:**
```bash
mvn exec:java -Dexec.mainClass="quantitymeasurement.QuantityMeasurementApp"
```
