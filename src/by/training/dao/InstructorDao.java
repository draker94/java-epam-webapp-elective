package by.training.dao;

import by.training.domain.Instructor;
import by.training.domain.User;

import java.util.List;

public interface InstructorDao extends Dao<Instructor, Long> {
    List<Instructor> getInstructorsList() throws DaoException;
    Instructor getBySurname(String surname) throws DaoException;
}
