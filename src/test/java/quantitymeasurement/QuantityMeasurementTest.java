package quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementTest {

    @Test
    public void givenZeroFeetAndZeroFeet_ShouldReturnEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(0.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(0.0);
        assertEquals(feet1, feet2);
    }

    @Test
    public void givenTwoDifferentFeetValues_ShouldReturnNotEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(0.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.0);
        assertNotEquals(feet1, feet2);
    }

    @Test
    public void givenNullFeet_ShouldReturnNotEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(0.0);
        assertNotEquals(feet1, null);
    }

    @Test
    public void givenReferenceToSameObject_ShouldReturnEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(0.0);
        assertEquals(feet1, feet1);
    }

    @Test
    public void givenDifferentClassType_ShouldReturnNotEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(0.0);
        Object obj = new Object();
        assertNotEquals(feet1, obj);
    }

    // --- Inches Tests ---

    @Test
    public void givenZeroInchesAndZeroInches_ShouldReturnEqual() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(0.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(0.0);
        assertEquals(inches1, inches2);
    }

    @Test
    public void givenTwoDifferentInchesValues_ShouldReturnNotEqual() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(0.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(1.0);
        assertNotEquals(inches1, inches2);
    }

    @Test
    public void givenNullInches_ShouldReturnNotEqual() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(0.0);
        assertNotEquals(inches1, null);
    }

    @Test
    public void givenReferenceToSameInchesObject_ShouldReturnEqual() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(0.0);
        assertEquals(inches1, inches1);
    }

    @Test
    public void givenDifferentClassTypeForInches_ShouldReturnNotEqual() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(0.0);
        Object obj = new Object();
        assertNotEquals(inches1, obj);
    }
}
