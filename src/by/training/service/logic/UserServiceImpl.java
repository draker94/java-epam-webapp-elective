package by.training.service.logic;

import by.training.dao.DaoException;
import by.training.dao.UserDao;
import by.training.domain.User;
import by.training.service.ServiceException;
import by.training.service.UserService;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.getUsersList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(User user) throws ServiceException {
        try {
            if (user.getId() != null) {
                userDao.update(user);
            } else {
                userDao.create(user);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
