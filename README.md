# UC17: Isolated Layer Testing with Mockito

## Overview
This branch (`feature/uc17-layer-testing`) focuses on enforcing N-Tier architectural testing standards. It ensures that the Controller and Service layers can be tested completely in isolation from the actual database utilizing Mockito.

## Features Implemented in UC17

1. **Mockito Integration**:
   - Integrated Mockito framework to create mock instances of dependencies.
   - Used `@ExtendWith(MockitoExtension.class)`, `@Mock`, and `@InjectMocks` annotations for clean test dependency injection.

2. **Isolated Service Layer Testing (`QuantityMeasurementServiceTest.java`)**:
   - Mocked the `IQuantityMeasurementRepository` layer to prevent actual database hits during business logic verification.
   - Tested arithmetic logic, comparisons, unit constraints (e.g. attempting to compare Length with Weight), and ensured the service correctly interacts with the mock repository (`repository.save()`).

3. **Isolated Controller Layer Testing (`QuantityMeasurementControllerTest.java`)**:
   - Mocked the `IQuantityMeasurementService` layer to isolate the entry-point execution.
   - Verified that the controller successfully unpacks the DTOs and delegates the calculations to the Service layer securely using `verify(service, times(1))`.

4. **Testing Verification**:
   - Running the test suite safely ignores actual database constraints and connections for the mocked layers, significantly improving test speed and reliability.
   - Combined with previous integration tests, the suite now successfully executes **79 automated tests**.

## Running the Code

**Build and Run Tests:**
```bash
mvn clean test
```

**Run the Application:**
```bash
mvn exec:java -Dexec.mainClass="com.app.quantitymeasurement.QuantityMeasurementApp"
```
