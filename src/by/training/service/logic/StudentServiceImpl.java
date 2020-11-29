package by.training.service.logic;

import by.training.dao.DaoException;
import by.training.dao.StudentDao;
import by.training.domain.Student;
import by.training.service.ServiceException;
import by.training.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> findAll() throws ServiceException {
        try {
            return studentDao.getStudentsList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Student findById(Long id) throws ServiceException {
        try {
            return studentDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long create(Student student) throws ServiceException {
        try {
            return studentDao.create(student);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Student student) throws ServiceException {
        try {
            studentDao.update(student);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            studentDao.delete(id, "students");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Student> findBySurname(String surname) throws ServiceException {
        try {
            return studentDao.getBySurname(surname);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
