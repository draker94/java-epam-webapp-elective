package by.training.dao;

import by.training.domain.Assignment;

import java.time.LocalDate;
import java.util.List;

public interface AssignmentDao extends Dao<Assignment, Long> {
    List<Assignment> getAssignmentsList() throws DaoException;
    List<Assignment> getAssignmentsByStartDate(LocalDate from, LocalDate to) throws DaoException;
    List<Assignment> getAssignmentsByEntDate(LocalDate from, LocalDate to) throws DaoException;
}
