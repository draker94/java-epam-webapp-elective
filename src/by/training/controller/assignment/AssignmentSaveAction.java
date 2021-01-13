package by.training.controller.assignment;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Assignment;
import by.training.domain.Course;
import by.training.domain.Student;
import by.training.domain.User;
import by.training.service.AssignmentService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 * If getted the ID of the course enrollment - do update, otherwise - create a new entry.
 */

public class AssignmentSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AssignmentSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isForwardFromStudentList = Boolean.parseBoolean(request.getParameter("isForwardFromStudentList"));
        boolean isForwardFromInstructorList = Boolean.parseBoolean(request.getParameter("isForwardFromInstructorList"));
        LOGGER.error(isForwardFromInstructorList);
        try {
            Long studentId = Long.parseLong(request.getParameter("studentId"));
            Long courseId = Long.parseLong(request.getParameter("courseId"));
            // Don't give the students write down on courses no one except themselves
            if (isForwardFromStudentList) {
                HttpSession httpSession = request.getSession();
                User student = (User) httpSession.getAttribute("sessionUser");
                if (!studentId.equals(student.getId())) {
                    response.sendError(403);
                    return null;
                }
            }
            AssignmentService assignmentService = getServiceCreator().getAssignmentService();
            Long id = null;
            try {
                id = Long.parseLong(request.getParameter("id"));
            } catch (NumberFormatException e) {
                LOGGER.error(e);
                // Is the student already enrolled in this course?
                List<Assignment> assignmentListThisStudent = assignmentService.findByStudent(studentId);
                for (Assignment assignment : assignmentListThisStudent) {
                    if (assignment.getCourse().getId().equals(courseId)) {
                        return new Forward("/assignment/list.html");
                    }
                }
            }
            // Optional fields
            LocalDate beginDate = null;
            LocalDate endDate = null;
            try {
                beginDate = LocalDate.parse(request.getParameter("beginDate"));
                endDate = LocalDate.parse(request.getParameter("endDate"));
            } catch (DateTimeParseException | NullPointerException e) {
                LOGGER.info(e);
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
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        } catch (NumberFormatException e) {
            LOGGER.error(e);
            response.sendError(400, "Student or course parameters isn't valid");
            return null;
        }
        if (isForwardFromStudentList) {
            return new Forward("/assignment/student-list.html");
        }
        return new Forward("/assignment/list.html");
    }
}
