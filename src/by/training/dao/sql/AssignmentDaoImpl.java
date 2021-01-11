package by.training.dao.sql;

import by.training.dao.AssignmentDao;
import by.training.dao.DaoException;
import by.training.domain.Assignment;
import by.training.domain.Course;
import by.training.domain.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class AssignmentDaoImpl extends BaseDaoImpl implements AssignmentDao {
    private static final Logger LOGGER = LogManager.getLogger(AssignmentDaoImpl.class);

    @Override
    public List<Assignment> getAssignmentsList() throws DaoException {
        String sql = "SELECT `id`, `student_id`, `course_id`, `begin`, `end` FROM `assignments`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Assignment> assignments = new ArrayList<>();
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(resultSet.getLong("id"));
                assignment.setStudent(new Student());
                assignment.getStudent().setId(resultSet.getLong("student_id"));
                assignment.setCourse(new Course());
                assignment.getCourse().setId(resultSet.getLong("course_id"));
                Date begin = resultSet.getDate("begin");
                Date end = resultSet.getDate("end");
                if (begin != null && end != null) {
                    assignment.setBeginDate(begin.toLocalDate());
                    assignment.setEndDate(end.toLocalDate());
                }
                assignments.add(assignment);
            }
            return assignments;
        } catch (SQLException e) {
            LOGGER.error(e);
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
    public List<Assignment> getAssignmentsByStartDate(LocalDate from, LocalDate to) throws DaoException {
        String sql = "SELECT `id`, `student_id`, `course_id`, `begin`, `end` FROM `assignments` WHERE `begin` >= ? AND `begin` <= ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setDate(1, Date.valueOf(from));
            statement.setDate(2, Date.valueOf(to));
            resultSet = statement.executeQuery();
            List<Assignment> assignments = new ArrayList<>();
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(resultSet.getLong("id"));
                assignment.setStudent(new Student());
                assignment.getStudent().setId(resultSet.getLong("student_id"));
                assignment.setCourse(new Course());
                assignment.getCourse().setId(resultSet.getLong("course_id"));
                Date begin = resultSet.getDate("begin");
                Date end = resultSet.getDate("end");
                if (begin != null && end != null) {
                    assignment.setBeginDate(begin.toLocalDate());
                    assignment.setEndDate(end.toLocalDate());
                }
                assignments.add(assignment);
            }
            return assignments;
        } catch (SQLException e) {
            LOGGER.error(e);
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
    public List<Assignment> getAssignmentsByEndDate(LocalDate from, LocalDate to) throws DaoException {
        String sql = "SELECT `id`, `student_id`, `course_id`, `begin`, `end` FROM `assignments` WHERE `end` >= ? AND `end` <= ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setDate(1, Date.valueOf(from));
            statement.setDate(2, Date.valueOf(to));
            resultSet = statement.executeQuery();
            List<Assignment> assignments = new ArrayList<>();
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(resultSet.getLong("id"));
                assignment.setStudent(new Student());
                assignment.getStudent().setId(resultSet.getLong("student_id"));
                assignment.setCourse(new Course());
                assignment.getCourse().setId(resultSet.getLong("course_id"));
                Date begin = resultSet.getDate("begin");
                Date end = resultSet.getDate("end");
                if (begin != null && end != null) {
                    assignment.setBeginDate(begin.toLocalDate());
                    assignment.setEndDate(end.toLocalDate());
                }
                assignments.add(assignment);
            }
            return assignments;
        } catch (SQLException e) {
            LOGGER.error(e);
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
    public List<Assignment> getAssignmentsByStudent(Long studentId) throws DaoException {
        String sql = "SELECT `id`, `course_id`, `begin`, `end` FROM `assignments` WHERE `student_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, studentId);
            resultSet = statement.executeQuery();
            List<Assignment> assignments = new ArrayList<>();
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(resultSet.getLong("id"));
                assignment.setStudent(new Student());
                assignment.getStudent().setId(studentId);
                assignment.setCourse(new Course());
                assignment.getCourse().setId(resultSet.getLong("course_id"));
                Date begin = resultSet.getDate("begin");
                Date end = resultSet.getDate("end");
                if (begin != null && end != null) {
                    assignment.setBeginDate(begin.toLocalDate());
                    assignment.setEndDate(end.toLocalDate());
                }
                assignments.add(assignment);
            }
            return assignments;
        } catch (SQLException e) {
            LOGGER.error(e);
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
    public List<Assignment> getAssignmentsByCourse(Long courseId) throws DaoException {
        String sql = "SELECT `id`, `student_id`, `begin`, `end` FROM `assignments` WHERE `course_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, courseId);
            resultSet = statement.executeQuery();
            List<Assignment> assignments = new ArrayList<>();
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(resultSet.getLong("id"));
                assignment.setStudent(new Student());
                assignment.getStudent().setId(resultSet.getLong("student_id"));
                assignment.setCourse(new Course());
                assignment.getCourse().setId(courseId);
                Date begin = resultSet.getDate("begin");
                Date end = resultSet.getDate("end");
                if (begin != null && end != null) {
                    assignment.setBeginDate(begin.toLocalDate());
                    assignment.setEndDate(end.toLocalDate());
                }
                assignments.add(assignment);
            }
            return assignments;
        } catch (SQLException e) {
            LOGGER.error(e);
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
    public Long create(Assignment assignment) throws DaoException {
        String sql = "INSERT INTO `assignments` (`student_id`, `course_id`, `begin`, `end`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, assignment.getStudent().getId());
            statement.setLong(2, assignment.getCourse().getId());
            LocalDate begin = assignment.getBeginDate();
            LocalDate end = assignment.getEndDate();
            if(begin != null && end != null) {
                statement.setDate(3, Date.valueOf(begin));
                statement.setDate(4, Date.valueOf(end));
            }
            else {
                statement.setNull(3, Types.NULL);
                statement.setNull(4, Types.NULL);
            }
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            LOGGER.error(e);
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
    public Assignment read(Long id) throws DaoException {
        String sql = "SELECT `student_id`, `course_id`, `begin`, `end` FROM `assignments` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Assignment assignment = null;
            if (resultSet.next()) {
                assignment = new Assignment();
                assignment.setId(id);
                assignment.setStudent(new Student());
                assignment.getStudent().setId(resultSet.getLong("student_id"));
                assignment.setCourse(new Course());
                assignment.getCourse().setId(resultSet.getLong("course_id"));
                Date begin = resultSet.getDate("begin");
                Date end = resultSet.getDate("end");
                if (begin != null && end != null) {
                    assignment.setBeginDate(begin.toLocalDate());
                    assignment.setEndDate(end.toLocalDate());
                }
            }
            return assignment;
        } catch (SQLException e) {
            LOGGER.error(e);
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
    public void update(Assignment assignment) throws DaoException {
        String sql = "UPDATE `assignments` SET `student_id` = ?, `course_id` = ?, `begin` = ?, `end` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, assignment.getStudent().getId());
            statement.setLong(2, assignment.getCourse().getId());
            LocalDate begin = assignment.getBeginDate();
            LocalDate end = assignment.getEndDate();
            if(begin != null && end != null) {
                statement.setDate(3, Date.valueOf(begin));
                statement.setDate(4, Date.valueOf(end));
            }
            else {
                statement.setNull(3, Types.NULL);
                statement.setNull(4, Types.NULL);
            }
            statement.setLong(5, assignment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Assignment> getFreeAssignmentsList() throws DaoException {
        String sql = "SELECT `id`, `student_id`, `course_id`, `begin`, `end` FROM `assignments` " +
                "WHERE `id` NOT IN (SELECT `assignment_id` FROM `results`)";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Assignment> assignments = new ArrayList<>();
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(resultSet.getLong("id"));
                assignment.setStudent(new Student());
                assignment.getStudent().setId(resultSet.getLong("student_id"));
                assignment.setCourse(new Course());
                assignment.getCourse().setId(resultSet.getLong("course_id"));
                Date begin = resultSet.getDate("begin");
                Date end = resultSet.getDate("end");
                if (begin != null && end != null) {
                    assignment.setBeginDate(begin.toLocalDate());
                    assignment.setEndDate(end.toLocalDate());
                }
                assignments.add(assignment);
            }
            return assignments;
        } catch (SQLException e) {
            LOGGER.error(e);
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
