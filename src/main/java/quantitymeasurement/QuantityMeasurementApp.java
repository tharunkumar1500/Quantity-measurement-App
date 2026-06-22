package quantitymeasurement;

import quantitymeasurement.controller.QuantityMeasurementController;
import quantitymeasurement.entity.QuantityDTO;
import quantitymeasurement.repository.IQuantityMeasurementRepository;
import quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import quantitymeasurement.service.IQuantityMeasurementService;
import quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
        System.out.println("Welcome to Quantity Measurement App (N-Tier Architecture)!");

        // Dependency Injection
        IQuantityMeasurementRepository repository = new QuantityMeasurementCacheRepository();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);

        System.out.println("\n--- Generic Quantity Equality ---");
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);
        controller.demonstrateEquality(q1, q2);

        QuantityDTO q3 = new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAM);
        QuantityDTO q4 = new QuantityDTO(1000.0, QuantityDTO.WeightUnit.GRAM);
        controller.demonstrateEquality(q3, q4);

        System.out.println("\n--- Generic Quantity Addition ---");
        QuantityDTO q5 = new QuantityDTO(2.0, QuantityDTO.LengthUnit.INCHES);
        QuantityDTO q6 = new QuantityDTO(2.54, QuantityDTO.LengthUnit.CM);
        controller.demonstrateAddition(q5, q6, QuantityDTO.LengthUnit.INCHES);

        QuantityDTO q7 = new QuantityDTO(1.0, QuantityDTO.WeightUnit.POUND);
        QuantityDTO q8 = new QuantityDTO(1.0, QuantityDTO.WeightUnit.POUND);
        controller.demonstrateAddition(q7, q8, QuantityDTO.WeightUnit.KILOGRAM);

        System.out.println("\n--- Volume Demonstration (UC11) ---");
        QuantityDTO v1 = new QuantityDTO(1.0, QuantityDTO.VolumeUnit.LITRE);
        QuantityDTO v2 = new QuantityDTO(1000.0, QuantityDTO.VolumeUnit.MILLILITRE);
        controller.demonstrateEquality(v1, v2);

        QuantityDTO v3 = new QuantityDTO(1.0, QuantityDTO.VolumeUnit.GALLON);
        QuantityDTO v4 = new QuantityDTO(3.785, QuantityDTO.VolumeUnit.LITRE);
        controller.demonstrateAddition(v3, v4, QuantityDTO.VolumeUnit.GALLON);

        System.out.println("\n--- Subtraction & Division (UC12) ---");
        QuantityDTO f1 = new QuantityDTO(10.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO f2 = new QuantityDTO(6.0, QuantityDTO.LengthUnit.INCHES);
        controller.demonstrateSubtraction(f1, f2, QuantityDTO.LengthUnit.FEET);

        QuantityDTO kg2 = new QuantityDTO(10.0, QuantityDTO.WeightUnit.KILOGRAM);
        QuantityDTO kg3 = new QuantityDTO(5.0, QuantityDTO.WeightUnit.KILOGRAM);
        controller.demonstrateDivision(kg2, kg3);

        System.out.println("\n--- Temperature Demonstration (UC14/UC15) ---");
        QuantityDTO c1 = new QuantityDTO(0.0, QuantityDTO.TemperatureUnit.CELSIUS);
        QuantityDTO f4 = new QuantityDTO(32.0, QuantityDTO.TemperatureUnit.FAHRENHEIT);
        controller.demonstrateEquality(c1, f4);

        System.out.println("Attempting unsupported operation (Addition on Temperature)...");
        controller.demonstrateAddition(c1, f4, QuantityDTO.TemperatureUnit.CELSIUS);
    }
}
