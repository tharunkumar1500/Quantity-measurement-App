package quantitymeasurement;

import java.util.Objects;

public class Quantity {
    private final double value;
    private final Unit unit;

    public Quantity(double value, Unit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double convertTo(Unit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (!this.unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Cannot convert between different measurement categories");
        }
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return Math.round(convertedValue * 1000.0) / 1000.0;
    }

    public Quantity add(Quantity other) {
        return this.add(other, this.unit);
    }

    public Quantity add(Quantity other, Unit targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity to add cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (!this.unit.getClass().equals(other.unit.getClass()) || !this.unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Cannot operate on different measurement categories");
        }
        double thisConvertedValue = this.convertTo(targetUnit);
        double otherConvertedValue = other.convertTo(targetUnit);
        double sumValue = thisConvertedValue + otherConvertedValue;
        return new Quantity(Math.round(sumValue * 1000.0) / 1000.0, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity quantity = (Quantity) obj;
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
        if (unit instanceof Enum) {
            return "Quantity(" + value + ", \"" + ((Enum<?>) unit).name().toLowerCase() + "\")";
        }
        return "Quantity(" + value + ", \"" + unit.toString() + "\")";
    }
}
