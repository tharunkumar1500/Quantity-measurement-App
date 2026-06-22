package quantitymeasurement;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS(c -> c, c -> c, "celsius"),
    FAHRENHEIT(f -> (f - 32.0) * 5.0 / 9.0, c -> (c * 9.0 / 5.0) + 32.0, "fahrenheit");

    private final Function<Double, Double> toBase;
    private final Function<Double, Double> fromBase;
    private final String unitName;
    private final SupportsArithmetic supportsArithmetic = () -> false;

    TemperatureUnit(Function<Double, Double> toBase, Function<Double, Double> fromBase, String unitName) {
        this.toBase = toBase;
        this.fromBase = fromBase;
        this.unitName = unitName;
    }

    @Override
    public double getConversionFactor() {
        return 1.0; // Not used directly due to custom conversion formulas
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBase.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromBase.apply(baseValue);
    }

    @Override
    public String getUnitName() {
        return unitName;
    }

    @Override
    public void validateOperationSupport(String operation) {
        if (!supportsArithmetic.isSupported()) {
            throw new UnsupportedOperationException("Temperature measurement does not support " + operation);
        }
    }
}
