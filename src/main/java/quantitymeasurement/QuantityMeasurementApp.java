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

    public static void main(String[] args) {
        System.out.println("Welcome to Quantity Measurement App!");
        
        // Example usage for UC1
        Feet feet1 = new Feet(0.0);
        Feet feet2 = new Feet(0.0);
        System.out.println("0 Feet equals 0 Feet: " + feet1.equals(feet2));
    }
}
