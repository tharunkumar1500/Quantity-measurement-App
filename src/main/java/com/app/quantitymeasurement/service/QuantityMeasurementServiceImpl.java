package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.IMeasurable;
import com.app.quantitymeasurement.LengthUnit;
import com.app.quantitymeasurement.Quantity;
import com.app.quantitymeasurement.TemperatureUnit;
import com.app.quantitymeasurement.VolumeUnit;
import com.app.quantitymeasurement.WeightUnit;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.entity.QuantityModel;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementServiceImpl.class);
    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    private <U extends IMeasurable> U getDomainUnit(QuantityDTO.IMeasurableUnit dtoUnit) {
        if (dtoUnit == null) return null;
        String name = ((Enum<?>) dtoUnit).name();

        IMeasurable unit = LengthUnit.FEET.getUnitInstance(name);
        if (unit == null) unit = WeightUnit.KILOGRAM.getUnitInstance(name);
        if (unit == null) unit = VolumeUnit.LITRE.getUnitInstance(name);
        if (unit == null) unit = TemperatureUnit.CELSIUS.getUnitInstance(name);

        if (unit != null) {
            return (U) unit;
        }
        throw new IllegalArgumentException("Unknown unit: " + name);
    }

    private <U extends IMeasurable> QuantityModel<U> createModel(QuantityDTO dto) {
        U domainUnit = getDomainUnit(dto.getUnit());
        return new QuantityModel<>(new Quantity<>(dto.getValue(), domainUnit));
    }

    @Override
    public QuantityMeasurementEntity compare(QuantityDTO q1, QuantityDTO q2) {
        try {
            IMeasurable domainUnit1 = getDomainUnit(q1.getUnit());
            IMeasurable domainUnit2 = getDomainUnit(q2.getUnit());

            if (!domainUnit1.getMeasurementType().equals(domainUnit2.getMeasurementType())) {
                throw new IllegalArgumentException("Cannot compare quantities of different measurement categories");
            }

            QuantityModel<?> model1 = new QuantityModel<>(new Quantity<>(q1.getValue(), domainUnit1));
            QuantityModel<?> model2 = new QuantityModel<>(new Quantity<>(q2.getValue(), domainUnit2));

            boolean result = model1.getQuantity().equals(model2.getQuantity());
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, q2, "COMPARE", String.valueOf(result));
            repository.save(entity);
            return entity;
        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity("COMPARE", e.getMessage());
            repository.save(errorEntity);
            return errorEntity;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementEntity convert(QuantityDTO q1, QuantityDTO.IMeasurableUnit targetUnit) {
        try {
            QuantityModel<?> model1 = createModel(q1);
            IMeasurable domainTarget = getDomainUnit(targetUnit);

            double resultValue = ((Quantity<IMeasurable>) model1.getQuantity()).convertTo(domainTarget);
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, "CONVERT to " + targetUnit, String.valueOf(resultValue));
            repository.save(entity);
            return entity;
        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity("CONVERT", e.getMessage());
            repository.save(errorEntity);
            return errorEntity;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementEntity add(QuantityDTO q1, QuantityDTO q2, QuantityDTO.IMeasurableUnit targetUnit) {
        try {
            QuantityModel<?> model1 = createModel(q1);
            QuantityModel<?> model2 = createModel(q2);
            IMeasurable domainTarget = getDomainUnit(targetUnit);

            Quantity<?> result = ((Quantity<IMeasurable>) model1.getQuantity()).add((Quantity<IMeasurable>) model2.getQuantity(), domainTarget);
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, q2, "ADD", result.toString());
            repository.save(entity);
            return entity;
        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity("ADD", e.getMessage());
            repository.save(errorEntity);
            return errorEntity;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementEntity subtract(QuantityDTO q1, QuantityDTO q2, QuantityDTO.IMeasurableUnit targetUnit) {
        try {
            QuantityModel<?> model1 = createModel(q1);
            QuantityModel<?> model2 = createModel(q2);
            IMeasurable domainTarget = getDomainUnit(targetUnit);

            Quantity<?> result = ((Quantity<IMeasurable>) model1.getQuantity()).subtract((Quantity<IMeasurable>) model2.getQuantity(), domainTarget);
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, q2, "SUBTRACT", result.toString());
            repository.save(entity);
            return entity;
        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity("SUBTRACT", e.getMessage());
            repository.save(errorEntity);
            return errorEntity;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementEntity divide(QuantityDTO q1, QuantityDTO q2) {
        try {
            QuantityModel<?> model1 = createModel(q1);
            QuantityModel<?> model2 = createModel(q2);

            double result = ((Quantity<IMeasurable>) model1.getQuantity()).divide((Quantity<IMeasurable>) model2.getQuantity());
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, q2, "DIVIDE", String.valueOf(result));
            repository.save(entity);
            return entity;
        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity("DIVIDE", e.getMessage());
            repository.save(errorEntity);
            return errorEntity;
        }
    }
}
