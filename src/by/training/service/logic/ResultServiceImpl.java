package by.training.service.logic;

import by.training.dao.DaoException;
import by.training.dao.ResultDao;
import by.training.domain.Result;
import by.training.service.ResultService;
import by.training.service.ServiceException;

import java.util.List;

public class ResultServiceImpl implements ResultService {
    private ResultDao resultDao;

    public void setResultDao(ResultDao resultDao) {
        this.resultDao = resultDao;
    }

    @Override
    public List<Result> findAll() throws ServiceException {
        try {
            return resultDao.getResultsList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Result> findByMark(int from, int to) throws ServiceException {
        try {
            return resultDao.getListByMark(from, to);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Result findById(Long id) throws ServiceException {
        try {
            return resultDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Result result) throws ServiceException {
        Long id = result.getId();
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
    public void delete(Long id) throws ServiceException {
        try {
            resultDao.delete(id, "results");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
