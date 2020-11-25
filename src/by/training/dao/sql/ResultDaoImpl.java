package by.training.dao.sql;

import by.training.dao.DaoException;
import by.training.dao.ResultDao;
import by.training.domain.Assignment;
import by.training.domain.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class ResultDaoImpl extends BaseDaoImpl implements ResultDao {
    @Override
    public List<Result> getResultsList() throws DaoException {
        String sql = "SELECT `id`, `assignment_id`, `mark`, `review`, `date` FROM `results`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Result> results = new ArrayList<>();
            while (resultSet.next()) {
                Result result = new Result();
                result.setId(resultSet.getLong("id"));
                result.setAssignment(new Assignment());
                result.getAssignment().setId(resultSet.getLong("assignment_id"));
                result.setMark(resultSet.getInt("mark"));
                result.setReview(resultSet.getString("review"));
                result.setDate(resultSet.getDate("date").toLocalDate());
                results.add(result);
            }
            return results;
        } catch (SQLException e) {
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
    public List<Result> getListByMark(int fromMark, int toMark) throws DaoException {
        String sql = "SELECT `id`, `assignment_id`, `mark`, `review`, `date` FROM `results` WHERE `mark` >= ? AND `mark` <= ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, fromMark);
            statement.setInt(2, toMark);
            resultSet = statement.executeQuery();
            List<Result> results = new ArrayList<>();
            while (resultSet.next()) {
                Result result = new Result();
                result.setId(resultSet.getLong("id"));
                result.setAssignment(new Assignment());
                result.getAssignment().setId(resultSet.getLong("assignment_id"));
                result.setMark(resultSet.getInt("mark"));
                result.setReview(resultSet.getString("review"));
                result.setDate(resultSet.getDate("date").toLocalDate());
                results.add(result);
            }
            return results;
        } catch (SQLException e) {
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
    public Long create(Result result) throws DaoException {
        String sql = "INSERT INTO `results` (`assignment_id`, `mark`, `review`, `date`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, result.getAssignment().getId());
            statement.setInt(2, result.getMark());
            statement.setString(3, result.getReview());
            statement.setDate(4, Date.valueOf(result.getDate()));
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
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
    public Result read(Long id) throws DaoException {
        String sql = "SELECT `assignment_id`, `mark`, `review`, `date` FROM `results` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Result result = null;
            if (resultSet.next()) {
                result = new Result();
                result.setId(id);
                result.setAssignment(new Assignment());
                result.getAssignment().setId(resultSet.getLong("assignment_id"));
                result.setMark(resultSet.getInt("mark"));
                result.setReview(resultSet.getString("review"));
                result.setDate(resultSet.getDate("date").toLocalDate());
            }
            return result;
        } catch (SQLException e) {
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
    public void update(Result result) throws DaoException {
        String sql = "UPDATE `results` SET `assignment_id` = ?, `mark` = ?, `review` = ?, `date` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, result.getAssignment().getId());
            statement.setInt(2, result.getMark());
            statement.setString(3, result.getReview());
            statement.setDate(4, Date.valueOf(result.getDate()));
            statement.setLong(5, result.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }
}
