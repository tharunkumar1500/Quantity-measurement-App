package quantitymeasurement.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private QuantityDTO operand1;
    private QuantityDTO operand2;
    private String operationType;
    private String result;
    private boolean hasError;
    private String errorMessage;

    // Single operand operation (e.g. conversion)
    public QuantityMeasurementEntity(QuantityDTO operand1, String operationType, String result) {
        this.operand1 = operand1;
        this.operationType = operationType;
        this.result = result;
        this.hasError = false;
    }

    // Binary operation
    public QuantityMeasurementEntity(QuantityDTO operand1, QuantityDTO operand2, String operationType, String result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operationType = operationType;
        this.result = result;
        this.hasError = false;
    }

    // Error
    public QuantityMeasurementEntity(String operationType, String errorMessage) {
        this.operationType = operationType;
        this.hasError = true;
        this.errorMessage = errorMessage;
    }

    public QuantityDTO getOperand1() { return operand1; }
    public QuantityDTO getOperand2() { return operand2; }
    public String getOperationType() { return operationType; }
    public String getResult() { return result; }
    public boolean hasError() { return hasError; }
    public String getErrorMessage() { return errorMessage; }

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
