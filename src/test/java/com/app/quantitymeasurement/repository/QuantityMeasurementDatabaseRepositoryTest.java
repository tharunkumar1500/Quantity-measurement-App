package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.util.ConnectionPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementDatabaseRepositoryTest {
    private QuantityMeasurementDatabaseRepository repository;

    @BeforeEach
    public void setUp() {
        // ApplicationConfig defaults to H2 and initializes schema
        repository = new QuantityMeasurementDatabaseRepository();
        repository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
        ConnectionPool.getInstance().closeAll();
    }

    @Test
    public void testDatabaseRepository_SaveEntity() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                new QuantityDTO(10.0, QuantityDTO.LengthUnit.FEET),
                new QuantityDTO(120.0, QuantityDTO.LengthUnit.INCHES),
                "COMPARE",
                "true"
        );
        QuantityMeasurementEntity saved = repository.save(entity);
        assertNotNull(saved.getId());
        assertEquals(1, repository.getTotalCount());
    }

    @Test
    public void testDatabaseRepository_RetrieveAllMeasurements() {
        repository.save(new QuantityMeasurementEntity("COMPARE", "error"));
        repository.save(new QuantityMeasurementEntity("ADD", "error"));
        List<QuantityMeasurementEntity> list = repository.getAllMeasurements();
        assertEquals(2, list.size());
    }

    @Test
    public void testDatabaseRepository_QueryByOperation() {
        repository.save(new QuantityMeasurementEntity("COMPARE", "error"));
        repository.save(new QuantityMeasurementEntity("ADD", "error"));
        repository.save(new QuantityMeasurementEntity("COMPARE", "error2"));
        
        List<QuantityMeasurementEntity> compares = repository.getMeasurementsByOperation("COMPARE");
        assertEquals(2, compares.size());
    }

    @Test
    public void testDatabaseRepository_QueryByMeasurementType() {
        QuantityMeasurementEntity e1 = new QuantityMeasurementEntity(
                new QuantityDTO(10.0, QuantityDTO.LengthUnit.FEET),
                "COMPARE", "true");
        QuantityMeasurementEntity e2 = new QuantityMeasurementEntity(
                new QuantityDTO(10.0, QuantityDTO.WeightUnit.KILOGRAM),
                "COMPARE", "true");
        repository.save(e1);
        repository.save(e2);
        
        List<QuantityMeasurementEntity> lengths = repository.getMeasurementsByType("LENGTH");
        assertEquals(1, lengths.size());
        assertEquals("LENGTH", lengths.get(0).getMeasurementType());
    }

    @Test
    public void testDatabaseRepository_DeleteAll() {
        repository.save(new QuantityMeasurementEntity("ADD", "error"));
        assertEquals(1, repository.getTotalCount());
        repository.deleteAll();
        assertEquals(0, repository.getTotalCount());
    }
}
