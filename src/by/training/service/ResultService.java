package by.training.service;

import by.training.domain.Result;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public interface ResultService {
    List<Result> findAll() throws ServiceException;

    List<Result> findByMark(int from, int to) throws ServiceException;

    Result findById(Long id) throws ServiceException;

    Long save(Result result) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;
}
