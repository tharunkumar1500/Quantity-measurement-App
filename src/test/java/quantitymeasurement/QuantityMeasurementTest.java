package quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementTest {

    // --- Base Equality Tests ---

    @Test
    public void givenZeroFeetAndZeroFeet_ShouldReturnEqual() {
        Quantity<LengthUnit> f1 = new Quantity<>(0.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(0.0, LengthUnit.FEET);
        assertEquals(f1, f2);
    }

    @Test
    public void givenTwoDifferentFeetValues_ShouldReturnNotEqual() {
        Quantity<LengthUnit> f1 = new Quantity<>(0.0, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotEquals(f1, f2);
    }

    @Test
    public void givenNullQuantity_ShouldReturnNotEqual() {
        Quantity<LengthUnit> f1 = new Quantity<>(0.0, LengthUnit.FEET);
        assertNotEquals(f1, null);
    }

    @Test
    public void givenReferenceToSameObject_ShouldReturnEqual() {
        Quantity<LengthUnit> f1 = new Quantity<>(0.0, LengthUnit.FEET);
        assertEquals(f1, f1);
    }

    @Test
    public void givenDifferentClassType_ShouldReturnNotEqual() {
        Quantity<LengthUnit> f1 = new Quantity<>(0.0, LengthUnit.FEET);
        Object obj = new Object();
        assertNotEquals(f1, obj);
    }

    // --- Same Unit Checks (Inches) ---

    @Test
    public void givenZeroInchesAndZeroInches_ShouldReturnEqual() {
        Quantity<LengthUnit> i1 = new Quantity<>(0.0, LengthUnit.INCHES);
        Quantity<LengthUnit> i2 = new Quantity<>(0.0, LengthUnit.INCHES);
        assertEquals(i1, i2);
    }

    @Test
    public void givenTwoDifferentInchesValues_ShouldReturnNotEqual() {
        Quantity<LengthUnit> i1 = new Quantity<>(0.0, LengthUnit.INCHES);
        Quantity<LengthUnit> i2 = new Quantity<>(1.0, LengthUnit.INCHES);
        assertNotEquals(i1, i2);
    }

    // --- Cross Unit Checks (Feet and Inches) ---

    @Test
    public void givenZeroFeetAndZeroInches_ShouldReturnEqual() {
        Quantity<LengthUnit> f1 = new Quantity<>(0.0, LengthUnit.FEET);
        Quantity<LengthUnit> i1 = new Quantity<>(0.0, LengthUnit.INCHES);
        assertEquals(f1, i1);
    }

    @Test
    public void givenOneFootAndTwelveInches_ShouldReturnEqual() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> i1 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(f1, i1);
        assertEquals(i1, f1);
    }

    @Test
    public void givenOneFootAndOneInch_ShouldReturnNotEqual() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> i1 = new Quantity<>(1.0, LengthUnit.INCHES);
        assertNotEquals(f1, i1);
    }

    // --- Yard Tests ---

    @Test
    public void givenThreeFeetAndOneYard_ShouldReturnEqual() {
        Quantity<LengthUnit> f1 = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> y1 = new Quantity<>(1.0, LengthUnit.YARD);
        assertEquals(f1, y1);
        assertEquals(y1, f1);
    }

    @Test
    public void givenOneFootAndOneYard_ShouldReturnNotEqual() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> y1 = new Quantity<>(1.0, LengthUnit.YARD);
        assertNotEquals(f1, y1);
    }

    @Test
    public void givenOneInchAndOneYard_ShouldReturnNotEqual() {
        Quantity<LengthUnit> i1 = new Quantity<>(1.0, LengthUnit.INCHES);
        Quantity<LengthUnit> y1 = new Quantity<>(1.0, LengthUnit.YARD);
        assertNotEquals(i1, y1);
    }

    @Test
    public void givenOneYardAndThirtySixInches_ShouldReturnEqual() {
        Quantity<LengthUnit> y1 = new Quantity<>(1.0, LengthUnit.YARD);
        Quantity<LengthUnit> i1 = new Quantity<>(36.0, LengthUnit.INCHES);
        assertEquals(y1, i1);
        assertEquals(i1, y1);
    }

    @Test
    public void givenThirtySixInchesAndOneYard_ShouldReturnEqual() {
        Quantity<LengthUnit> i1 = new Quantity<>(36.0, LengthUnit.INCHES);
        Quantity<LengthUnit> y1 = new Quantity<>(1.0, LengthUnit.YARD);
        assertEquals(i1, y1);
    }

    @Test
    public void givenOneYardAndThreeFeet_ShouldReturnEqual() {
        Quantity<LengthUnit> y1 = new Quantity<>(1.0, LengthUnit.YARD);
        Quantity<LengthUnit> f1 = new Quantity<>(3.0, LengthUnit.FEET);
        assertEquals(y1, f1);
    }

    // --- CM Tests ---

    @Test
    public void givenTwoInchesAndFivePointZeroEightCm_ShouldReturnEqual() {
        Quantity<LengthUnit> i1 = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> cm1 = new Quantity<>(5.08, LengthUnit.CM);
        assertEquals(i1, cm1);
        assertEquals(cm1, i1);
    }

    // --- Validation Tests ---
    @Test
    public void givenNullUnit_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    public void givenNaNValue_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    public void givenInfiniteValue_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    @Test
    public void givenNullTargetUnitInConversion_ShouldThrowException() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.convertTo(null));
    }

    // --- Conversion Tests ---

    @Test
    public void givenOneFoot_ShouldConvertToTwelveInches() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(12.0, f1.convertTo(LengthUnit.INCHES));
    }

    @Test
    public void givenThirtySixInches_ShouldConvertToOneYard() {
        Quantity<LengthUnit> i1 = new Quantity<>(36.0, LengthUnit.INCHES);
        assertEquals(1.0, i1.convertTo(LengthUnit.YARD));
    }

    @Test
    public void givenOneInch_ShouldConvertToTwoPointFiveFourCm() {
        Quantity<LengthUnit> i1 = new Quantity<>(1.0, LengthUnit.INCHES);
        assertEquals(2.54, i1.convertTo(LengthUnit.CM));
    }

    @Test
    public void givenTwoYards_ShouldConvertToSixFeet() {
        Quantity<LengthUnit> y1 = new Quantity<>(2.0, LengthUnit.YARD);
        assertEquals(6.0, y1.convertTo(LengthUnit.FEET));
    }

    @Test
    public void givenOneYard_ShouldConvertToNinetyOnePointFourFourCm() {
        Quantity<LengthUnit> y1 = new Quantity<>(1.0, LengthUnit.YARD);
        assertEquals(91.44, y1.convertTo(LengthUnit.CM));
    }

    // --- Addition Tests (UC6) ---

    @Test
    public void givenTwoInchesAndTwoInches_ShouldReturnFourInches() {
        Quantity<LengthUnit> i1 = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> i2 = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<?> sum = i1.add(i2);
        assertEquals(new Quantity<>(4.0, LengthUnit.INCHES), sum);
    }

    @Test
    public void givenOneFootAndTwoInches_ShouldReturnFourteenInches_WhenAddedToInches() {
        Quantity<LengthUnit> i1 = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<?> sum = i1.add(f1);
        assertEquals(new Quantity<>(14.0, LengthUnit.INCHES), sum);
    }

    @Test
    public void givenOneFootAndTwelveInches_ShouldReturnTwoFeet() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> i1 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<?> sum = f1.add(i1);
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), sum);
    }

    @Test
    public void givenTwoInchesAndTwoPointFiveFourCm_ShouldReturnThreeInches() {
        Quantity<LengthUnit> i1 = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> cm1 = new Quantity<>(2.54, LengthUnit.CM);
        Quantity<?> sum = i1.add(cm1);
        assertEquals(new Quantity<>(3.0, LengthUnit.INCHES), sum);
    }

    @Test
    public void givenNullQuantityToAdd_ShouldThrowException() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> f1.add(null));
    }

    // --- Target Unit Addition Tests (UC7) ---

    @Test
    public void givenOneFootAndTwelveInches_ShouldReturnTwoFeet_WhenTargetIsFeet() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> i1 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<?> sum = f1.add(i1, LengthUnit.FEET);
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), sum);
    }

    @Test
    public void givenOneFootAndTwelveInches_ShouldReturnTwentyFourInches_WhenTargetIsInches() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> i1 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<?> sum = f1.add(i1, LengthUnit.INCHES);
        assertEquals(new Quantity<>(24.0, LengthUnit.INCHES), sum);
    }

    @Test
    public void givenThirtySixInchesAndOneYard_ShouldReturnSixFeet_WhenTargetIsFeet() {
        Quantity<LengthUnit> i1 = new Quantity<>(36.0, LengthUnit.INCHES);
        Quantity<LengthUnit> y1 = new Quantity<>(1.0, LengthUnit.YARD);
        Quantity<?> sum = i1.add(y1, LengthUnit.FEET);
        assertEquals(new Quantity<>(6.0, LengthUnit.FEET), sum);
    }

    @Test
    public void givenTwoPointFiveFourCmAndOneInch_ShouldReturnFivePointZeroEightCm_WhenTargetIsCm() {
        Quantity<LengthUnit> cm1 = new Quantity<>(2.54, LengthUnit.CM);
        Quantity<LengthUnit> i1 = new Quantity<>(1.0, LengthUnit.INCHES);
        Quantity<?> sum = cm1.add(i1, LengthUnit.CM);
        assertEquals(new Quantity<>(5.08, LengthUnit.CM), sum);
    }

    @Test
    public void givenValidQuantities_ShouldThrowException_WhenTargetUnitIsNull() {
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> i1 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertThrows(IllegalArgumentException.class, () -> f1.add(i1, null));
    }

    // --- Weight Tests (UC9) ---

    @Test
    public void givenOneKgAndOneKg_ShouldReturnEqual() {
        Quantity<WeightUnit> kg1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> kg2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertEquals(kg1, kg2);
    }

    @Test
    public void givenOneKgAndThousandGrams_ShouldReturnEqual() {
        Quantity<WeightUnit> kg1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g1 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(kg1, g1);
    }

    @Test
    public void givenOnePound_ShouldReturnApproxKg() {
        Quantity<WeightUnit> lb1 = new Quantity<>(1.0, WeightUnit.POUND);
        assertEquals(0.454, lb1.convertTo(WeightUnit.KILOGRAM)); // 0.453592 rounded to 3 places
    }

    @Test
    public void givenThousandGramsAndOneKg_ShouldReturnTwoKg_WhenTargetIsKg() {
        Quantity<WeightUnit> g1 = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> kg1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<?> sum = g1.add(kg1, WeightUnit.KILOGRAM);
        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), sum);
    }

    @Test
    public void givenOnePoundAndOnePound_ShouldReturnExpectedKg() {
        Quantity<WeightUnit> lb1 = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> lb2 = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<?> sum = lb1.add(lb2, WeightUnit.KILOGRAM);
        assertEquals(new Quantity<>(0.907, WeightUnit.KILOGRAM), sum);
    }

    // --- Cross Category Boundary Tests ---

    @Test
    public void givenOneKgAndOneFoot_ShouldNotBeEqual() {
        Quantity<WeightUnit> kg1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<LengthUnit> f1 = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotEquals(kg1, f1);
    }

    // --- Volume Tests (UC11) ---

    @Test
    public void givenOneLitreAndOneLitre_ShouldReturnEqual() {
        Quantity<VolumeUnit> l1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> l2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(l1, l2);
    }

    @Test
    public void givenOneLitreAndThousandMillilitres_ShouldReturnEqual() {
        Quantity<VolumeUnit> l1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(l1, ml1);
    }

    @Test
    public void givenOneGallon_ShouldReturnApproxLitres() {
        Quantity<VolumeUnit> gal1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertEquals(3.785, gal1.convertTo(VolumeUnit.LITRE)); // 3.78541 rounded to 3 places is 3.785
    }

    @Test
    public void givenOneLitreAndThousandMillilitres_ShouldReturnTwoLitres_WhenTargetIsLitre() {
        Quantity<VolumeUnit> l1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = l1.add(ml1, VolumeUnit.LITRE);
        assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), sum);
    }

    @Test
    public void givenOneGallonAndThreePointSevenEightLitres_ShouldReturnApproxTwoGallons() {
        Quantity<VolumeUnit> gal1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> l1 = new Quantity<>(3.785, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sum = gal1.add(l1, VolumeUnit.GALLON);
        assertEquals(new Quantity<>(2.0, VolumeUnit.GALLON), sum);
    }

    @Test
    public void givenOneLitreAndOneKg_ShouldNotBeEqual() {
        Quantity<VolumeUnit> l1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> kg1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(l1, kg1);
    }

    // --- Arithmetic Tests (Subtraction and Division) (UC12) ---

    @Test
    public void givenFiveLitresAndTwoLitres_ShouldReturnThreeLitres_OnSubtraction() {
        Quantity<VolumeUnit> l1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> l2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> diff = l1.subtract(l2);
        assertEquals(new Quantity<>(3.0, VolumeUnit.LITRE), diff);
    }

    @Test
    public void givenTenFeetAndSixInches_ShouldReturnNinePointFiveFeet_OnSubtraction() {
        Quantity<LengthUnit> f1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> i1 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> diff = f1.subtract(i1);
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), diff);
    }

    @Test
    public void givenOneKilogramAndFiveHundredGrams_ShouldReturnZeroPointFiveKilograms_OnSubtraction() {
        Quantity<WeightUnit> kg1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g1 = new Quantity<>(500.0, WeightUnit.GRAM);
        Quantity<WeightUnit> diff = kg1.subtract(g1);
        assertEquals(new Quantity<>(0.5, WeightUnit.KILOGRAM), diff);
    }

    @Test
    public void givenTenKilogramsAndFiveKilograms_ShouldReturnTwo_OnDivision() {
        Quantity<WeightUnit> kg1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> kg2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        double result = kg1.divide(kg2);
        assertEquals(2.0, result, 0.001);
    }

    @Test
    public void givenOneGallonAndThreePointSevenEightLitres_ShouldReturnApproxOne_OnDivision() {
        Quantity<VolumeUnit> gal1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> l1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        double result = gal1.divide(l1);
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void givenQuantityDividedByZero_ShouldThrowException() {
        Quantity<LengthUnit> f1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> zeroLength = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> f1.divide(zeroLength));
    }
}
