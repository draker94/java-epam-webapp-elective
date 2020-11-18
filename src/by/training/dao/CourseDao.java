package by.training.dao;

import by.training.domain.Course;
import by.training.domain.Instructor;
import by.training.domain.Student;
import by.training.domain.User;

import java.util.List;

public interface CourseDao extends Dao<Course, Long> {
    List<Course> getCoursesList() throws DaoException;
    List<Course> getInstructorCoursesList(Long id) throws DaoException;
}
