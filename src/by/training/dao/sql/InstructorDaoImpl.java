package by.training.dao.sql;

import by.training.dao.DaoException;
import by.training.dao.InstructorDao;
import by.training.domain.Instructor;

import java.util.List;

public class InstructorDaoImpl extends BaseDaoImpl implements InstructorDao {
    @Override
    public List<Instructor> getInstructorsList() throws DaoException {
        return null;
    }

    @Override
    public Instructor getBySurname(String login) throws DaoException {
        return null;
    }

    @Override
    public Long create(Instructor entity) throws DaoException {
        return null;
    }

    @Override
    public Instructor read(Long id) throws DaoException {
        return null;
    }

    @Override
    public void update(Instructor entity) throws DaoException {

    }

    @Override
    public void delete(Long id) throws DaoException {

    }
}
