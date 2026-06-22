# Quantity Measurement Application (UC3) - Generic Quantity

This project implements the third use case of the Quantity Measurement Application. It refactors the codebase to eliminate the duplication between Feet and Inches by introducing a generic `Quantity` class and a `LengthUnit` enum, strictly adhering to the DRY (Don't Repeat Yourself) principle.

## Features
- **Generic Quantity Class**: A unified `Quantity` class to represent any measurement with a value and a unit type.
- **LengthUnit Enum**: Defines measurement units (`FEET`, `INCHES`) and handles precise base unit conversion logic.
- **Precise Equality Check**: The `equals()` method converts measurements to a common base unit before performing floating-point comparison.
- **Comprehensive Testing**: JUnit 5 tests verifying equality, inequality, cross-unit comparison, null checks, and class type validation.

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
--- Generic Quantity Equality ---
Quantity(1.0, "feet") equals Quantity(1.0, "feet"): true
Quantity(1.0, "feet") equals Quantity(2.0, "feet"): false
Quantity(1.0, "feet") equals Quantity(12.0, "inches"): true
```

## Code Explanation

### `QuantityMeasurementApp.java`
Contains the `LengthUnit` Enum and `Quantity` class with the following:
- `LengthUnit`: Manages the conversion factor relative to the base unit.
- `Quantity.equals(Object obj)` method implementing:
  - Reference check (same object)
  - Null check and type check
  - Base unit conversion utilizing `LengthUnit`
  - Value comparison using `Double.compare()` for precise equality check.

### `QuantityMeasurementTest.java`
JUnit 5 tests verifying:
- **Base Equality Tests**: Basic comparison between identical units (Feet).
- **Same Unit Checks**: Validating Inches to Inches comparison.
- **Cross Unit Checks**: Validating cross-unit comparisons:
  - 0 Feet == 0 Inches
  - 1 Foot == 12 Inches
  - 1 Foot != 1 Inch
