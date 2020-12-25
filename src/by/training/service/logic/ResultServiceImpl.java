package by.training.service.logic;

import by.training.dao.AssignmentDao;
import by.training.dao.DaoException;
import by.training.dao.ResultDao;
import by.training.domain.Assignment;
import by.training.domain.Result;
import by.training.service.ResultService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ResultServiceImpl implements ResultService {
    private final static Logger LOGGER = LogManager.getLogger(ResultServiceImpl.class);
    private ResultDao resultDao;
    private AssignmentDao assignmentDao;

    public void setResultDao(ResultDao resultDao) {
        this.resultDao = resultDao;
    }

    public void setAssignmentDao(AssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    @Override
    public List<Result> findAll() throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Result> resultList = resultDao.getResultsList();
            for(Result result : resultList) {
                result.setAssignment(assignmentDao.read(result.getAssignment().getId()));
            }
            return resultList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Result> findByMark(int from, int to) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Result> resultListByMark = resultDao.getListByMark(from, to);
            for(Result result : resultListByMark) {
                result.setAssignment(assignmentDao.read(result.getAssignment().getId()));
            }
            return resultListByMark;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Result findById(Long id) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            Result result = resultDao.read(id);
            result.setAssignment(assignmentDao.read(result.getAssignment().getId()));
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Result result) throws ServiceException {
        Long id = result.getId();
        LOGGER.debug("Method entering.");
        try {
            if (id != null) {
                resultDao.update(result);
            } else {
                id = resultDao.create(result);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            for(Long id : ids) {
                resultDao.delete(id, "results");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
