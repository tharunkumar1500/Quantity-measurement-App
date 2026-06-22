package com.app.quantitymeasurement.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurement_history")
public class QuantityMeasurementEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private QuantityDTO operand1;

    @Transient
    private QuantityDTO operand2;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "result")
    private String result;

    @Column(name = "has_error")
    private boolean hasError;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "measurement_type")
    private String measurementType;

    public QuantityMeasurementEntity() {}

    // Single operand operation (e.g. conversion)
    public QuantityMeasurementEntity(QuantityDTO operand1, String operationType, String result) {
        this.operand1 = operand1;
        this.operationType = operationType;
        this.result = result;
        this.hasError = false;
        if (operand1 != null) {
            this.measurementType = operand1.getUnit().getClass().getSimpleName().replace("Unit", "").toUpperCase();
        }
    }

    // Binary operation
    public QuantityMeasurementEntity(QuantityDTO operand1, QuantityDTO operand2, String operationType, String result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operationType = operationType;
        this.result = result;
        this.hasError = false;
        if (operand1 != null) {
            this.measurementType = operand1.getUnit().getClass().getSimpleName().replace("Unit", "").toUpperCase();
        }
    }

    // Error
    public QuantityMeasurementEntity(String operationType, String errorMessage) {
        this.operationType = operationType;
        this.hasError = true;
        this.errorMessage = errorMessage;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public QuantityDTO getOperand1() { return operand1; }
    public void setOperand1(QuantityDTO operand1) { this.operand1 = operand1; }
    public QuantityDTO getOperand2() { return operand2; }
    public void setOperand2(QuantityDTO operand2) { this.operand2 = operand2; }
    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }
    public String getOperation() { return operationType; }
    public void setOperation(String operation) { this.operationType = operation; }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public boolean hasError() { return hasError; }
    public void setHasError(boolean hasError) { this.hasError = hasError; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    public String getMeasurementType() { return measurementType; }
    public void setMeasurementType(String measurementType) { this.measurementType = measurementType; }
    
    public double getOperand1Value() { return operand1 != null ? operand1.getValue() : 0.0; }
    public String getOperand1Unit() { return operand1 != null ? operand1.getUnit().toString() : null; }
    public double getOperand2Value() { return operand2 != null ? operand2.getValue() : 0.0; }
    public String getOperand2Unit() { return operand2 != null ? operand2.getUnit().toString() : null; }
    
    public void setOperand1Value(double v) {}
    public void setOperand1Unit(String u) {}
    public void setOperand2Value(double v) {}
    public void setOperand2Unit(String u) {}

    @Override
    public String toString() {
        if (hasError) {
            return "Operation: " + operationType + " | Error: " + errorMessage;
        }
        if (operand2 == null) {
            return "Operation: " + operationType + " | Operand1: " + operand1.getValue() + " " + operand1.getUnit() + " | Result: " + result;
        }
        return "Operation: " + operationType + " | Operand1: " + operand1.getValue() + " " + operand1.getUnit() + " | Operand2: " + operand2.getValue() + " " + operand2.getUnit() + " | Result: " + result;
    }
}
