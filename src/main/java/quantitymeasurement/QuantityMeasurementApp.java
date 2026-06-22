package quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1 + " equals " + q2 + ": " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        Quantity<U> sum = q1.add(q2, targetUnit);
        System.out.println(q1 + " + " + q2 + " to " + targetUnit.getUnitName() + " = " + sum);
    }

    public static <U extends IMeasurable> void demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        Quantity<U> diff = q1.subtract(q2, targetUnit);
        System.out.println(q1 + " - " + q2 + " to " + targetUnit.getUnitName() + " = " + diff);
    }

    public static <U extends IMeasurable> void demonstrateDivision(Quantity<U> q1, Quantity<U> q2) {
        double result = q1.divide(q2);
        System.out.println(q1 + " / " + q2 + " = " + result);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Quantity Measurement App!");
        
        System.out.println("\n--- Generic Quantity Equality ---");
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> i1 = new Quantity<>(12.0, LengthUnit.INCHES);
        demonstrateEquality(f1, i1);

        Quantity<WeightUnit> kg1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g1 = new Quantity<>(1000.0, WeightUnit.GRAM);
        demonstrateEquality(kg1, g1);

        System.out.println("\n--- Generic Quantity Addition ---");
        Quantity<LengthUnit> cm1 = new Quantity<>(2.54, LengthUnit.CM);
        Quantity<LengthUnit> i2 = new Quantity<>(2.0, LengthUnit.INCHES);
        demonstrateAddition(i2, cm1, LengthUnit.INCHES);

        Quantity<WeightUnit> lb1 = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> lb2 = new Quantity<>(1.0, WeightUnit.POUND);
        demonstrateAddition(lb1, lb2, WeightUnit.KILOGRAM);

        System.out.println("\n--- Volume Demonstration (UC11) ---");
        Quantity<VolumeUnit> l1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        demonstrateEquality(l1, ml1);

        Quantity<VolumeUnit> gal1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> l2 = new Quantity<>(3.785, VolumeUnit.LITRE);
        demonstrateAddition(gal1, l2, VolumeUnit.GALLON);

        System.out.println("\n--- Subtraction & Division (UC12) ---");
        Quantity<LengthUnit> f2 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> i3 = new Quantity<>(6.0, LengthUnit.INCHES);
        demonstrateSubtraction(f2, i3, LengthUnit.FEET);

        Quantity<WeightUnit> kg2 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> kg3 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        demonstrateDivision(kg2, kg3);
    }
}
