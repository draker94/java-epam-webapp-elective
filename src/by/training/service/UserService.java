package by.training.service;

import by.training.domain.User;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public interface UserService {
    List<User> findAll() throws ServiceException;

    User findById(Long id) throws ServiceException;

    Long save(User user) throws ServiceException;

    void delete(Long id) throws ServiceException;

    User findByLogin(String login) throws ServiceException;

    boolean changePassword(String  login, String oldPassword, String newPassword) throws ServiceException;

    boolean changeMail(String login, String mail) throws ServiceException;

}
