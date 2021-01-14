package by.training.controller.assignment;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Assignment;
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
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 * Getting registrations for courses for a specific instructor.
 */

public class AssignmentByInstructorAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AssignmentByInstructorAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession httpSession = request.getSession();
            User instructor = (User) httpSession.getAttribute("sessionUser");
            AssignmentService assignmentService = getServiceCreator().getAssignmentService();
            List<Assignment> assignmentList = assignmentService.findInstructorAssignment(instructor.getId());
            List<Assignment> freeAssignments = assignmentService.findAllFreeAssignments();
            request.setAttribute("assignmentList", assignmentList);
            request.setAttribute("freeAssignments", freeAssignments);
            request.setAttribute("instructorId", instructor.getId());
            httpSession.setAttribute("backToAssignmentList", "/assignment/instructor-list.html");
            return new Forward("/assignment/list", false);
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
    }
}
