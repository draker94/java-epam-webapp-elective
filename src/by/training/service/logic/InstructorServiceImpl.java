package by.training.service.logic;

import by.training.dao.DaoException;
import by.training.dao.InstructorDao;
import by.training.domain.Instructor;
import by.training.service.InstructorService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class InstructorServiceImpl implements InstructorService {
    private final static Logger LOGGER = LogManager.getLogger(InstructorServiceImpl.class);
    private InstructorDao instructorDao;

    public void setInstructorDao(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    @Override
    public List<Instructor> findAll() throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return instructorDao.getInstructorsList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Instructor findById(Long id) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return instructorDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long create(Instructor instructor) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return instructorDao.create(instructor);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Instructor instructor) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            instructorDao.update(instructor);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            for(Long id : ids) {
                instructorDao.delete(id, "instructors");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Instructor> findBySurname(String surname) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return instructorDao.getBySurname(surname);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Instructor> findAllFreeInstructors() throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            return instructorDao.getFreeInstructorsList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
