package by.training.service;

import by.training.domain.Assignment;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public interface AssignmentService {
    List<Assignment> findAll() throws ServiceException;

    List<Assignment> findByStartDate(LocalDate from, LocalDate to) throws ServiceException;

    List<Assignment> findByEndDate(LocalDate from, LocalDate to) throws ServiceException;

    Assignment findById(Long id) throws ServiceException;

    Long save(Assignment assignment) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
