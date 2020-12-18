package by.training.dao.sql;

import by.training.dao.DaoException;
import by.training.dao.UserDao;
import by.training.domain.User;
import by.training.enums.Roles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public List<User> getUsersList() throws DaoException {
        String sql = "SELECT `id`, `login`, `password`, `e-mail`, `role` FROM `users`";
        Statement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setMail(resultSet.getString("e-mail"));
                user.setRole(Roles.values()[resultSet.getInt("role")]);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public User getByLogin(String login) throws DaoException {
        String sql = "SELECT `id`, `password`, `e-mail`, `role` FROM `users` WHERE `login` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
                user.setMail(resultSet.getString("e-mail"));
                user.setRole(Roles.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Long create(User user) throws DaoException {
        String sql = "INSERT INTO `users` (`login`, `password`, `e-mail`, `role`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getMail());
            statement.setLong(4, user.getRole().getId());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public User read(Long id) throws DaoException {
        String sql = "SELECT `login`, `password`, `e-mail`, `role` FROM `users` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setMail(resultSet.getString("e-mail"));
                user.setRole(Roles.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(User user) throws DaoException {
        String sql = "UPDATE `users` SET `login` = ?, `password` = ?, `e-mail` = ?, `role` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getMail());
            statement.setLong(4, user.getRole().getId());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }
}
