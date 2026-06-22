package com.app.quantitymeasurement;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.app.quantitymeasurement.util.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityMeasurementApp {
    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementApp.class);
    private final QuantityMeasurementController controller;
    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementApp() {
        String repoType = ApplicationConfig.getProperty("app.repository.type", "CACHE").toUpperCase();
        logger.info("Initializing QuantityMeasurementApp with {} repository.", repoType);

        if ("DATABASE".equals(repoType)) {
            this.repository = new QuantityMeasurementDatabaseRepository();
        } else {
            this.repository = new QuantityMeasurementCacheRepository();
        }

        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(this.repository);
        this.controller = new QuantityMeasurementController(service);
    }

    public void runDemonstration() {
        logger.info("--- Starting Quantity Measurement App Demonstration ---");
        
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);
        controller.compare(q1, q2);

        QuantityDTO q3 = new QuantityDTO(1.0, QuantityDTO.VolumeUnit.GALLON);
        QuantityDTO q4 = new QuantityDTO(3.78, QuantityDTO.VolumeUnit.LITRE);
        controller.compare(q3, q4);

        QuantityDTO q5 = new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAM);
        QuantityDTO q6 = new QuantityDTO(1000.0, QuantityDTO.WeightUnit.GRAM);
        controller.compare(q5, q6);

        QuantityDTO q7 = new QuantityDTO(212.0, QuantityDTO.TemperatureUnit.FAHRENHEIT);
        QuantityDTO q8 = new QuantityDTO(100.0, QuantityDTO.TemperatureUnit.CELSIUS);
        controller.compare(q7, q8);

        logger.info("--- Demonstration Finished ---");
    }

    public void reportAllMeasurements() {
        logger.info("--- Measurements Stored in Repository ---");
        repository.getAllMeasurements().forEach(entity -> logger.info(entity.toString()));
        logger.info("Total Measurements: {}", repository.getTotalCount());
        logger.info("Pool Statistics: {}", repository.getPoolStatistics());
    }

    public void deleteAllMeasurements() {
        logger.info("Deleting all measurements...");
        repository.deleteAll();
    }

    public void closeResources() {
        logger.info("Closing repository resources...");
        repository.closeResources();
    }

    public static void main(String[] args) {
        QuantityMeasurementApp app = new QuantityMeasurementApp();
        app.runDemonstration();
        app.reportAllMeasurements();
        app.deleteAllMeasurements();
        app.closeResources();
    }
}
