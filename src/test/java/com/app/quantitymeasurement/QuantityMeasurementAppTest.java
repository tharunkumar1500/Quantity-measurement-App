package com.app.quantitymeasurement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private IQuantityMeasurementRepository repository;
    private IQuantityMeasurementService service;
    private QuantityMeasurementController controller;

    @BeforeEach
    public void setup() {
        repository = new QuantityMeasurementCacheRepository();
        service = new QuantityMeasurementServiceImpl(repository);
        controller = new QuantityMeasurementController(service);
    }

    @Test
    public void testQuantityEntity_ToString_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, "CONVERT to INCHES", "12.0");
        assertFalse(entity.hasError());
        assertTrue(entity.toString().contains("CONVERT"));
    }

    @Test
    public void testQuantityEntity_ToString_Error() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD", "Unsupported operation");
        assertTrue(entity.hasError());
        assertTrue(entity.toString().contains("Error: Unsupported operation"));
    }

    @Test
    public void testService_CompareEquality_SameUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityMeasurementEntity result = service.compare(q1, q2);
        assertFalse(result.hasError());
        assertEquals("true", result.getResult());
        assertEquals(1, repository.getAllMeasurements().size());
    }

    @Test
    public void testService_CompareEquality_CrossCategory_Error() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAM);
        QuantityMeasurementEntity result = service.compare(q1, q2);
        assertTrue(result.hasError());
        assertEquals("COMPARE", result.getOperationType());
    }

    @Test
    public void testService_Add_Success() {
        QuantityDTO q1 = new QuantityDTO(2.0, QuantityDTO.LengthUnit.INCHES);
        QuantityDTO q2 = new QuantityDTO(2.54, QuantityDTO.LengthUnit.CM);
        QuantityMeasurementEntity result = service.add(q1, q2, QuantityDTO.LengthUnit.INCHES);
        assertFalse(result.hasError());
        assertTrue(result.getResult().contains("3.0"));
    }

    @Test
    public void testService_Add_UnsupportedOperation_Error() {
        QuantityDTO c1 = new QuantityDTO(100.0, QuantityDTO.TemperatureUnit.CELSIUS);
        QuantityDTO c2 = new QuantityDTO(50.0, QuantityDTO.TemperatureUnit.CELSIUS);
        QuantityMeasurementEntity result = service.add(c1, c2, QuantityDTO.TemperatureUnit.CELSIUS);
        assertTrue(result.hasError());
        assertTrue(result.getErrorMessage().contains("does not support add"));
    }

    @Test
    public void testController_DemonstrateEquality_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);
        // Should execute without throwing any exceptions
        assertDoesNotThrow(() -> controller.demonstrateEquality(q1, q2));
    }
}
