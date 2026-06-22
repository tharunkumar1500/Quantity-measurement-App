package quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0);

        private final double baseUnitConversionFactor;

        LengthUnit(double baseUnitConversionFactor) {
            this.baseUnitConversionFactor = baseUnitConversionFactor;
        }

        public double toBaseUnit(double value) {
            return value * this.baseUnitConversionFactor;
        }
    }

    public static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Quantity quantity = (Quantity) obj;
            return Double.compare(
                quantity.unit.toBaseUnit(quantity.value),
                this.unit.toBaseUnit(this.value)
            ) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(unit.toBaseUnit(value));
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Quantity Measurement App!");
        
        System.out.println("--- Generic Quantity Equality ---");
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity f2 = new Quantity(1.0, LengthUnit.FEET);
        System.out.println(f1 + " equals " + f2 + ": " + f1.equals(f2));

        Quantity f3 = new Quantity(2.0, LengthUnit.FEET);
        System.out.println(f1 + " equals " + f3 + ": " + f1.equals(f3));

        Quantity i1 = new Quantity(12.0, LengthUnit.INCHES);
        System.out.println(f1 + " equals " + i1 + ": " + f1.equals(i1));
    }
}
