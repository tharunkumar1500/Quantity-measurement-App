# Quantity Measurement Application (UC4) - Extended Unit Support

This project implements the fourth use case of the Quantity Measurement Application. Building upon the generic `Quantity` class introduced in UC3, it adds support for new measurement units: Yards and Centimeters (CM), demonstrating the power and flexibility of the DRY (Don't Repeat Yourself) principle.

## Features
- **Generic Quantity Class**: A unified `Quantity` class to represent any measurement with a value and a unit type.
- **Extended Unit Support**: The `LengthUnit` Enum now supports `FEET`, `INCHES`, `YARD`, and `CM`.
- **Precise Cross-Unit Equality Check**: Accurately compares measurements across different unit types using precision base-unit conversion logic.
- **Comprehensive Testing**: JUnit 5 tests verifying equality, inequality, and cross-unit comparison across all supported units (e.g., Yard to Inches, Inches to CM).

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
- `LengthUnit`: Manages the conversion factor relative to the base unit for Feet, Inches, Yards, and CM.
- `Quantity.equals(Object obj)` method implementing:
  - Reference check (same object)
  - Null check and type check
  - Base unit conversion utilizing `LengthUnit`
  - Value comparison using `Double.compare()` for precise equality check.

### `QuantityMeasurementTest.java`
JUnit 5 tests verifying:
- **Base Equality Tests**: Basic comparison between identical units.
- **Same Unit Checks**: Validating unit-to-unit comparison.
- **Cross Unit Checks**: Validating cross-unit comparisons across all 4 units:
  - 1 Foot == 12 Inches
  - 1 Yard == 36 Inches
  - 1 Yard == 3 Feet
  - 2 Inches == 5.08 CM
