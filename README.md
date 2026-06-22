# Quantity Measurement Application (UC5) - Unit-to-Unit Conversion

This project implements the fifth use case of the Quantity Measurement Application. Building upon the previous use cases, it introduces a robust unit-to-unit conversion API. Clients can now convert numeric values seamlessly between Length units while handling precision and validation accurately.

## Features
- **Generic Quantity Class**: A unified `Quantity` class to represent any measurement with a value and a unit type.
- **Extended Unit Support**: The `LengthUnit` Enum supports `FEET`, `INCHES`, `YARD`, and `CM`.
- **Unit Conversion API**: Introduces `convertTo(LengthUnit targetUnit)` to explicitly convert a given measurement to any other supported unit and retrieve the numeric value.
- **Robust Validation**: Rejects invalid states like `null` units, `NaN` values, and `Infinite` values via `IllegalArgumentException`.
- **Precise Cross-Unit Equality Check**: Accurately compares measurements across different unit types using precision base-unit conversion logic.
- **Comprehensive Testing**: JUnit 5 tests verifying equality, inequality, cross-unit comparison, conversions, and invalid-input exception handling.

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
- `Quantity` constructor: Validates inputs (throws `IllegalArgumentException` on NaN, Infinity, or null unit).
- `Quantity.convertTo(LengthUnit targetUnit)`: Converts the measurement to the specified target unit and returns the precise `double` numeric value.
- `Quantity.equals(Object obj)` method: Handles references, null checks, type checks, and precise floating-point comparison using base unit conversion logic.

### `QuantityMeasurementTest.java`
JUnit 5 tests verifying:
- **Validation Tests**: Ensures constructors and methods reject invalid states (`null`, `NaN`, `Infinity`).
- **Conversion Tests**: Tests explicit numeric conversion:
  - 1 Foot to Inches -> 12.0
  - 36 Inches to Yards -> 1.0
  - 1 Inch to CM -> 2.54
  - 2 Yards to Feet -> 6.0
  - 1 Yard to CM -> 91.44
- **Base Equality Tests**: Basic comparison between identical units.
- **Same Unit Checks**: Validating unit-to-unit comparison.
- **Cross Unit Checks**: Validating cross-unit comparisons across all 4 units.
