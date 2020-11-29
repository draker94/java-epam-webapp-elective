package by.training.service.logic;

import by.training.dao.DaoException;
import by.training.dao.InstructorDao;
import by.training.domain.Instructor;
import by.training.service.InstructorService;
import by.training.service.ServiceException;

import java.util.List;

public class InstructorServiceImpl implements InstructorService {
    private InstructorDao instructorDao;

    public void setInstructorDao(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    @Override
    public List<Instructor> findAll() throws ServiceException {
        try {
            return instructorDao.getInstructorsList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Instructor findById(Long id) throws ServiceException {
        try {
            return instructorDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long create(Instructor instructor) throws ServiceException {
        try {
            return instructorDao.create(instructor);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Instructor instructor) throws ServiceException {
        try {
            instructorDao.update(instructor);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            instructorDao.delete(id, "instructors");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Instructor> findBySurname(String surname) throws ServiceException {
        try {
            return instructorDao.getBySurname(surname);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
