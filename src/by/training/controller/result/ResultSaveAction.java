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

public class ResultSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(ResultSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Method entering.");
        Long assignmentId = Long.parseLong(request.getParameter("assignmentId"));
        int mark = Integer.parseInt(request.getParameter("mark"));
        LocalDate date = LocalDate.parse(request.getParameter("markDate"));
        String review = request.getParameter("review");
        try {
            ResultService resultService = getServiceCreator().getResultService();
            if (assignmentId != null && mark != 0 && date != null && review != null && !review.isBlank()) {
                LOGGER.debug("Method save starts!");
                Long id = null;
                try {
                    id = Long.parseLong(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    LOGGER.error(e);
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
        }
        return new Forward("/result/list.html");
    }
}
