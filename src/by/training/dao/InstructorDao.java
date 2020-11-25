package by.training.dao;

import by.training.domain.Instructor;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public interface InstructorDao extends Dao<Instructor, Long> {
    List<Instructor> getInstructorsList() throws DaoException;
    // Teachers may meet with the same name, so return the List
    List<Instructor> getBySurname(String surname) throws DaoException;
}
