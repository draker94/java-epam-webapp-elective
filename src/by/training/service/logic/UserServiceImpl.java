package by.training.service.logic;

import by.training.dao.DaoException;
import by.training.dao.UserDao;
import by.training.dao.sql.UserDaoImpl;
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
            for(Long id : ids) {
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
    public boolean changePassword(Long id, String oldPassword, String newPassword) throws ServiceException {
        boolean isChanged = false;
        LOGGER.debug("Method entering.");
        try {
            User user = userDao.read(id);
            if (user.getPassword().equals(oldPassword) && !(newPassword.isEmpty())) {
                user.setPassword(newPassword);
                userDao.update(user);
                isChanged = true;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            return isChanged;
        }
    }

    @Override
    public boolean changeMail(Long id, String mail) throws ServiceException {
        boolean isChanged = false;
        LOGGER.debug("Method entering.");
        try {
            User user = userDao.read(id);
            if (mail.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\\b")) {
                user.setMail(mail);
                userDao.update(user);
                isChanged = true;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            return isChanged;
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
