# Quantity Measurement Application (UC2) - Feet and Inches Equality

This project implements the second use case of the Quantity Measurement Application. It extends the app to support equality checks for Inches along with Feet. It uses Test-Driven Development (TDD) principles to ensure high-quality, reliable code.

## Features
- **Feet Measurement Class**: A robust inner class `Feet` to represent measurements in feet.
- **Inches Measurement Class**: A separate inner class `Inches` to represent measurements in inches.
- **Precise Equality Check**: Overridden `equals()` method for accurate comparison of both feet and inches.
- **Comprehensive Testing**: JUnit 5 tests covering equality, inequality, null checks, and class type validation for both Feet and Inches.

## Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven 3.6 or higher

## Getting Started

### Running Tests
To run the JUnit 5 tests and verify the implementation:
```bash
mvn test
```

### Running the Application
To run the main demonstration:
```bash
mvn exec:java -Dexec.mainClass="quantitymeasurement.QuantityMeasurementApp"
```

## Sample Output
```
--- Feet Equality ---
1.0 ft equals 1.0 ft: true
1.0 ft equals 2.0 ft: false
--- Inches Equality ---
1.0 inch equals 1.0 inch: true
1.0 inch equals 2.0 inch: false
```

## Code Explanation

### `QuantityMeasurementApp.java`
Contains the `Feet` and `Inches` inner classes with the following:
- `equals(Object obj)` method implementing:
  - Reference check (same object)
  - Null check
  - Type check (must be the correct instance)
  - Value comparison using `Double.compare()` for floating-point precision

### `QuantityMeasurementTest.java`
JUnit 5 tests verifying:
- Same value equality for both Feet and Inches.
- Different values inequality for both Feet and Inches.
- Comparing with null returning false.
- Different class types returning false.
- Same object reference returning true.
