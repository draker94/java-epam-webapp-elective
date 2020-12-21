package by.training.dao;

import by.training.domain.Course;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public interface CourseDao extends Dao<Course, Long> {
    List<Course> getCoursesList() throws DaoException;

    List<Course> getInstructorCoursesList(Long id) throws DaoException;

    List<Course> getFreeCoursesList() throws DaoException;

}
