package by.training.dao.sql;

import by.training.dao.DaoException;
import by.training.dao.StudentDao;
import by.training.domain.Student;
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

public class StudentDaoImpl extends BaseDaoImpl implements StudentDao {
    private static final Logger LOGGER = LogManager.getLogger(StudentDaoImpl.class);

    @Override
    public List<Student> getStudentsList() throws DaoException {
        String sql = "SELECT `id`, `surname`, `name`, `study_year` FROM `students`";
        Statement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setSurname(resultSet.getString("surname"));
                student.setName(resultSet.getString("name"));
                student.setStudyYear(resultSet.getInt("study_year"));
                students.add(student);
            }
            return students;
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
    public List<Student> getBySurname(String surname) throws DaoException {
        String sql = "SELECT `id`, `surname`, `name`, `study_year` FROM `students` WHERE `surname` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, surname);
            resultSet = statement.executeQuery();
            List<Student> studentList = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setSurname(resultSet.getString("surname"));
                student.setName(resultSet.getString("name"));
                student.setStudyYear(resultSet.getInt("study_year"));
                studentList.add(student);
            }
            return studentList;
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
    public Long create(Student student) throws DaoException {
        String sql = "INSERT INTO `students` (`id`, `surname`, `name`, `study_year`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, student.getId());
            statement.setString(2, student.getSurname());
            statement.setString(3, student.getName());
            statement.setLong(4, student.getStudyYear());
            statement.executeUpdate();
            return student.getId();
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
    public Student read(Long id) throws DaoException {
        String sql = "SELECT `id`, `surname`, `name`, `study_year` FROM `students` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Student student = null;
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setSurname(resultSet.getString("surname"));
                student.setName(resultSet.getString("name"));
                student.setStudyYear(resultSet.getInt("study_year"));
            }
            return student;
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
    public void update(Student student) throws DaoException {
        String sql = "UPDATE `students` SET `surname` = ?, `name` = ?, `study_year` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, student.getSurname());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getStudyYear());
            statement.setLong(4, student.getId());
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

    @Override
    public List<Student> getFreeStudentsList() throws DaoException {
        String sql = "SELECT `id`, `surname`, `name`, `study_year` FROM `students`" +
                "WHERE `id` NOT IN (SELECT `student_id` FROM `assignments`)";
        Statement statement = null;
        ResultSet resultSet = null;
        LOGGER.debug("Method entering.");
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setSurname(resultSet.getString("surname"));
                student.setName(resultSet.getString("name"));
                student.setStudyYear(resultSet.getInt("study_year"));
                students.add(student);
            }
            return students;
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
}
