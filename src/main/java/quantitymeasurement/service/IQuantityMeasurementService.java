package quantitymeasurement.service;

import quantitymeasurement.entity.QuantityDTO;
import quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {
    QuantityMeasurementEntity compare(QuantityDTO q1, QuantityDTO q2);
    QuantityMeasurementEntity convert(QuantityDTO q1, QuantityDTO.IMeasurableUnit targetUnit);
    QuantityMeasurementEntity add(QuantityDTO q1, QuantityDTO q2, QuantityDTO.IMeasurableUnit targetUnit);
    QuantityMeasurementEntity subtract(QuantityDTO q1, QuantityDTO q2, QuantityDTO.IMeasurableUnit targetUnit);
    QuantityMeasurementEntity divide(QuantityDTO q1, QuantityDTO q2);
}
