package by.training.dao;

import by.training.domain.User;

import java.util.List;

public interface UserDao extends Dao<User, Long> {
    List<User> getUsersList() throws DaoException;
    User getByLogin(String login) throws DaoException;
}
