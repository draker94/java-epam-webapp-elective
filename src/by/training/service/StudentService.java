package by.training.service;

import by.training.domain.Student;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public interface StudentService {
    List<Student> findAll() throws ServiceException;

    Student findById(Long id) throws ServiceException;

    Long create(Student student) throws ServiceException;

    void update(Student student) throws ServiceException;

    void delete(Long id) throws ServiceException;

    Student findBySurname(String surname) throws ServiceException;
}
