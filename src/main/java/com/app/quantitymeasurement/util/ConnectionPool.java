package com.app.quantitymeasurement.util;

import com.app.quantitymeasurement.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private final String url;
    private final String username;
    private final String password;
    private final int maxPoolSize;
    private final int timeout;
    
    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections;

    private static ConnectionPool instance;

    private ConnectionPool() {
        this.url = ApplicationConfig.getProperty("db.url", "jdbc:h2:mem:quantitydb;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:db/schema.sql'");
        this.username = ApplicationConfig.getProperty("db.username", "sa");
        this.password = ApplicationConfig.getProperty("db.password", "");
        
        try {
            Class.forName(ApplicationConfig.getProperty("db.driver", "org.h2.Driver"));
        } catch (ClassNotFoundException e) {
            logger.error("Database driver not found", e);
            throw new DatabaseException("Database driver not found", e);
        }

        int initialPoolSize = Integer.parseInt(ApplicationConfig.getProperty("pool.initialSize", "5"));
        this.maxPoolSize = Integer.parseInt(ApplicationConfig.getProperty("pool.maxSize", "15"));
        this.timeout = Integer.parseInt(ApplicationConfig.getProperty("pool.timeout", "5000"));

        connectionPool = new ArrayList<>(initialPoolSize);
        usedConnections = new ArrayList<>(initialPoolSize);

        for (int i = 0; i < initialPoolSize; i++) {
            connectionPool.add(createConnection());
        }
        logger.info("Connection pool initialized with {} connections.", initialPoolSize);
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.error("Error creating database connection", e);
            throw new DatabaseException("Error creating database connection", e);
        }
    }

    public synchronized Connection getConnection() {
        long startTime = System.currentTimeMillis();
        
        while (connectionPool.isEmpty()) {
            if (usedConnections.size() < maxPoolSize) {
                connectionPool.add(createConnection());
            } else {
                try {
                    long remainingWait = timeout - (System.currentTimeMillis() - startTime);
                    if (remainingWait <= 0) {
                        throw new DatabaseException("Timeout waiting for database connection");
                    }
                    wait(remainingWait);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new DatabaseException("Interrupted while waiting for connection", e);
                }
            }
        }
        
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        try {
            if (!connection.isValid(1)) {
                connection = createConnection();
            }
        } catch (SQLException e) {
            connection = createConnection();
        }
        
        usedConnections.add(connection);
        return connection;
    }

    public synchronized boolean releaseConnection(Connection connection) {
        if (connection != null) {
            usedConnections.remove(connection);
            connectionPool.add(connection);
            notifyAll();
            return true;
        }
        return false;
    }

    public synchronized String getPoolStatistics() {
        return String.format("Pool Statistics - Available: %d, In Use: %d, Total: %d, Max: %d",
                connectionPool.size(), usedConnections.size(),
                connectionPool.size() + usedConnections.size(), maxPoolSize);
    }

    public synchronized void closeAll() {
        try {
            for (Connection c : connectionPool) {
                if (c != null && !c.isClosed()) {
                    c.close();
                }
            }
            for (Connection c : usedConnections) {
                if (c != null && !c.isClosed()) {
                    c.close();
                }
            }
            connectionPool.clear();
            usedConnections.clear();
            logger.info("All database connections closed.");
        } catch (SQLException e) {
            logger.error("Error closing connections", e);
        }
    }
}
