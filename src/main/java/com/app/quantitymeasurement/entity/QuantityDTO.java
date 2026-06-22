package com.app.quantitymeasurement.entity;

public class QuantityDTO {
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
