package by.training.service;

import by.training.domain.Course;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public interface CourseService {
    List<Course> findAll() throws ServiceException;

    List<Course> findByInstructor(String condition) throws ServiceException;

    Course findById(Long id) throws ServiceException;

    Long save(Course course) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
