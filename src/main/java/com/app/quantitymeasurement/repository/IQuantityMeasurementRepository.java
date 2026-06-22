package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementRepository {
    QuantityMeasurementEntity save(QuantityMeasurementEntity entity);
    List<QuantityMeasurementEntity> getAllMeasurements();
    List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation);
    List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType);
    int getTotalCount();
    void deleteAll();
    
    default String getPoolStatistics() {
        return "N/A (Not supported by this repository implementation)";
    }
    
    default void closeResources() {
        // Default implementation does nothing
    }
}
