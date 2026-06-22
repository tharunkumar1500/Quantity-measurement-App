# Quantity Measurement Application (UC6) - Addition of Two Length Units

This project implements the sixth use case of the Quantity Measurement Application. Building upon the previous use cases, it introduces the ability to perform arithmetic operations, specifically adding two length measurements of potentially different units, and returning the result in the unit of the first operand.

## Features
- **Generic Quantity Class**: A unified `Quantity` class to represent any measurement with a value and a unit type.
- **Extended Unit Support**: The `LengthUnit` Enum supports `FEET`, `INCHES`, `YARD`, and `CM`.
- **Unit Conversion API**: Includes `convertTo(LengthUnit targetUnit)` for explicit conversions.
- **Arithmetic Operations (Addition)**: Introduces the `add(Quantity other)` method to seamlessly add two units (e.g., Feet + Inches) and return a new immutable `Quantity` object.
- **Robust Validation**: Rejects invalid states like `null` units, `NaN` values, and `Infinite` values via `IllegalArgumentException`.
- **Comprehensive Testing**: JUnit 5 tests verifying equality, cross-unit comparison, conversions, valid/invalid additions, and exception handling.

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
Quantity(1.0, "feet") equals Quantity(12.0, "inches"): true
Quantity(2.0, "inches") + Quantity(2.54, "cm") = Quantity(3.0, "inches")
```

## Code Explanation

### `QuantityMeasurementApp.java`
Contains the `LengthUnit` Enum and `Quantity` class with the following:
- `LengthUnit`: Manages the conversion factor relative to the base unit for Feet, Inches, Yards, and CM.
- `Quantity` constructor: Validates inputs (throws `IllegalArgumentException` on NaN, Infinity, or null unit).
- `Quantity.convertTo(LengthUnit targetUnit)`: Converts the measurement to the specified target unit and returns the precise `double` numeric value.
- `Quantity.add(Quantity other)`: Adds two different length measurements by internally using `convertTo` and returning a new immutable `Quantity` object representing the sum.
- `Quantity.equals(Object obj)` method: Handles references, null checks, type checks, and precise floating-point comparison using base unit conversion logic.

### `QuantityMeasurementTest.java`
JUnit 5 tests verifying:
- **Addition Tests (UC6)**: Tests explicit summation logic across various units:
  - 2 Inches + 2 Inches -> 4.0 Inches
  - 1 Foot + 2 Inches -> 14.0 Inches (if evaluated relative to inches)
  - 2 Inches + 2.54 CM -> 3.0 Inches
- **Validation Tests**: Ensures constructors and methods reject invalid states (`null`, `NaN`, `Infinity`).
- **Conversion Tests**: Tests explicit numeric conversion (e.g., 1 Yard to CM -> 91.44).
- **Base Equality & Cross-Unit Checks**: Validating comparison logic across all 4 units.
