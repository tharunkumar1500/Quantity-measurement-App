package com.app.quantitymeasurement.integrationTests;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class QuantityMeasurementIntegrationTest {

    @Autowired
    private QuantityMeasurementController controller;

    @Test
    public void testCompare_Integration() {
        QuantityMeasurementController.CompareRequest request = new QuantityMeasurementController.CompareRequest();
        request.setQ1(new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET));
        request.setQ2(new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES));

        ResponseEntity<QuantityMeasurementEntity> response = controller.compare(request);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().hasError());
        assertEquals("true", response.getBody().getResult());
    }
}
