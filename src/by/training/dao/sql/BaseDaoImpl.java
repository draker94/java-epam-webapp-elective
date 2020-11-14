package by.training.dao.sql;

import java.sql.Connection;

public class BaseDaoImpl {
    private Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
