package by.training.di;

import by.training.connection.ConnectionPool;
import by.training.connection.ConnectionPoolException;
import by.training.dao.*;
import by.training.dao.sql.*;
import by.training.service.*;
import by.training.service.logic.*;
import com.mysql.cj.log.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceCreator implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(ServiceCreator.class);

    private Connection connection = null;

    private UserService userService = null;
    private StudentService studentService = null;
    private ResultService resultService = null;
    private InstructorService instructorService = null;
    private CourseService courseService = null;
    private AssignmentService assignmentService = null;

    //local caching
    private UserDao userDao = null;
    private StudentDao studentDao = null;
    private ResultDao resultDao = null;
    private InstructorDao instructorDao = null;
    private CourseDao courseDao = null;
    private AssignmentDao assignmentDao = null;

    public UserService getUserService() throws ServiceCreationException {
        if (userService == null) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            userServiceImpl.setUserDao(getUserDao());
            userService = userServiceImpl;
        }
        return userService;
    }

    private UserDao getUserDao() throws ServiceCreationException {
        if (userDao == null) {
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            userDaoImpl.setConnection(getConnection());
            userDao = userDaoImpl;
        }
        return userDao;
    }

    public StudentService getStudentService() throws ServiceCreationException {
        if (studentService == null) {
            StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
            studentServiceImpl.setStudentDao(getStudentDao());
            studentService = studentServiceImpl;
        }
        return studentService;
    }

    private StudentDao getStudentDao() throws ServiceCreationException {
        if (studentDao == null) {
            StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
            studentDaoImpl.setConnection(getConnection());
            studentDao = studentDaoImpl;
        }
        return studentDao;
    }

    public ResultService getResultService() throws ServiceCreationException {
        if (resultService == null) {
            ResultServiceImpl resultServiceImpl = new ResultServiceImpl();
            resultServiceImpl.setResultDao(getResultDao());
            resultService = resultServiceImpl;
        }
        return resultService;
    }

    private ResultDao getResultDao() throws ServiceCreationException {
        if (resultDao == null) {
            ResultDaoImpl resultDaoImpl = new ResultDaoImpl();
            resultDaoImpl.setConnection(getConnection());
            resultDao = resultDaoImpl;
        }
        return resultDao;
    }

    public InstructorService getInstructorService() throws ServiceCreationException {
        if (instructorService == null) {
            InstructorServiceImpl instructorServiceImpl = new InstructorServiceImpl();
            instructorServiceImpl.setInstructorDao(getInstructorDao());
            instructorService = instructorServiceImpl;
        }
        return instructorService;
    }

    private InstructorDao getInstructorDao() throws ServiceCreationException {
        if (instructorDao == null) {
            InstructorDaoImpl instructorDaoImpl = new InstructorDaoImpl();
            instructorDaoImpl.setConnection(getConnection());
            instructorDao = instructorDaoImpl;
        }
        return instructorDao;
    }

    public CourseService getCourseService() throws ServiceCreationException {
        if (courseService == null) {
            CourseServiceImpl courseServiceImpl = new CourseServiceImpl();
            courseServiceImpl.setCourseDao(getCourseDao());
            courseService = courseServiceImpl;
        }
        return courseService;
    }

    private CourseDao getCourseDao() throws ServiceCreationException {
        if (courseDao == null) {
            CourseDaoImpl courseDaoImpl = new CourseDaoImpl();
            courseDaoImpl.setConnection(getConnection());
            courseDao = courseDaoImpl;
        }
        return courseDao;
    }

    public AssignmentService getAssignmentService() throws ServiceCreationException {
        if (assignmentDao == null) {
            AssignmentServiceImpl assignmentServiceImpl = new AssignmentServiceImpl();
            assignmentServiceImpl.setAssignmentDao(getAssignmentDao());
            assignmentService = assignmentServiceImpl;
        }
        return assignmentService;
    }


    private AssignmentDao getAssignmentDao() throws ServiceCreationException {
        if (assignmentDao == null) {
            AssignmentDaoImpl assignmentDaoImpl = new AssignmentDaoImpl();
            assignmentDaoImpl.setConnection(getConnection());
            assignmentDao = assignmentDaoImpl;
        }
        return assignmentDao;
    }

    private Connection getConnection() throws ServiceCreationException {
        if (connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (ConnectionPoolException e) {
                throw new ServiceCreationException((e));
            }
        }
        return connection;
    }

    @Override
    public void close() throws ServiceCreationException {
        try {
            connection.close();
            LOGGER.debug("Connection is close");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
