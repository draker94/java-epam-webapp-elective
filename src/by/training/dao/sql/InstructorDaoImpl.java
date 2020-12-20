package by.training.dao.sql;

import by.training.dao.DaoException;
import by.training.dao.InstructorDao;
import by.training.domain.Instructor;
import by.training.enums.Ranks;
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

public class InstructorDaoImpl extends BaseDaoImpl implements InstructorDao {
    private static final Logger LOGGER = LogManager.getLogger(InstructorDaoImpl.class);

    @Override
    public List<Instructor> getInstructorsList() throws DaoException {
        String sql = "SELECT `id`, `surname`, `name`, `rank` FROM `instructors`";
        Statement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Instructor> instructors = new ArrayList<>();
            while (resultSet.next()) {
                Instructor instructor = new Instructor();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
                instructor.setRank(Ranks.values()[resultSet.getInt("rank")]);
                instructors.add(instructor);
            }
            return instructors;
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
    public List<Instructor> getBySurname(String surname) throws DaoException {
        String sql = "SELECT `id`, `surname`, `name`, `rank` FROM `instructors` WHERE `surname` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, surname);
            resultSet = statement.executeQuery();
            List<Instructor> instructors = new ArrayList<>();
            while (resultSet.next()) {
                Instructor instructor = new Instructor();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
                instructor.setRank(Ranks.values()[resultSet.getInt("rank")]);
                instructors.add(instructor);
            }
            return instructors;
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
    public Long create(Instructor instructor) throws DaoException {
        String sql = "INSERT INTO `instructors` (`id`, `surname`, `name`, `rank`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, instructor.getId());
            statement.setString(2, instructor.getSurname());
            statement.setString(3, instructor.getName());
            statement.setLong(4, instructor.getRank().getId());
            statement.executeUpdate();
            return instructor.getId();
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
    public Instructor read(Long id) throws DaoException {
        String sql = "SELECT `id`, `surname`, `name`, `rank` FROM `instructors` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Instructor instructor = null;
            if (resultSet.next()) {
                instructor = new Instructor();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
                instructor.setRank(Ranks.values()[resultSet.getInt("rank")]);
            }
            return instructor;
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
    public void update(Instructor instructor) throws DaoException {
        String sql = "UPDATE `instructors` SET `surname` = ?, `name` = ?, `rank` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, instructor.getSurname());
            statement.setString(2, instructor.getName());
            statement.setLong(3, instructor.getRank().getId());
            statement.setLong(4, instructor.getId());
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