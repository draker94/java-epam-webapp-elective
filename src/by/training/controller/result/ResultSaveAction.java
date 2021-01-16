package by.training.controller.result;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Assignment;
import by.training.domain.Result;
import by.training.service.ResultService;
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
 * If getted the ID of the result - do update, otherwise - create a new result.
 */

public class ResultSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(ResultSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String review = request.getParameter("review");
        try {
            Long assignmentId = Long.parseLong(request.getParameter("assignmentId"));
            int mark = Integer.parseInt(request.getParameter("mark"));
            LocalDate date = LocalDate.parse(request.getParameter("markDate"));
            ResultService resultService = getServiceCreator().getResultService();
            if (mark > 0 && mark < 11 && review != null && !review.isBlank()) {
                Long id = null;
                try {
                    id = Long.parseLong(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    LOGGER.error(e);
                    //Is there already a rating for this entry on this date?
                    List<Result> resultList = resultService.findAll();
                    for (Result result : resultList) {
                        if (result.getDate().equals(date) && result.getAssignment().getId().equals(assignmentId)) {
                            request.getSession().setAttribute("message", "result.save.message.error");
                            return new Forward("/result/list.html");
                        }
                    }
                }
                Result result = new Result();
                result.setId(id);
                Assignment assignment = new Assignment();
                assignment.setId(assignmentId);
                result.setAssignment(assignment);
                result.setMark(mark);
                result.setDate(date);
                result.setReview(review);
                resultService.save(result);
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        } catch (DateTimeParseException | NullPointerException e) {
            LOGGER.error(e);
            response.sendError(400, "Date isn't valid!");
            return null;
        }
        catch (NumberFormatException e) {
            LOGGER.error(e);
            response.sendError(400, "Assignment or mark isn't valid");
            return null;
        }
        request.getSession().setAttribute("message", "application.message.success");
        return new Forward("/result/list.html");
    }
}
