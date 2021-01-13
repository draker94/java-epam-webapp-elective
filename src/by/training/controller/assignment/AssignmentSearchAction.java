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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 * Search by date (start OR end).
 */

public class AssignmentSearchAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AssignmentSearchAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean startDate = Boolean.parseBoolean(request.getParameter("startDate"));
        try {
            LocalDate fromDate = LocalDate.parse(request.getParameter("fromDate"));
            LocalDate toDate = LocalDate.parse(request.getParameter("toDate"));
            AssignmentService assignmentService = getServiceCreator().getAssignmentService();
            List<Assignment> searchResult;
            if (startDate) {
                searchResult = assignmentService.findByStartDate(fromDate, toDate);
            } else {
                searchResult = assignmentService.findByEndDate(fromDate, toDate);
            }
            request.setAttribute("assignmentList", searchResult);
            return new Forward("/assignment/list", false);
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        } catch (DateTimeParseException | NullPointerException e) {
            LOGGER.error(e);
            response.sendError(400, "Date isn't valid!");
            return null;
        }
    }
}
