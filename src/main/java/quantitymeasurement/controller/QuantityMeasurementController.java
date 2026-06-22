package quantitymeasurement.controller;

import quantitymeasurement.entity.QuantityDTO;
import quantitymeasurement.entity.QuantityMeasurementEntity;
import quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementController {
    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null");
        }
        this.service = service;
    }

    public void demonstrateEquality(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity result = service.compare(q1, q2);
        System.out.println(result.toString());
    }

    public void demonstrateConversion(QuantityDTO q1, QuantityDTO.IMeasurableUnit targetUnit) {
        QuantityMeasurementEntity result = service.convert(q1, targetUnit);
        System.out.println(result.toString());
    }

    public void demonstrateAddition(QuantityDTO q1, QuantityDTO q2, QuantityDTO.IMeasurableUnit targetUnit) {
        QuantityMeasurementEntity result = service.add(q1, q2, targetUnit);
        System.out.println(result.toString());
    }

    public void demonstrateSubtraction(QuantityDTO q1, QuantityDTO q2, QuantityDTO.IMeasurableUnit targetUnit) {
        QuantityMeasurementEntity result = service.subtract(q1, q2, targetUnit);
        System.out.println(result.toString());
    }

    public void demonstrateDivision(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity result = service.divide(q1, q2);
        System.out.println(result.toString());
    }
}
