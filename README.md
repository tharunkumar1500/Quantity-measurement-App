# Quantity Measurement Application (UC9) - Weight Measurement (Kilogram, Gram, Pound)

This project implements the ninth use case of the Quantity Measurement Application. It introduces a new measurement category, **Weight**, and successfully scales the generic architectural design to support independent, incomparable measurement categories (Length and Weight) side-by-side securely.

## Features
- **Generic Unit Interface**: `Unit` is an interface establishing base conversion contracts.
- **Standalone Enums**: 
  - `LengthUnit` supports `FEET`, `INCHES`, `YARD`, and `CM`.
  - `WeightUnit` supports `KILOGRAM`, `GRAM`, and `POUND`.
- **Standalone Generic Quantity Class**: The `Quantity` class is category-agnostic. It delegates math to the respective unit implementation.
- **Strict Category Boundaries**: Validates operations (add, equals, convert) and throws `IllegalArgumentException` if a mix between Length and Weight is attempted.
- **Arithmetic Operations (Addition)**: 
  - `add(Quantity other)`: Adds two units and returns the result in the unit of the first operand.
  - `add(Quantity other, Unit targetUnit)`: Adds two units and returns the result in the explicitly specified target unit.
- **Robust Validation**: Rejects invalid states like `null` units, `NaN` values, and `Infinite` values via `IllegalArgumentException`.
- **Comprehensive Testing**: JUnit 5 tests verifying equality, cross-unit comparison, conversions, explicit target additions, cross-category boundary safety, and exception handling.

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

### `Unit.java`
An interface establishing unit contract rules (`convertToBaseUnit` & `convertFromBaseUnit`).

### `LengthUnit.java` & `WeightUnit.java`
Standalone Enums managing length and weight units securely via internal factors, implementing the `Unit` interface.

### `Quantity.java`
A standalone Class representing the mathematical model:
- `Quantity` constructor: Validates inputs.
- `convertTo(Unit targetUnit)`: Delegates base unit arithmetic. Ensures `targetUnit` is of the exact same category.
- `add(...)`: Overloaded methods to handle summation with category checks.
- `equals(Object obj)` method: Handles references, type checks, and category isolation, enabling precise floating-point comparison only when categories match.

### `QuantityMeasurementTest.java`
JUnit 5 tests verifying:
- **Weight Tests (UC9)**: Validates equality, conversion, and addition across Kg, G, and Lbs.
- **Cross Category Boundary Tests (UC9)**: Proves Length vs Weight operations are prohibited and throw `IllegalArgumentException`.
- **Target Unit Addition Tests (UC7)**: Tests summation with an explicit output unit.
- **Addition Tests (UC6)**: Tests basic summation logic.
- **Validation Tests**: Ensures constructors and methods reject invalid states (`null`, `NaN`, `Infinity`).
- **Conversion Tests**: Tests explicit numeric conversion.
- **Base Equality & Cross-Unit Checks**: Validating comparison logic across all length units.
