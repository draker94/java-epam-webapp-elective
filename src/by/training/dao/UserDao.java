package by.training.dao;

import by.training.domain.User;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public interface UserDao extends Dao<User, Long> {
    List<User> getUsersList() throws DaoException;

    User getByLoginAndPass(String login, String password) throws DaoException;

    List<User> getFreeUsersList() throws DaoException;
}
