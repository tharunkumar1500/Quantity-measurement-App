package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.DatabaseException;
import com.app.quantitymeasurement.util.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {
    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementDatabaseRepository.class);
    private final ConnectionPool connectionPool;

    public QuantityMeasurementDatabaseRepository() {
        this.connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public QuantityMeasurementEntity save(QuantityMeasurementEntity entity) {
        String sql = "INSERT INTO quantity_measurement_history (operation, operand1_value, operand1_unit, " +
                "operand2_value, operand2_unit, result, error_message, measurement_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            
            try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, entity.getOperation());
                pstmt.setDouble(2, entity.getOperand1Value());
                pstmt.setString(3, entity.getOperand1Unit());
                pstmt.setDouble(4, entity.getOperand2Value());
                pstmt.setString(5, entity.getOperand2Unit());
                pstmt.setString(6, entity.getResult());
                pstmt.setString(7, entity.getErrorMessage());
                pstmt.setString(8, entity.getMeasurementType());
                
                pstmt.executeUpdate();
                connection.commit();
                
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        entity.setId(rs.getLong(1));
                    }
                }
                
                logger.info("Saved entity to database: {}", entity.getOperation());
                return entity;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            logger.error("Error saving entity to database", e);
            throw new DatabaseException("Error saving entity to database", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ignored) {}
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        String sql = "SELECT * FROM quantity_measurement_history ORDER BY id ASC";
        return executeQueryList(sql);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        String sql = "SELECT * FROM quantity_measurement_history WHERE operation = ? ORDER BY id ASC";
        return executeQueryList(sql, operation);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {
        String sql = "SELECT * FROM quantity_measurement_history WHERE measurement_type = ? ORDER BY id ASC";
        return executeQueryList(sql, measurementType);
    }

    @Override
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM quantity_measurement_history";
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            try (PreparedStatement pstmt = connection.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            }
        } catch (SQLException e) {
            logger.error("Error getting total count", e);
            throw new DatabaseException("Error getting total count", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM quantity_measurement_history";
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.executeUpdate();
                connection.commit();
                logger.info("Deleted all measurements from database.");
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            logger.error("Error deleting all measurements", e);
            throw new DatabaseException("Error deleting all measurements", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ignored) {}
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public String getPoolStatistics() {
        return connectionPool.getPoolStatistics();
    }

    @Override
    public void closeResources() {
        connectionPool.closeAll();
    }

    private List<QuantityMeasurementEntity> executeQueryList(String sql, String... params) {
        List<QuantityMeasurementEntity> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setString(i + 1, params[i]);
                }
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        list.add(mapResultSetToEntity(rs));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing query list", e);
            throw new DatabaseException("Error executing query list", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    private QuantityMeasurementEntity mapResultSetToEntity(ResultSet rs) throws SQLException {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setId(rs.getLong("id"));
        entity.setOperation(rs.getString("operation"));
        entity.setOperand1Value(rs.getDouble("operand1_value"));
        entity.setOperand1Unit(rs.getString("operand1_unit"));
        entity.setOperand2Value(rs.getDouble("operand2_value"));
        entity.setOperand2Unit(rs.getString("operand2_unit"));
        entity.setResult(rs.getString("result"));
        entity.setErrorMessage(rs.getString("error_message"));
        entity.setMeasurementType(rs.getString("measurement_type"));
        return entity;
    }
}
