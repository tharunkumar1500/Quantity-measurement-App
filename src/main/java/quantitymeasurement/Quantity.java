package quantitymeasurement;

import java.util.Objects;

public class Quantity {
    private final double value;
    private final LengthUnit unit;

    public Quantity(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return Math.round(convertedValue * 1000.0) / 1000.0;
    }

    public Quantity add(Quantity other) {
        return this.add(other, this.unit);
    }

    public Quantity add(Quantity other, LengthUnit targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity to add cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
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
        double value1 = Math.round(quantity.unit.convertToBaseUnit(quantity.value) * 1000.0) / 1000.0;
        double value2 = Math.round(this.unit.convertToBaseUnit(this.value) * 1000.0) / 1000.0;
        return Double.compare(value1, value2) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
    }
}
