package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuantityMeasurementControllerTest {

    @Mock
    private IQuantityMeasurementService service;

    @InjectMocks
    private QuantityMeasurementController controller;

    @BeforeEach
    public void setUp() {
        // Setup mock response
        QuantityMeasurementEntity mockEntity = new QuantityMeasurementEntity(
                new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET),
                new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES),
                "COMPARE",
                "true"
        );
        lenient().when(service.compare(any(), any())).thenReturn(mockEntity);
        lenient().when(service.add(any(), any(), any())).thenReturn(mockEntity);
    }

    @Test
    public void testCompare_CallsService() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);

        controller.compare(q1, q2);

        verify(service, times(1)).compare(eq(q1), eq(q2));
    }

    @Test
    public void testDemonstrateAddition_CallsService() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);

        controller.demonstrateAddition(q1, q2, QuantityDTO.LengthUnit.INCHES);

        verify(service, times(1)).add(eq(q1), eq(q2), eq(QuantityDTO.LengthUnit.INCHES));
    }
}
