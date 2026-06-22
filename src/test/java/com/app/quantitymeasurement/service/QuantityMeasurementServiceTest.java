package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = QuantityMeasurementServiceImpl.class)
public class QuantityMeasurementServiceTest {

    @MockBean
    private IQuantityMeasurementRepository repository;

    @Autowired
    private QuantityMeasurementServiceImpl service;

    @BeforeEach
    public void setUp() {
        // Mock behavior: just return the saved entity as-is
        lenient().when(repository.save(any(QuantityMeasurementEntity.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void testCompare_SameCategory_ReturnsTrue() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);

        QuantityMeasurementEntity result = service.compare(q1, q2);

        assertFalse(result.hasError());
        assertEquals("true", result.getResult());
        verify(repository, times(1)).save(any(QuantityMeasurementEntity.class));
    }

    @Test
    public void testCompare_DifferentCategory_ReturnsError() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAM);

        QuantityMeasurementEntity result = service.compare(q1, q2);

        assertTrue(result.hasError());
        assertTrue(result.getErrorMessage().contains("Cannot compare quantities"));
        verify(repository, times(1)).save(any(QuantityMeasurementEntity.class));
    }
}
