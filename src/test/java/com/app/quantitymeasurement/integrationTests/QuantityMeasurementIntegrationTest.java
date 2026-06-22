package com.app.quantitymeasurement.integrationTests;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.app.quantitymeasurement.util.ApplicationConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuantityMeasurementIntegrationTest {
    private QuantityMeasurementController controller;
    private IQuantityMeasurementRepository repository;

    @BeforeEach
    public void setUp() {
        System.setProperty("app.repository.type", "DATABASE");
        repository = new QuantityMeasurementDatabaseRepository();
        repository.deleteAll();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        controller = new QuantityMeasurementController(service);
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
        repository.closeResources();
        System.clearProperty("app.repository.type");
    }

    @Test
    public void testIntegration_CompareLengths_StoresInDatabase() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);
        
        controller.compare(q1, q2);
        
        assertEquals(1, repository.getTotalCount());
        assertEquals("COMPARE", repository.getAllMeasurements().get(0).getOperation());
    }

    @Test
    public void testIntegration_AddVolumes_StoresInDatabase() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.VolumeUnit.GALLON);
        QuantityDTO q2 = new QuantityDTO(3.78, QuantityDTO.VolumeUnit.LITRE);
        
        controller.demonstrateAddition(q1, q2, QuantityDTO.VolumeUnit.GALLON);
        
        assertEquals(1, repository.getTotalCount());
        assertEquals("ADD", repository.getAllMeasurements().get(0).getOperation());
    }
}
