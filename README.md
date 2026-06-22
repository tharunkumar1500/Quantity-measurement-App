# Quantity Measurement Application (UC14) - Temperature Measurement

This project implements the fourteenth use case of the Quantity Measurement Application. It introduces **Temperature Measurement** (Celsius and Fahrenheit) while refactoring the generic `IMeasurable` interface to optionally disable arithmetic operations for measurement categories where they don't logically apply (like Temperature).

## Features
- **Generic Unit Interface**: `IMeasurable` is an interface standardizing conversion behaviors and operational support validation across categories.
- **Standalone Enums**: 
  - `LengthUnit` implements `IMeasurable` and supports `FEET`, `INCHES`, `YARD`, and `CM`.
  - `WeightUnit` implements `IMeasurable` and supports `KILOGRAM`, `GRAM`, and `POUND`.
  - `VolumeUnit` implements `IMeasurable` and supports `LITRE`, `MILLILITRE`, and `GALLON`.
  - `TemperatureUnit` implements `IMeasurable` and supports `CELSIUS` and `FAHRENHEIT`.
- **Compile-Time Category Safety**: The `Quantity<U>` class is strictly parameterized. Attempts to mix categories (e.g. `LengthUnit` + `VolumeUnit`) will fail to compile. This replaces error-prone runtime checks.
- **Centralized & Validated Arithmetic Operations (DRY)**: 
  - `add(...)`, `subtract(...)`, `divide(...)`: Generic arithmetic methods wrapping `performOperation`.
  - Under the hood, these methods delegate to a private helper `performOperation`, which validates whether the `IMeasurable` category supports arithmetic (via `validateOperationSupport`).
  - Attempts to perform arithmetic on Temperature objects safely fail with an `UnsupportedOperationException`.
- **Robust Validation**: Rejects invalid states like `null` units, `NaN` values, and `Infinite` values via `IllegalArgumentException`. Division by zero explicitly throws an exception.
- **Comprehensive Testing**: JUnit 5 tests utilizing generic parameters to verify equality, conversions, arithmetic, unsupported constraints, and isolated category tests for Length, Weight, Volume, and Temperature.

## Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven 3.6 or higher

## Getting Started

### Running Tests
To run the JUnit 5 tests and verify the implementation:
```bash
mvn clean test
```

### Running the Application
To run the main demonstration, demonstrating Length, Weight, Volume, and Temperature cleanly using one generic orchestration method:
```bash
mvn exec:java -Dexec.mainClass="quantitymeasurement.QuantityMeasurementApp"
```

## Sample Output
```
Welcome to Quantity Measurement App!

--- Generic Quantity Equality ---
Quantity(1.0, "feet") equals Quantity(12.0, "inches"): true
Quantity(1.0, "kilogram") equals Quantity(1000.0, "gram"): true

--- Generic Quantity Addition ---
Quantity(2.0, "inches") + Quantity(2.54, "cm") to inches = Quantity(3.0, "inches")
Quantity(1.0, "pound") + Quantity(1.0, "pound") to kilogram = Quantity(0.907, "kilogram")

--- Volume Demonstration (UC11) ---
Quantity(1.0, "litre") equals Quantity(1000.0, "millilitre"): true
Quantity(1.0, "gallon") + Quantity(3.785, "litre") to gallon = Quantity(2.0, "gallon")

--- Subtraction & Division (UC12) ---
Quantity(10.0, "feet") - Quantity(6.0, "inches") to feet = Quantity(9.5, "feet")
Quantity(10.0, "kilogram") / Quantity(5.0, "kilogram") = 2.0

--- Temperature Demonstration (UC14) ---
Quantity(0.0, "celsius") equals Quantity(32.0, "fahrenheit"): true
Attempting unsupported operation (Addition on Temperature)...
Caught Expected Exception: Temperature measurement does not support add
```

## Code Explanation

### `IMeasurable.java` & `SupportsArithmetic.java`
An interface establishing unit contract rules (`getConversionFactor()`, `convertToBaseUnit()`, `convertFromBaseUnit()`, `getUnitName()`, `validateOperationSupport()`). The `SupportsArithmetic` interface is a functional interface utilized by Enums to declare arithmetic validity.

### Measurement Unit Enums
Standalone Enums managing measurement units securely. `TemperatureUnit` implements bidirectional formula conversions (`c -> (c * 9/5) + 32` etc.) rather than standard unit scale multipliers.

### `Quantity<U extends IMeasurable>`
A standalone Class representing the parameterized mathematical model:
- `Quantity` constructor: Validates inputs.
- `performOperation`: Private helper method that standardizes validations, checks operation support, unifies base conversions, and evaluates logic based on a private `ArithmeticOperation` enum.
- `equals(Object obj)` method: Handles runtime type checks. Generic bounds block statically invalid comparisons but it maintains logic for raw objects.

### `QuantityMeasurementTest.java`
JUnit 5 tests verifying:
- **Refactored Generic APIs**: Validates strict compilation and safe test instantiation.
- **Temperature Tests (UC14)**: Equality, bidirectional conversion, negative boundaries, and operational constraint exceptions.
- **Arithmetic Tests (UC12/13)**: Validates subtraction and division logic across various units and boundary cases like division by zero.
- **Volume Tests (UC11)**: Validates equality, conversion, and addition across L, mL, and Gal.
- **Weight Tests (UC9)**: Validates equality, conversion, and addition across Kg, G, and Lbs.
- **Cross Category Boundary Tests (UC10 & UC11)**: Negative equality comparisons (Length != Weight, Volume != Weight).
- **Validation Tests**: Ensures constructors and methods reject invalid states (`null`, `NaN`, `Infinity`).
