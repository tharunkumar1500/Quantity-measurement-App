package quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import quantitymeasurement.QuantityMeasurementApp.Quantity;
import quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementTest {

    // --- Base Equality Tests ---

    @Test
    public void givenZeroFeetAndZeroFeet_ShouldReturnEqual() {
        Quantity f1 = new Quantity(0.0, LengthUnit.FEET);
        Quantity f2 = new Quantity(0.0, LengthUnit.FEET);
        assertEquals(f1, f2);
    }

    @Test
    public void givenTwoDifferentFeetValues_ShouldReturnNotEqual() {
        Quantity f1 = new Quantity(0.0, LengthUnit.FEET);
        Quantity f2 = new Quantity(1.0, LengthUnit.FEET);
        assertNotEquals(f1, f2);
    }

    @Test
    public void givenNullQuantity_ShouldReturnNotEqual() {
        Quantity f1 = new Quantity(0.0, LengthUnit.FEET);
        assertNotEquals(f1, null);
    }

    @Test
    public void givenReferenceToSameObject_ShouldReturnEqual() {
        Quantity f1 = new Quantity(0.0, LengthUnit.FEET);
        assertEquals(f1, f1);
    }

    @Test
    public void givenDifferentClassType_ShouldReturnNotEqual() {
        Quantity f1 = new Quantity(0.0, LengthUnit.FEET);
        Object obj = new Object();
        assertNotEquals(f1, obj);
    }

    // --- Same Unit Checks (Inches) ---

    @Test
    public void givenZeroInchesAndZeroInches_ShouldReturnEqual() {
        Quantity i1 = new Quantity(0.0, LengthUnit.INCHES);
        Quantity i2 = new Quantity(0.0, LengthUnit.INCHES);
        assertEquals(i1, i2);
    }

    @Test
    public void givenTwoDifferentInchesValues_ShouldReturnNotEqual() {
        Quantity i1 = new Quantity(0.0, LengthUnit.INCHES);
        Quantity i2 = new Quantity(1.0, LengthUnit.INCHES);
        assertNotEquals(i1, i2);
    }

    // --- Cross Unit Checks (Feet and Inches) ---

    @Test
    public void givenZeroFeetAndZeroInches_ShouldReturnEqual() {
        Quantity f1 = new Quantity(0.0, LengthUnit.FEET);
        Quantity i1 = new Quantity(0.0, LengthUnit.INCHES);
        assertEquals(f1, i1);
    }

    @Test
    public void givenOneFootAndTwelveInches_ShouldReturnEqual() {
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity i1 = new Quantity(12.0, LengthUnit.INCHES);
        assertEquals(f1, i1);
        assertEquals(i1, f1);
    }

    @Test
    public void givenOneFootAndOneInch_ShouldReturnNotEqual() {
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity i1 = new Quantity(1.0, LengthUnit.INCHES);
        assertNotEquals(f1, i1);
    }

    // --- Yard Tests ---

    @Test
    public void givenThreeFeetAndOneYard_ShouldReturnEqual() {
        Quantity f1 = new Quantity(3.0, LengthUnit.FEET);
        Quantity y1 = new Quantity(1.0, LengthUnit.YARD);
        assertEquals(f1, y1);
        assertEquals(y1, f1);
    }

    @Test
    public void givenOneFootAndOneYard_ShouldReturnNotEqual() {
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity y1 = new Quantity(1.0, LengthUnit.YARD);
        assertNotEquals(f1, y1);
    }

    @Test
    public void givenOneInchAndOneYard_ShouldReturnNotEqual() {
        Quantity i1 = new Quantity(1.0, LengthUnit.INCHES);
        Quantity y1 = new Quantity(1.0, LengthUnit.YARD);
        assertNotEquals(i1, y1);
    }

    @Test
    public void givenOneYardAndThirtySixInches_ShouldReturnEqual() {
        Quantity y1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity i1 = new Quantity(36.0, LengthUnit.INCHES);
        assertEquals(y1, i1);
        assertEquals(i1, y1);
    }

    @Test
    public void givenThirtySixInchesAndOneYard_ShouldReturnEqual() {
        Quantity i1 = new Quantity(36.0, LengthUnit.INCHES);
        Quantity y1 = new Quantity(1.0, LengthUnit.YARD);
        assertEquals(i1, y1);
    }

    @Test
    public void givenOneYardAndThreeFeet_ShouldReturnEqual() {
        Quantity y1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity f1 = new Quantity(3.0, LengthUnit.FEET);
        assertEquals(y1, f1);
    }

    // --- CM Tests ---

    @Test
    public void givenTwoInchesAndFivePointZeroEightCm_ShouldReturnEqual() {
        Quantity i1 = new Quantity(2.0, LengthUnit.INCHES);
        Quantity cm1 = new Quantity(5.08, LengthUnit.CM);
        assertEquals(i1, cm1);
        assertEquals(cm1, i1);
    }

    // --- Validation Tests ---
    @Test
    public void givenNullUnit_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(1.0, null));
    }

    @Test
    public void givenNaNValue_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(Double.NaN, LengthUnit.FEET));
    }

    @Test
    public void givenInfiniteValue_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    @Test
    public void givenNullTargetUnitInConversion_ShouldThrowException() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.convertTo(null));
    }

    // --- Conversion Tests ---

    @Test
    public void givenOneFoot_ShouldConvertToTwelveInches() {
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        assertEquals(12.0, f1.convertTo(LengthUnit.INCHES));
    }

    @Test
    public void givenThirtySixInches_ShouldConvertToOneYard() {
        Quantity i1 = new Quantity(36.0, LengthUnit.INCHES);
        assertEquals(1.0, i1.convertTo(LengthUnit.YARD));
    }

    @Test
    public void givenOneInch_ShouldConvertToTwoPointFiveFourCm() {
        Quantity i1 = new Quantity(1.0, LengthUnit.INCHES);
        assertEquals(2.54, i1.convertTo(LengthUnit.CM));
    }

    @Test
    public void givenTwoYards_ShouldConvertToSixFeet() {
        Quantity y1 = new Quantity(2.0, LengthUnit.YARD);
        assertEquals(6.0, y1.convertTo(LengthUnit.FEET));
    }

    @Test
    public void givenOneYard_ShouldConvertToNinetyOnePointFourFourCm() {
        Quantity y1 = new Quantity(1.0, LengthUnit.YARD);
        assertEquals(91.44, y1.convertTo(LengthUnit.CM));
    }

    // --- Addition Tests (UC6) ---

    @Test
    public void givenTwoInchesAndTwoInches_ShouldReturnFourInches() {
        Quantity i1 = new Quantity(2.0, LengthUnit.INCHES);
        Quantity i2 = new Quantity(2.0, LengthUnit.INCHES);
        Quantity sum = i1.add(i2);
        assertEquals(new Quantity(4.0, LengthUnit.INCHES), sum);
    }

    @Test
    public void givenOneFootAndTwoInches_ShouldReturnFourteenInches_WhenAddedToInches() {
        Quantity i1 = new Quantity(2.0, LengthUnit.INCHES);
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity sum = i1.add(f1);
        assertEquals(new Quantity(14.0, LengthUnit.INCHES), sum);
    }

    @Test
    public void givenOneFootAndTwelveInches_ShouldReturnTwoFeet() {
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity i1 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity sum = f1.add(i1);
        assertEquals(new Quantity(2.0, LengthUnit.FEET), sum);
    }

    @Test
    public void givenTwoInchesAndTwoPointFiveFourCm_ShouldReturnThreeInches() {
        Quantity i1 = new Quantity(2.0, LengthUnit.INCHES);
        Quantity cm1 = new Quantity(2.54, LengthUnit.CM);
        Quantity sum = i1.add(cm1);
        assertEquals(new Quantity(3.0, LengthUnit.INCHES), sum);
    }

    @Test
    public void givenNullQuantityToAdd_ShouldThrowException() {
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> f1.add(null));
    }

    // --- Target Unit Addition Tests (UC7) ---

    @Test
    public void givenOneFootAndTwelveInches_ShouldReturnTwoFeet_WhenTargetIsFeet() {
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity i1 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity sum = f1.add(i1, LengthUnit.FEET);
        assertEquals(new Quantity(2.0, LengthUnit.FEET), sum);
    }

    @Test
    public void givenOneFootAndTwelveInches_ShouldReturnTwentyFourInches_WhenTargetIsInches() {
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity i1 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity sum = f1.add(i1, LengthUnit.INCHES);
        assertEquals(new Quantity(24.0, LengthUnit.INCHES), sum);
    }

    @Test
    public void givenThirtySixInchesAndOneYard_ShouldReturnSixFeet_WhenTargetIsFeet() {
        Quantity i1 = new Quantity(36.0, LengthUnit.INCHES);
        Quantity y1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity sum = i1.add(y1, LengthUnit.FEET);
        assertEquals(new Quantity(6.0, LengthUnit.FEET), sum);
    }

    @Test
    public void givenTwoPointFiveFourCmAndOneInch_ShouldReturnFivePointZeroEightCm_WhenTargetIsCm() {
        Quantity cm1 = new Quantity(2.54, LengthUnit.CM);
        Quantity i1 = new Quantity(1.0, LengthUnit.INCHES);
        Quantity sum = cm1.add(i1, LengthUnit.CM);
        assertEquals(new Quantity(5.08, LengthUnit.CM), sum);
    }

    @Test
    public void givenValidQuantities_ShouldThrowException_WhenTargetUnitIsNull() {
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity i1 = new Quantity(12.0, LengthUnit.INCHES);
        assertThrows(IllegalArgumentException.class, () -> f1.add(i1, null));
    }
}
