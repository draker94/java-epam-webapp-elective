package by.training.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Andrey Kliuchnikov
 */

public final class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance = new ConnectionPool();
    private Queue<Connection> freeConnection = new ConcurrentLinkedQueue<>();
    private Collection<Connection> usedConnection = new CopyOnWriteArraySet<>();
    private String jdbcUrl;
    private String user;
    private String password;
    private int maxSize;
    private int minSize;
    private int validConTimeout;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void initConnections(String propertyConfig, int minSize, int maxSize, int validConTimeout) {
        destroy();
        try {
            Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
            DriverManager.registerDriver(driver);
            ResourceBundle resourceBundle = ResourceBundle.getBundle(propertyConfig);
            this.jdbcUrl = resourceBundle.getString("url");
            this.user = resourceBundle.getString("username");
            this.password = resourceBundle.getString("password");
            this.minSize = minSize;
            this.maxSize = maxSize;
            this.validConTimeout = validConTimeout;
            for (int i = 0; i < minSize; i++) {
                freeConnection.add(newConnection());
            }
            LOGGER.info("Connection pool has been successfully initialized.");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException | ClassNotFoundException | SQLException e) {
            LOGGER.error("Connection pool initialized error");
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        while (connection == null) {
            try {
                connection = freeConnection.poll();
                if (connection != null) {
                    if (!connection.isValid(validConTimeout)) {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            LOGGER.error("Error handling invalid connection.");
                        }
                        connection = null;
                    }
                } else if (usedConnection.size() < maxSize) {
                    connection = newConnection();
                } else {
                    LOGGER.error("The maximum number of connections has been reached.");
                    throw new ConnectionPoolException("The maximum number of connections has been reached.");
                }
            } catch (SQLException e) {
                LOGGER.error("Error getting connection.");
                throw new ConnectionPoolException(e);
            }
        }
        usedConnection.add(connection);
        LOGGER.debug("Connection pool returns connection.");
        return new ConnectionWrapper(connection);
    }

    public void destroy() {
        synchronized (freeConnection) {
            synchronized (usedConnection) {
                usedConnection.addAll(freeConnection);
                for (Connection connection : usedConnection) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        LOGGER.error("Connection closing error while clearing the connection pool");
                    }
                }
                usedConnection.clear();
                LOGGER.info("Connection pool successfully destroyed.");
            }
        }
    }

    void freeConnection(Connection connection) {
        try {
            connection.clearWarnings();
            usedConnection.remove(connection);
            freeConnection.add(connection);
            LOGGER.trace("Returned the connection to the pool.");
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException e1) {
                LOGGER.error("Connection closing error while handling the return of the collection to the pool");
            }
        }
    }

    private Connection newConnection() throws SQLException {
        LOGGER.info("Create a connection.");
        return DriverManager.getConnection(jdbcUrl, user, password);
    }
}


