package quantitymeasurement;

public enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double baseUnitConversionFactor;

    WeightUnit(double baseUnitConversionFactor) {
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
