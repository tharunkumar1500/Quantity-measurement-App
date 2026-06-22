package com.app.quantitymeasurement.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class QuantityDTO {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = LengthUnit.class, name = "LengthUnit"),
        @JsonSubTypes.Type(value = VolumeUnit.class, name = "VolumeUnit"),
        @JsonSubTypes.Type(value = WeightUnit.class, name = "WeightUnit"),
        @JsonSubTypes.Type(value = TemperatureUnit.class, name = "TemperatureUnit")
    })
    public interface IMeasurableUnit {}
    public enum LengthUnit implements IMeasurableUnit { FEET, INCHES, YARD, CM }
    public enum VolumeUnit implements IMeasurableUnit { LITRE, MILLILITRE, GALLON }
    public enum WeightUnit implements IMeasurableUnit { KILOGRAM, GRAM, POUND }
    public enum TemperatureUnit implements IMeasurableUnit { CELSIUS, FAHRENHEIT }

    private double value;
    private IMeasurableUnit unit;

    public QuantityDTO() {}

    public QuantityDTO(double value, IMeasurableUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }
    public IMeasurableUnit getUnit() { return unit; }
    public void setValue(double value) { this.value = value; }
    public void setUnit(IMeasurableUnit unit) { this.unit = unit; }
}
