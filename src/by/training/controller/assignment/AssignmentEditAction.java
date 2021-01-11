package by.training.controller.assignment;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Assignment;
import by.training.domain.Course;
import by.training.domain.Student;
import by.training.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AssignmentEditAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AssignmentEditAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            StudentService studentService = getServiceCreator().getStudentService();
            CourseService courseService = getServiceCreator().getCourseService();
            List<Student> studentList = studentService.findAll();
            List<Course> courseList = courseService.findAll();
            request.setAttribute("studentList", studentList);
            request.setAttribute("courseList", courseList);
            if (id != null) {
                AssignmentService assignmentService = getServiceCreator().getAssignmentService();
                Assignment assignment = assignmentService.findById(Long.parseLong(id));
                LOGGER.debug(assignment);
                if (assignment != null) {
                    request.setAttribute("assignment", assignment);
                } else {
                    throw new IllegalArgumentException("Assignment is wrong!");
                }
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        catch (IllegalArgumentException e) {
            response.sendError(404, e.getMessage());
        }
        return null;
    }
}
