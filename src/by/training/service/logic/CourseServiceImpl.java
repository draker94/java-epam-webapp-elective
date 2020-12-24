package by.training.service.logic;

import by.training.dao.CourseDao;
import by.training.dao.DaoException;
import by.training.dao.InstructorDao;
import by.training.domain.Course;
import by.training.domain.Instructor;
import by.training.service.CourseService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private final static Logger LOGGER = LogManager.getLogger(CourseServiceImpl.class);
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
        LOGGER.debug("Method entering.");
        try {
            List<Course> courseList = courseDao.getCoursesList();
            List<Instructor> instructorsList = instructorDao.getInstructorsList();
            for(Course course : courseList) {
                for(Instructor instructor : instructorsList) {
                    if(course.getInstructor().getId().equals(instructor.getId())) {
                        course.setInstructor(instructor);
                    }
                }
            }
            return courseList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    //find courses by instructor's ID or surname (including namesakes)
    @Override
    public List<Course> findByInstructor(String condition) throws ServiceException {
        List<Course> courseListByInstructor = new ArrayList<>();
        LOGGER.debug("Method entering.");
        try {
            if (condition.matches("\\d++")) {
                Long id = Long.parseLong(condition);
                courseListByInstructor = courseDao.getInstructorCoursesList(id);
                for(Course course : courseListByInstructor) {
                    course.setInstructor(instructorDao.read(id));
                }
            } else {
                List<Instructor> instructorList = instructorDao.getInstructorsList();
                for (Instructor instructor : instructorList) {
                    if (instructor.getSurname().equals(condition)) {
                        courseListByInstructor.addAll(courseDao.getInstructorCoursesList(instructor.getId()));
                        courseListByInstructor.forEach(course -> course.setInstructor(instructor));
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
        LOGGER.debug("Method entering.");
        try {
            Course course = courseDao.read(id);
            if(course != null) {
                Instructor instructor = instructorDao.read(course.getInstructor().getId());
                course.setInstructor(instructor);
            }
            return course;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Course course) throws ServiceException {
        Long id = course.getId();
        LOGGER.debug("Method entering.");
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
    public void delete(List<Long> ids) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            for(Long id : ids) {
                courseDao.delete(id, "courses");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> findAllFreeCourses() throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Course> freeCoursesList = courseDao.getFreeCoursesList();
            List<Instructor> instructorsList = instructorDao.getInstructorsList();
            for(Course course : freeCoursesList) {
                for(Instructor instructor : instructorsList) {
                    if(course.getInstructor().getId().equals(instructor.getId())) {
                        course.setInstructor(instructor);
                    }
                }
            }
            return freeCoursesList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
