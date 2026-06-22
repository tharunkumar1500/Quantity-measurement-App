package quantitymeasurement;

public interface Unit {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
}
