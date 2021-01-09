package by.training.dao;

import by.training.domain.Result;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public interface ResultDao extends Dao<Result, Long> {
    List<Result> getResultsList() throws DaoException;
    List<Result> getListByMark(int fromMark, int toMark) throws DaoException;
    List<Result> getListByAssignment(Long id) throws DaoException;
}
