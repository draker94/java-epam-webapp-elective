package by.training.service.logic;

import by.training.dao.DaoException;
import by.training.dao.UserDao;
import by.training.dao.sql.UserDaoImpl;
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
        Long id = user.getId();
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
        try {
            return userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            userDao.delete(id, "users");
            UserDao userDao1 = new UserDaoImpl();
            userDao1.delete(1L, "dada");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        try {
            return userDao.getByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changePassword(String login, String oldPassword, String newPassword) throws ServiceException {
        boolean isChanged = false;
        try {
            User user = userDao.getByLogin(login);
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
    public boolean changeMail(String login, String mail) throws ServiceException {
        boolean isChanged = false;
        try {
            User user = userDao.getByLogin(login);
            if (user.getLogin().equals(login) &&
                    mail.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\\b")) {
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
}
