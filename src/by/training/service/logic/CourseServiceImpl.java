package by.training.service.logic;

import by.training.dao.CourseDao;
import by.training.dao.DaoException;
import by.training.dao.InstructorDao;
import by.training.domain.Course;
import by.training.domain.Instructor;
import by.training.service.CourseService;
import by.training.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;
    private InstructorDao instructorDao;

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setInstructorDao(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    @Override
    public List<Course> findAll() throws ServiceException {
        try {
            return courseDao.getCoursesList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    //find courses by instructor's ID or surname (including namesakes)
    @Override
    public List<Course> findByInstructor(String condition) throws ServiceException {
        List<Course> courseListByInstructor = new ArrayList<>();
        try {
            if (condition.matches("\\d++")) {
                courseListByInstructor = courseDao.getInstructorCoursesList(Long.parseLong(condition));
            } else {
                List<Instructor> instructorList = instructorDao.getInstructorsList();
                for (Instructor instructor : instructorList) {
                    if (instructor.getSurname().equals(condition)) {
                        courseListByInstructor.addAll(courseDao.getInstructorCoursesList(instructor.getId()));
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return courseListByInstructor;
    }

    @Override
    public Course findById(Long id) throws ServiceException {
        try {
            return courseDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Course course) throws ServiceException {
        Long id = course.getId();
        try {
            if (id != null) {
                courseDao.update(course);
            } else {
                id = courseDao.create(course);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            courseDao.delete(id, "courses");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
