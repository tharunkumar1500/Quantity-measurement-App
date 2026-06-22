package quantitymeasurement;

public enum LengthUnit implements IMeasurable {
    FEET(12.0),
    INCHES(1.0),
    YARD(36.0),
    CM(1.0 / 2.54);

    private final double baseUnitConversionFactor;

    LengthUnit(double baseUnitConversionFactor) {
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

    @Override
    public String getMeasurementType() {
        return "Length";
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        for (LengthUnit unit : LengthUnit.values()) {
            if (unit.name().equalsIgnoreCase(unitName) || unit.getUnitName().equalsIgnoreCase(unitName)) {
                return unit;
            }
        }
        return null;
    }
}
