package by.training.dao.sql;

import by.training.dao.CourseDao;
import by.training.dao.DaoException;
import by.training.domain.Course;
import by.training.domain.Instructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class CourseDaoImpl extends BaseDaoImpl implements CourseDao {
    @Override
    public List<Course> getCoursesList() throws DaoException {
        String sql = "SELECT `id`, `name`, `hours`, `description`, `instructor_id` FROM `courses`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getLong("id"));
                course.setName(resultSet.getString("name"));
                course.setHours(resultSet.getInt("hours"));
                course.setDescription(resultSet.getString("description"));
                course.setInstructor(new Instructor());
                course.getInstructor().setId(resultSet.getLong("instructor_id"));
                courses.add(course);
            }
            return courses;
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
    public List<Course> getInstructorCoursesList(Long id) throws DaoException {
        String sql = "SELECT `id`, `name`, `hours`, `description` FROM `courses` WHERE `instructor_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getLong("id"));
                course.setName(resultSet.getString("name"));
                course.setHours(resultSet.getInt("hours"));
                course.setDescription(resultSet.getString("description"));
                course.setInstructor(new Instructor());
                course.getInstructor().setId(id);
                courses.add(course);
            }
            return courses;
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
    public Long create(Course course) throws DaoException {
        String sql = "INSERT INTO `courses` (`name`, `hours`, `description`, `instructor_id`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, course.getName());
            statement.setInt(2, course.getHours());
            statement.setString(3, course.getDescription());
            statement.setLong(4, course.getInstructor().getId());
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
    public Course read(Long id) throws DaoException {
        String sql = "SELECT `name`, `hours`, `description`, `instructor_id` FROM `courses` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Course course = null;
            if (resultSet.next()) {
                course = new Course();
                course.setId(id);
                course.setName(resultSet.getString("name"));
                course.setHours(resultSet.getInt("hours"));
                course.setDescription(resultSet.getString("description"));
                course.setInstructor(new Instructor());
                course.getInstructor().setId(resultSet.getLong("instructor_id"));
            }
            return course;
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
    public void update(Course course) throws DaoException {
        String sql = "UPDATE `courses` SET `name` = ?, `hours` = ?, `description` = ?, `instructor_id` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, course.getName());
            statement.setInt(2, course.getHours());
            statement.setString(3, course.getDescription());
            statement.setLong(4, course.getInstructor().getId());
            statement.setLong(5, course.getId());
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
