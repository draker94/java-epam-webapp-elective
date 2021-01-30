package by.training.service.logic;

import by.training.dao.*;
import by.training.domain.Assignment;
import by.training.domain.Result;
import by.training.service.ResultService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class ResultServiceImpl implements ResultService {
    private final static Logger LOGGER = LogManager.getLogger(ResultServiceImpl.class);
    private ResultDao resultDao;
    private AssignmentDao assignmentDao;
    private StudentDao studentDao;
    private CourseDao courseDao;

    public void setResultDao(ResultDao resultDao) {
        this.resultDao = resultDao;
    }

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
    public List<Result> findAll() throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Result> resultList = resultDao.getResultsList();
            for (Result result : resultList) {
                result.setAssignment(assignmentDao.read(result.getAssignment().getId()));
                result.getAssignment().setCourse(courseDao.read(result.getAssignment().getCourse().getId()));
                result.getAssignment().setStudent(studentDao.read(result.getAssignment().getStudent().getId()));
            }
            return resultList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Result> findByMark(int from, int to) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Result> resultListByMark = resultDao.getListByMark(from, to);
            for (Result result : resultListByMark) {
                result.setAssignment(assignmentDao.read(result.getAssignment().getId()));
                result.getAssignment().setCourse(courseDao.read(result.getAssignment().getCourse().getId()));
                result.getAssignment().setStudent(studentDao.read(result.getAssignment().getStudent().getId()));
            }
            return resultListByMark;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Result> findByStudent(Long id) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            List<Assignment> studentAssignment = assignmentDao.getAssignmentsByStudent(id);
            List<Result> resultList = new ArrayList<>();
            for (Assignment assignment : studentAssignment) {
                resultList.addAll(resultDao.getListByAssignment(assignment.getId()));
            }
            for (Result result : resultList) {
                for (Assignment assignment : studentAssignment) {
                    if (Math.toIntExact(result.getAssignment().getId()) == assignment.getId()) {
                        result.setAssignment(assignment);
                    }
                }
                result.getAssignment().setCourse(courseDao.read(result.getAssignment().getCourse().getId()));
                result.getAssignment().setStudent(studentDao.read(result.getAssignment().getStudent().getId()));
            }
            return resultList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Result findById(Long id) throws ServiceException {
        LOGGER.debug("Method entering.");
        try {
            Result result = resultDao.read(id);
            result.setAssignment(assignmentDao.read(result.getAssignment().getId()));
            result.getAssignment().setCourse(courseDao.read(result.getAssignment().getCourse().getId()));
            result.getAssignment().setStudent(studentDao.read(result.getAssignment().getStudent().getId()));
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Result result) throws ServiceException {
        Long id = result.getId();
        LOGGER.debug("Method entering.");
        try {
            if (id != null) {
                LOGGER.info("Updating the result with ID - " + result.getId());
                resultDao.update(result);
            } else {
                LOGGER.info("Creating a new result - " + result);
                id = resultDao.create(result);
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
                resultDao.delete(id, "results");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
