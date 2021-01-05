package by.training.controller.assignment;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Assignment;
import by.training.domain.Course;
import by.training.domain.Student;
import by.training.service.AssignmentService;
import by.training.service.CourseService;
import by.training.service.ServiceException;
import by.training.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AssignmentEnrollAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AssignmentEnrollAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long studentId = Long.valueOf(request.getParameter("studentId"));
        try {
            CourseService courseService = getServiceCreator().getCourseService();
            StudentService studentService = getServiceCreator().getStudentService();
            AssignmentService assignmentService = getServiceCreator().getAssignmentService();
            List<Course> courseList = courseService.findAll();
            Student student = studentService.findById(studentId);
            List<Assignment> studentAssignment = assignmentService.findByStudent(studentId);
            request.setAttribute("courseList", courseList);
            request.setAttribute("student", student);
            request.setAttribute("studentAssignment", studentAssignment);
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e);
            response.sendError(404);
            return null;
        }
        return null;
    }
}
