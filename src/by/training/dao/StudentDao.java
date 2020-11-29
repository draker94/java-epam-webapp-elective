package by.training.dao;

import by.training.domain.Student;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public interface StudentDao extends Dao<Student, Long> {
    List<Student> getStudentsList() throws DaoException;
    List<Student> getBySurname(String surname) throws DaoException;
}
