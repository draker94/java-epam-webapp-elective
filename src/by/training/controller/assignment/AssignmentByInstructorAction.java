package by.training.controller.assignment;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Assignment;
import by.training.service.AssignmentService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AssignmentByInstructorAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AssignmentByInstructorAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long instructorId = Long.parseLong(request.getParameter("instructorId"));
            AssignmentService assignmentService = getServiceCreator().getAssignmentService();
            List<Assignment> assignmentList = assignmentService.findInstructorAssignment(instructorId);
            request.setAttribute("assignmentList", assignmentList);
            return new Forward("/assignment/list", false);
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
    }
}
