package quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return Math.round(convertedValue * 1000.0) / 1000.0;
    }

    private enum ArithmeticOperation {
        ADD, SUBTRACT, DIVIDE
    }

    private double performOperation(Quantity<U> other, ArithmeticOperation operation) {
        if (operation == ArithmeticOperation.DIVIDE && other.value == 0.0) {
            throw new IllegalArgumentException("Cannot divide by zero quantity.");
        }
        double thisBaseValue = this.unit.convertToBaseUnit(this.value);
        double otherBaseValue = other.unit.convertToBaseUnit(other.value);

        switch (operation) {
            case ADD:
                return thisBaseValue + otherBaseValue;
            case SUBTRACT:
                return thisBaseValue - otherBaseValue;
            case DIVIDE:
                return thisBaseValue / otherBaseValue;
            default:
                throw new UnsupportedOperationException("Unknown operation");
        }
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity to add cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double resultBaseValue = performOperation(other, ArithmeticOperation.ADD);
        double resultInTargetUnit = targetUnit.convertFromBaseUnit(resultBaseValue);
        // Rounding to 3 decimal places to avoid floating point precision issues
        resultInTargetUnit = Math.round(resultInTargetUnit * 1000.0) / 1000.0;
        return new Quantity<>(resultInTargetUnit, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        double resultBaseValue = performOperation(other, ArithmeticOperation.SUBTRACT);
        double resultInTargetUnit = targetUnit.convertFromBaseUnit(resultBaseValue);
        // Rounding to 3 decimal places to avoid floating point precision issues
        resultInTargetUnit = Math.round(resultInTargetUnit * 1000.0) / 1000.0;
        return new Quantity<>(resultInTargetUnit, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public double divide(Quantity<U> other) {
        return performOperation(other, ArithmeticOperation.DIVIDE);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity<?> quantity = (Quantity<?>) obj;
        if (!this.unit.getClass().equals(quantity.unit.getClass())) {
            return false;
        }
        double value1 = Math.round(quantity.unit.convertToBaseUnit(quantity.value) * 1000.0) / 1000.0;
        double value2 = Math.round(this.unit.convertToBaseUnit(this.value) * 1000.0) / 1000.0;
        return Double.compare(value1, value2) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", \"" + unit.getUnitName() + "\")";
    }
}
