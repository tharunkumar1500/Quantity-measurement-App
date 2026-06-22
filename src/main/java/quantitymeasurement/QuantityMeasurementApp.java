package quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {

    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet feet = (Feet) obj;
            return Double.compare(feet.value, value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inches inches = (Inches) obj;
            return Double.compare(inches.value, value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Quantity Measurement App!");
        
        System.out.println("--- Feet Equality ---");
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        System.out.println("1.0 ft equals 1.0 ft: " + feet1.equals(feet2));
        Feet feet3 = new Feet(2.0);
        System.out.println("1.0 ft equals 2.0 ft: " + feet1.equals(feet3));

        System.out.println("--- Inches Equality ---");
        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(1.0);
        System.out.println("1.0 inch equals 1.0 inch: " + inches1.equals(inches2));
        Inches inches3 = new Inches(2.0);
        System.out.println("1.0 inch equals 2.0 inch: " + inches1.equals(inches3));
    }
}
