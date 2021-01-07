package by.training.controller.assignment;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Assignment;
import by.training.domain.Course;
import by.training.domain.Student;
import by.training.service.AssignmentService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AssignmentSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AssignmentSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Method entering.");
        Long studentId = Long.parseLong(request.getParameter("studentId"));
        Long courseId = Long.parseLong(request.getParameter("courseId"));
        boolean isRedirectStudentList = Boolean.parseBoolean(request.getParameter("isRedirectStudentList"));
        try {
            AssignmentService assignmentService = getServiceCreator().getAssignmentService();
            if (studentId != null && courseId != null) {
                LOGGER.debug("Method save starts!");
                Long id = null;
                LocalDate beginDate = null;
                LocalDate endDate = null;
                try {
                    id = Long.parseLong(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    LOGGER.error(e);
                }
                try {
                    beginDate = LocalDate.parse(request.getParameter("beginDate"));
                    endDate = LocalDate.parse(request.getParameter("endDate"));
                } catch (NullPointerException e) {
                    LOGGER.error(e);
                }
                Assignment assignment = new Assignment();
                assignment.setId(id);
                Student student = new Student();
                student.setId(studentId);
                assignment.setStudent(student);
                Course course = new Course();
                course.setId(courseId);
                assignment.setCourse(course);
                assignment.setBeginDate(beginDate);
                assignment.setEndDate(endDate);
                assignmentService.save(assignment);
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        if (isRedirectStudentList) {
            return new Forward("/assignment/student-list.html");
        }
        return new Forward("/assignment/list.html");
    }
}
