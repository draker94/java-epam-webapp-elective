package by.training.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author Andrey Kliuchnikov
 */

public final class ConnectionPool {
    private static ConnectionPool instance = new ConnectionPool();
    private Queue<Connection> freeConnection = new ConcurrentLinkedQueue<>();
    private Collection<Connection> usedConnection = new ConcurrentSkipListSet<>(Comparator.comparingInt(Object::hashCode));
    //Поискать Concurrent коллекцию на основе хэштаблиц
    private String jdbcUrl;
    private String user;
    private String password;
    private String settingsPath;
    private int maxSize;
    private int minSize;
    private int validConTimeout;

    Logger logger = LogManager.getLogger();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void initConnections(String settingsPath, int minSize, int maxSize, int validConTimeout) {
        destroy();
        try {
            Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
            DriverManager.registerDriver(driver);
            this.settingsPath = settingsPath;
            ResourceBundle resourceBundle = ResourceBundle.getBundle(settingsPath);
            this.jdbcUrl = resourceBundle.getString("url");
            this.user = resourceBundle.getString("username");
            this.password = resourceBundle.getString("password");
            this.minSize = minSize;
            this.maxSize = maxSize;
            this.validConTimeout = validConTimeout;
            for (int i = 0; i < minSize; i++) {
                freeConnection.add(newConnection());
            }
            logger.info("Пул соединений успешно проинициализирован");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException | ClassNotFoundException | SQLException e) {
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
                            // посмотреть где free connect
                        } catch (SQLException e) {
                        }
                        connection = null;
                    }
                } else if (usedConnection.size() < maxSize) { //подумоть о синхронизации
                    connection = newConnection();
                } else {
                    throw new ConnectionPoolException("The database connections number exceeded(истёк) limit");
                }
            } catch (SQLException e) {
                throw new ConnectionPoolException(e);
            }
        }
        usedConnection.add(connection);
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

                    }
                }
                usedConnection.clear();
            }
        }
    }

    void freeConnection(Connection connection) {
        try {
            connection.clearWarnings();
            usedConnection.remove(connection);
            freeConnection.add(connection);
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException e1) {

            }
        }
    }

    private Connection newConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, user, password);
    }




    /*private static ConnectionData instance = null;
    private static int poolSize = 10;
    private static final String PROPERTIES = "by.training.resources.database";
    private BlockingQueue<Connection> connectionQueue = null;

    private ConnectionData() {}

    public static ConnectionData getInstance() {
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void freeConnection(Connection connection) {

    }

    public static int getPoolSize() {
        return poolSize;
    }

    public static void setPoolSize(int poolSize) {
        ConnectionData.poolSize = poolSize;
    }

    public static void initConnections() {
        if (instance == null) {
            Properties prop = new Properties();
            ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTIES);
            String url = resourceBundle.getString("url");
            String username = resourceBundle.getString("username");
            String password = resourceBundle.getString("password");
            instance = new ConnectionData(url, username, password);
        }
    }

    private ConnectionData(String url, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                connectionQueue.offer(connection);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    } */
}


