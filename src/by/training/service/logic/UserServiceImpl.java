package by.training.service.logic;

import by.training.dao.DaoException;
import by.training.dao.UserDao;
import by.training.domain.User;
import by.training.service.ServiceException;
import by.training.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return userDao.getUsersList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(User user) throws ServiceException {
        Long id = user.getId();
        LOGGER.debug("Method entering.");
        try {
            if (id != null) {
                userDao.update(user);
            } else {
                id = userDao.create(user);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            for (Long id : ids) {
                userDao.delete(id, "users");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLoginAndPass(String login, String password) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return userDao.getByLoginAndPass(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllFreeUsers() throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return userDao.getFreeUsersList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
