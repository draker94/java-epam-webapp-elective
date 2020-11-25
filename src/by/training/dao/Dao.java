package by.training.dao;

import by.training.domain.Entity;

/**
 * @author Andrey Kliuchnikov
 */

public interface Dao<T extends Entity, PK> {
    Long create(T entity) throws DaoException;
    T read(PK id) throws DaoException;
    void update(T entity) throws DaoException;
}
