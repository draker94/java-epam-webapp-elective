package by.training.service.logic;

import by.training.dao.AssignmentDao;
import by.training.dao.DaoException;
import by.training.domain.Assignment;
import by.training.service.AssignmentService;
import by.training.service.ServiceException;

import java.time.LocalDate;
import java.util.List;

public class AssignmentServiceImpl implements AssignmentService {
    private AssignmentDao assignmentDao;

    public void setAssignmentDao(AssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    @Override
    public List<Assignment> findAll() throws ServiceException {
        try {
            return assignmentDao.getAssignmentsList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Assignment> findByStartDate(LocalDate from, LocalDate to) throws ServiceException {
        try {
            return assignmentDao.getAssignmentsByStartDate(from, to);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Assignment> findByEndDate(LocalDate from, LocalDate to) throws ServiceException {
        try {
            return assignmentDao.getAssignmentsByEndDate(from, to);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Assignment findById(Long id) throws ServiceException {
        try {
            return assignmentDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Assignment assignment) throws ServiceException {
        Long id = assignment.getId();
        try {
            if(id != null) {
                assignmentDao.update(assignment);
            }
            else {
                id = assignmentDao.create(assignment);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            assignmentDao.delete(id, "assignments");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
