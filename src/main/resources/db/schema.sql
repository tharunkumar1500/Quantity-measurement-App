CREATE TABLE IF NOT EXISTS quantity_measurement_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation VARCHAR(50) NOT NULL,
    operand1_value DOUBLE,
    operand1_unit VARCHAR(50),
    operand2_value DOUBLE,
    operand2_unit VARCHAR(50),
    result VARCHAR(255),
    error_message VARCHAR(255),
    measurement_type VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_operation ON quantity_measurement_history(operation);
CREATE INDEX IF NOT EXISTS idx_measurement_type ON quantity_measurement_history(measurement_type);
