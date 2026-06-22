package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityMeasurementController {
    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementController.class);
    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null");
        }
        this.service = service;
    }

    public void compare(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity entity = service.compare(q1, q2);
        logger.info(entity.toString());
    }

    public void convert(QuantityDTO q1, QuantityDTO.IMeasurableUnit targetUnit) {
        QuantityMeasurementEntity entity = service.convert(q1, targetUnit);
        logger.info(entity.toString());
    }

    public void demonstrateAddition(QuantityDTO q1, QuantityDTO q2, QuantityDTO.IMeasurableUnit targetUnit) {
        QuantityMeasurementEntity entity = service.add(q1, q2, targetUnit);
        logger.info(entity.toString());
    }

    public void demonstrateSubtraction(QuantityDTO q1, QuantityDTO q2, QuantityDTO.IMeasurableUnit targetUnit) {
        QuantityMeasurementEntity entity = service.subtract(q1, q2, targetUnit);
        logger.info(entity.toString());
    }

    public void demonstrateDivision(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity entity = service.divide(q1, q2);
        logger.info(entity.toString());
    }

    public void demonstrateEquality(QuantityDTO q1, QuantityDTO q2) {
        compare(q1, q2);
    }
}
