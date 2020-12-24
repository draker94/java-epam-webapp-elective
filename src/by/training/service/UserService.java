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

    void delete(List<Long> ids) throws ServiceException;

    User findByLoginAndPass(String login, String password) throws ServiceException;

    boolean changePassword(Long id, String oldPassword, String newPassword) throws ServiceException;

    boolean changeMail(Long id, String mail) throws ServiceException;

    List<User> findAllFreeUsers() throws ServiceException;
}
