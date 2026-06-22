# Quantity Measurement Application (UC10) - Generic Quantity Class with IMeasurable Interface

This project implements the tenth use case of the Quantity Measurement Application. It focuses on resolving the disadvantages of redundant category patterns (UC9) by leveraging **Java Generics** (`Quantity<U extends IMeasurable>`). This ensures type safety at compile time and makes the design infinitely scalable for future categories (like Volume or Temperature).

## Features
- **Generic Unit Interface**: `IMeasurable` is an interface standardizing conversion behaviors across categories.
- **Standalone Enums**: 
  - `LengthUnit` implements `IMeasurable` and supports `FEET`, `INCHES`, `YARD`, and `CM`.
  - `WeightUnit` implements `IMeasurable` and supports `KILOGRAM`, `GRAM`, and `POUND`.
- **Compile-Time Category Safety**: The `Quantity<U>` class is strictly parameterized. Attempts to add a `LengthUnit` to a `WeightUnit` will fail to compile. This replaces error-prone runtime checks.
- **Arithmetic Operations (Addition)**: 
  - `add(Quantity<U> other)`: Adds two generic units and returns the result in the unit of the first operand.
  - `add(Quantity<U> other, U targetUnit)`: Adds two units and returns the result in the explicitly specified target unit.
- **Robust Validation**: Rejects invalid states like `null` units, `NaN` values, and `Infinite` values via `IllegalArgumentException`.
- **Comprehensive Testing**: JUnit 5 tests utilizing generic parameters to verify equality, conversions, explicitly targeted additions, and isolated category tests.

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
To run the main demonstration, demonstrating both Length and Weight cleanly using one generic orchestration method:
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
Quantity(1.0, "pound") + Quantity(1.0, "pound") to kilogram = Quantity(0.908, "kilogram")
```

## Code Explanation

### `IMeasurable.java`
An interface establishing unit contract rules (`getConversionFactor()`, `convertToBaseUnit()`, `convertFromBaseUnit()`, `getUnitName()`).

### `LengthUnit.java` & `WeightUnit.java`
Standalone Enums managing length and weight units securely via internal factors, seamlessly implementing the `IMeasurable` interface.

### `Quantity<U extends IMeasurable>`
A standalone Class representing the parameterized mathematical model:
- `Quantity` constructor: Validates inputs.
- `convertTo(U targetUnit)`: Delegates base unit arithmetic. 
- `add(...)`: Overloaded methods to handle generic summation with strictly identical parameters.
- `equals(Object obj)` method: Handles runtime type checks. Generic bounds block statically invalid comparisons but it maintains logic for raw objects.

### `QuantityMeasurementTest.java`
JUnit 5 tests verifying:
- **Refactored Generic APIs**: Validates strict compilation and safe test instantiation.
- **Weight Tests (UC9)**: Validates equality, conversion, and addition across Kg, G, and Lbs.
- **Cross Category Boundary Tests (UC10)**: Negative equality comparisons (Length != Weight).
- **Validation Tests**: Ensures constructors and methods reject invalid states (`null`, `NaN`, `Infinity`).
