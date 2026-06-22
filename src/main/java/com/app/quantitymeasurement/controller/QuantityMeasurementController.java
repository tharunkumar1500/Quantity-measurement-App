package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/measurement")
public class QuantityMeasurementController {
    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementController.class);

    private final IQuantityMeasurementService service;

    @Autowired
    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    @PostMapping("/compare")
    public ResponseEntity<QuantityMeasurementEntity> compare(@RequestBody CompareRequest request) {
        QuantityMeasurementEntity result = service.compare(request.getQ1(), request.getQ2());
        logger.info(result.toString());
        return result.hasError() ? ResponseEntity.badRequest().body(result) : ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityMeasurementEntity> add(@RequestBody ArithmeticRequest request) {
        QuantityMeasurementEntity result = service.add(request.getQ1(), request.getQ2(), request.getTargetUnit());
        logger.info(result.toString());
        return result.hasError() ? ResponseEntity.badRequest().body(result) : ResponseEntity.ok(result);
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityMeasurementEntity> subtract(@RequestBody ArithmeticRequest request) {
        QuantityMeasurementEntity result = service.subtract(request.getQ1(), request.getQ2(), request.getTargetUnit());
        logger.info(result.toString());
        return result.hasError() ? ResponseEntity.badRequest().body(result) : ResponseEntity.ok(result);
    }

    @PostMapping("/divide")
    public ResponseEntity<QuantityMeasurementEntity> divide(@RequestBody CompareRequest request) {
        QuantityMeasurementEntity result = service.divide(request.getQ1(), request.getQ2());
        logger.info(result.toString());
        return result.hasError() ? ResponseEntity.badRequest().body(result) : ResponseEntity.ok(result);
    }

    @PostMapping("/convert")
    public ResponseEntity<QuantityMeasurementEntity> convert(@RequestBody ConvertRequest request) {
        QuantityMeasurementEntity result = service.convert(request.getQ1(), request.getTargetUnit());
        logger.info(result.toString());
        return result.hasError() ? ResponseEntity.badRequest().body(result) : ResponseEntity.ok(result);
    }

    // Inner classes for request payloads
    public static class CompareRequest {
        private QuantityDTO q1;
        private QuantityDTO q2;
        public QuantityDTO getQ1() { return q1; }
        public void setQ1(QuantityDTO q1) { this.q1 = q1; }
        public QuantityDTO getQ2() { return q2; }
        public void setQ2(QuantityDTO q2) { this.q2 = q2; }
    }

    public static class ArithmeticRequest {
        private QuantityDTO q1;
        private QuantityDTO q2;
        private QuantityDTO.IMeasurableUnit targetUnit;
        public QuantityDTO getQ1() { return q1; }
        public void setQ1(QuantityDTO q1) { this.q1 = q1; }
        public QuantityDTO getQ2() { return q2; }
        public void setQ2(QuantityDTO q2) { this.q2 = q2; }
        public QuantityDTO.IMeasurableUnit getTargetUnit() { return targetUnit; }
        public void setTargetUnit(QuantityDTO.IMeasurableUnit targetUnit) { this.targetUnit = targetUnit; }
    }

    public static class ConvertRequest {
        private QuantityDTO q1;
        private QuantityDTO.IMeasurableUnit targetUnit;
        public QuantityDTO getQ1() { return q1; }
        public void setQ1(QuantityDTO q1) { this.q1 = q1; }
        public QuantityDTO.IMeasurableUnit getTargetUnit() { return targetUnit; }
        public void setTargetUnit(QuantityDTO.IMeasurableUnit targetUnit) { this.targetUnit = targetUnit; }
    }

    // Retaining legacy method for test compatibility
    public void compare(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity result = service.compare(q1, q2);
        logger.info(result.toString());
    }

    public void demonstrateAddition(QuantityDTO q1, QuantityDTO q2, QuantityDTO.IMeasurableUnit targetUnit) {
        QuantityMeasurementEntity result = service.add(q1, q2, targetUnit);
        logger.info(result.toString());
    }
}
