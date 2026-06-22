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
}
