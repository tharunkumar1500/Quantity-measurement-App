package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {
    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementCacheRepository.class);
    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

    @Override
    public QuantityMeasurementEntity save(QuantityMeasurementEntity entity) {
        cache.add(entity);
        logger.info("Saved entity to cache: {}", entity.getOperation());
        return entity;
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return new ArrayList<>(cache);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        List<QuantityMeasurementEntity> result = new ArrayList<>();
        for (QuantityMeasurementEntity entity : cache) {
            if (operation.equalsIgnoreCase(entity.getOperation())) {
                result.add(entity);
            }
        }
        return result;
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {
        List<QuantityMeasurementEntity> result = new ArrayList<>();
        for (QuantityMeasurementEntity entity : cache) {
            if (measurementType.equalsIgnoreCase(entity.getMeasurementType())) {
                result.add(entity);
            }
        }
        return result;
    }

    @Override
    public int getTotalCount() {
        return cache.size();
    }

    @Override
    public void deleteAll() {
        cache.clear();
        logger.info("Cleared cache repository.");
    }
}
