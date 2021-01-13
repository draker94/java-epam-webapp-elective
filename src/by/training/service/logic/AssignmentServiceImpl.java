package by.training.service.logic;

import by.training.dao.AssignmentDao;
import by.training.dao.CourseDao;
import by.training.dao.DaoException;
import by.training.dao.StudentDao;
import by.training.domain.Assignment;
import by.training.domain.Course;
import by.training.service.AssignmentService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class AssignmentServiceImpl implements AssignmentService {
    private final static Logger LOGGER = LogManager.getLogger(AssignmentServiceImpl.class);
    private AssignmentDao assignmentDao;
    private StudentDao studentDao;
    private CourseDao courseDao;

    public void setAssignmentDao(AssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Assignment> findAll() throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Assignment> assignmentList = assignmentDao.getAssignmentsList();
            for (Assignment assignment : assignmentList) {
                assignment.setStudent(studentDao.read(assignment.getStudent().getId()));
                assignment.setCourse(courseDao.read(assignment.getCourse().getId()));
            }
            return assignmentList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Assignment> findByStartDate(LocalDate from, LocalDate to) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Assignment> assignmentList = assignmentDao.getAssignmentsByStartDate(from, to);
            for(Assignment assignment : assignmentList) {
                assignment.setStudent(studentDao.read(assignment.getStudent().getId()));
                assignment.setCourse(courseDao.read(assignment.getCourse().getId()));
            }
            return assignmentList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Assignment> findByEndDate(LocalDate from, LocalDate to) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Assignment> assignmentList = assignmentDao.getAssignmentsByEndDate(from, to);
            for(Assignment assignment : assignmentList) {
                assignment.setStudent(studentDao.read(assignment.getStudent().getId()));
                assignment.setCourse(courseDao.read(assignment.getCourse().getId()));
            }
            return assignmentList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Assignment findById(Long id) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            Assignment assignment = assignmentDao.read(id);
            assignment.setStudent(studentDao.read(assignment.getStudent().getId()));
            assignment.setCourse(courseDao.read(assignment.getCourse().getId()));
            return assignment;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Assignment> findByStudent(Long studentId) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Assignment> assignmentList = assignmentDao.getAssignmentsByStudent(studentId);
            for(Assignment assignment : assignmentList) {
                assignment.setStudent(studentDao.read(assignment.getStudent().getId()));
                assignment.setCourse(courseDao.read(assignment.getCourse().getId()));
            }
            return assignmentList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Assignment> findInstructorAssignment(Long instructorId) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Course> instructorCourses = courseDao.getInstructorCoursesList(instructorId);
            List<Assignment> results = new ArrayList<>();
            for(Course course : instructorCourses) {
                results.addAll(assignmentDao.getAssignmentsByCourse(course.getId()));
            }
            for(Assignment assignment : results) {
                assignment.setStudent(studentDao.read(assignment.getStudent().getId()));
                assignment.setCourse(courseDao.read(assignment.getCourse().getId()));
            }
            return results;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Assignment assignment) throws ServiceException {
        Long id = assignment.getId();
        LOGGER.debug("Method entering.");
        try {
            if (id != null) {
                assignmentDao.update(assignment);
            } else {
                id = assignmentDao.create(assignment);
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
            for (Long id : ids) {
                assignmentDao.delete(id, "assignments");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Assignment> findAllFreeAssignments() throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Assignment> freeAssignmentsList = assignmentDao.getFreeAssignmentsList();
            for(Assignment assignment : freeAssignmentsList) {
                assignment.setStudent(studentDao.read(assignment.getStudent().getId()));
                assignment.setCourse(courseDao.read(assignment.getCourse().getId()));
            }
            return freeAssignmentsList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
