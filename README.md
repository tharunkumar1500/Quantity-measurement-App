# Quantity Measurement Application (UC8) - Refactoring Unit Enum to Standalone

This project implements the eighth use case of the Quantity Measurement Application. It focuses on an architectural refactoring to eliminate inner classes, resolve Single Responsibility Principle (SRP) violations, and establish a scalable structure for future measurement categories.

## Features
- **Standalone Unit Enum**: `LengthUnit` is now a standalone enum that fully encapsulates base unit conversion logic.
- **Standalone Quantity Class**: The `Quantity` class is now a top-level entity that delegates conversion operations to the unit class, making it loosely coupled.
- **Extended Unit Support**: Supports `FEET`, `INCHES`, `YARD`, and `CM`.
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

### `LengthUnit.java`
A standalone Enum managing the length units:
- `LengthUnit`: Manages the conversion factor relative to the base unit for Feet, Inches, Yards, and CM.
- `convertToBaseUnit(double value)` & `convertFromBaseUnit(double baseValue)`: Encapsulates all mathematical conversion responsibility securely inside the Enum.

### `Quantity.java`
A standalone Class representing the mathematical model:
- `Quantity` constructor: Validates inputs (throws `IllegalArgumentException` on NaN, Infinity, or null unit).
- `convertTo(LengthUnit targetUnit)`: Delegates base unit arithmetic to `LengthUnit` and returns the precise `double` numeric value.
- `add(...)`: Overloaded methods to handle summation with and without an explicit target unit, returning an immutable `Quantity` object.
- `equals(Object obj)` method: Handles references, null checks, type checks, and precise floating-point comparison.

### `QuantityMeasurementTest.java`
JUnit 5 tests verifying:
- **Refactored APIs**: Proves 100% backward compatibility of previous test logic with the new standalone classes.
- **Target Unit Addition Tests (UC7)**: Tests summation with an explicit output unit.
- **Addition Tests (UC6)**: Tests basic summation logic.
- **Validation Tests**: Ensures constructors and methods reject invalid states (`null`, `NaN`, `Infinity`).
- **Conversion Tests**: Tests explicit numeric conversion.
- **Base Equality & Cross-Unit Checks**: Validating comparison logic across all 4 units.
