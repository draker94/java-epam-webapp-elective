package by.training.service.logic;

import by.training.dao.DaoException;
import by.training.dao.StudentDao;
import by.training.domain.Student;
import by.training.service.ServiceException;
import by.training.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final static Logger LOGGER = LogManager.getLogger(StudentServiceImpl.class);
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> findAll() throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return studentDao.getStudentsList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Student findById(Long id) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return studentDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long create(Student student) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return studentDao.create(student);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Student student) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            studentDao.update(student);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            for(Long id : ids) {
                studentDao.delete(id, "students");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Student> findBySurname(String surname) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return studentDao.getBySurname(surname);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Student> findAllFreeStudents() throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return studentDao.getFreeStudentsList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
