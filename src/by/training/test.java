package by.training;

import by.training.dao.DaoException;
import by.training.dao.sql.BaseDaoImpl;
import by.training.dao.sql.UserDaoImpl;
import by.training.domain.User;
import by.training.util.ConnectionData;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws DaoException {
        ConnectionData.initConnections();
        Connection connection = ConnectionData.getInstance().getConnection();
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(connection);

        List<User> list = userDao.getUserList();
        list.forEach(System.out::println);
    }

}
