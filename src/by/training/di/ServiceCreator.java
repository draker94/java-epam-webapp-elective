package by.training.di;

import by.training.connection.ConnectionPool;
import by.training.connection.ConnectionPoolException;
import by.training.dao.UserDao;
import by.training.dao.sql.UserDaoImpl;
import by.training.service.UserService;
import by.training.service.logic.UserServiceImpl;

import java.sql.Connection;

public class ServiceCreator implements AutoCloseable {
    private UserService userService = null;

    public UserService getUserService() throws ServiceCreationException {
        if (userService == null) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            userServiceImpl.setUserDao(getUserDao());
            userService = userServiceImpl;
        }
        return userService;
    }

    private UserDao userDao = null;// Локальное кеширование

    private UserDao getUserDao() throws ServiceCreationException {
        if (userDao == null) {
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            userDaoImpl.setConnection(getConnection());
            userDao = userDaoImpl;
        }
        return userDao;
    }

    private Connection connection = null;

    private Connection getConnection() throws ServiceCreationException {
        if (connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (ConnectionPoolException e) {
                throw new ServiceCreationException((e));
            }
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (Exception e) {
        }
    }
}
