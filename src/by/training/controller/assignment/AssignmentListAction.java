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

/**
 * @author Andrey Kliuchnikov
 */

public class AssignmentListAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AssignmentListAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AssignmentService assignmentService = getServiceCreator().getAssignmentService();
            List<Assignment> assignmentList = assignmentService.findAll();
            List<Assignment> freeAssignments = assignmentService.findAllFreeAssignments();
            request.setAttribute("assignmentList", assignmentList);
            request.setAttribute("freeAssignments", freeAssignments);
            request.getSession().setAttribute("backToAssignmentList", "/assignment/list.html");
            return null;
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
    }
}
