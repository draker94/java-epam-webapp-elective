package by.training.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionData {
    private static ConnectionData instance = null;
    private BlockingQueue<Connection> connectionQueue = null;
    private static int poolSize = 10;

    public static int getPoolSize() {
        return poolSize;
    }

    public static void setPoolSize(int poolSize) {
        ConnectionData.poolSize = poolSize;
    }

    public void initConnections(String property) {
        if (instance == null) {
            Properties prop = new Properties();
            try {
                prop.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
                String url = prop.getProperty("url");
                String username = prop.getProperty("username");
                String password = prop.getProperty("password");
                instance = new ConnectionData(url, username, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public static ConnectionData getInstance() {
        return instance;
    }
}

