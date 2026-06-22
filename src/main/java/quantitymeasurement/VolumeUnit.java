package quantitymeasurement;

public enum VolumeUnit implements IMeasurable {
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double baseUnitConversionFactor;

    VolumeUnit(double baseUnitConversionFactor) {
        this.baseUnitConversionFactor = baseUnitConversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return this.baseUnitConversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * this.baseUnitConversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / this.baseUnitConversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.name().toLowerCase();
    }
}
