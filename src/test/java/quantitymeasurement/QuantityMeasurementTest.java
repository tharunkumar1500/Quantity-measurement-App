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
}
