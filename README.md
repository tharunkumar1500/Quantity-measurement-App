# Quantity Measurement Application (UC7) - Addition with Target Unit Specification

This project implements the seventh use case of the Quantity Measurement Application. Building upon the previous use cases, it introduces the ability to perform arithmetic operations where the target unit for the result is explicitly specified by the caller, instead of defaulting to the first operand's unit.

## Features
- **Generic Quantity Class**: A unified `Quantity` class to represent any measurement with a value and a unit type.
- **Extended Unit Support**: The `LengthUnit` Enum supports `FEET`, `INCHES`, `YARD`, and `CM`.
- **Unit Conversion API**: Includes `convertTo(LengthUnit targetUnit)` for explicit conversions.
- **Arithmetic Operations (Addition)**: 
  - `add(Quantity other)`: Adds two units and returns the result in the unit of the first operand.
  - `add(Quantity other, LengthUnit targetUnit)`: Adds two units and returns the result in the explicitly specified target unit.
- **Robust Validation**: Rejects invalid states like `null` units, `NaN` values, and `Infinite` values via `IllegalArgumentException`.
- **Comprehensive Testing**: JUnit 5 tests verifying equality, cross-unit comparison, conversions, explicit target additions, and exception handling.

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
Quantity(1.0, "feet") equals Quantity(12.0, "inches"): true
Quantity(2.0, "inches") + Quantity(2.54, "cm") = Quantity(3.0, "inches")
Quantity(36.0, "inches") + Quantity(1.0, "yards") to "feet" = Quantity(6.0, "feet")
```

## Code Explanation

### `QuantityMeasurementApp.java`
Contains the `LengthUnit` Enum and `Quantity` class with the following:
- `LengthUnit`: Manages the conversion factor relative to the base unit for Feet, Inches, Yards, and CM.
- `Quantity` constructor: Validates inputs (throws `IllegalArgumentException` on NaN, Infinity, or null unit).
- `Quantity.convertTo(LengthUnit targetUnit)`: Converts the measurement to the specified target unit and returns the precise `double` numeric value.
- `Quantity.add(...)`: Overloaded methods to handle summation with and without an explicit target unit, returning an immutable `Quantity` object.
- `Quantity.equals(Object obj)` method: Handles references, null checks, type checks, and precise floating-point comparison using base unit conversion logic.

### `QuantityMeasurementTest.java`
JUnit 5 tests verifying:
- **Target Unit Addition Tests (UC7)**: Tests summation with an explicit output unit:
  - 1 Foot + 12 Inches to INCHES -> 24.0 Inches
  - 36 Inches + 1 Yard to FEET -> 6.0 Feet
  - 2.54 CM + 1 Inch to CM -> 5.08 CM
- **Addition Tests (UC6)**: Tests basic summation logic.
- **Validation Tests**: Ensures constructors and methods reject invalid states (`null`, `NaN`, `Infinity`).
- **Conversion Tests**: Tests explicit numeric conversion.
- **Base Equality & Cross-Unit Checks**: Validating comparison logic across all 4 units.
