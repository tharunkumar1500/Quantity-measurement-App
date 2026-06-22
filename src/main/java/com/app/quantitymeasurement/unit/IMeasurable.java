package com.app.quantitymeasurement;

public interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double value);
    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }
    String getUnitName();

    default void validateOperationSupport(String operation) {
        // By default, units support all operations.
    }

    String getMeasurementType();
    IMeasurable getUnitInstance(String unitName);
}
