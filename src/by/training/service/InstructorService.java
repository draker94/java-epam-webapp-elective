package by.training.service;

import by.training.domain.Instructor;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public interface InstructorService {
    List<Instructor> findAll() throws ServiceException;

    Instructor findById(Long id) throws ServiceException;

    Long create(Instructor instructor) throws ServiceException;

    void update(Instructor instructor) throws ServiceException;

    void delete(Long id) throws ServiceException;

    Instructor findBySurname(String surname) throws ServiceException;
}
