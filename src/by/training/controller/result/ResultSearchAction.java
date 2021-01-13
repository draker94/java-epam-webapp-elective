package by.training.controller.result;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Result;
import by.training.service.ResultService;
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

public class ResultSearchAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(ResultSearchAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int from = Integer.parseInt(request.getParameter("from"));
            int to = Integer.parseInt(request.getParameter("to"));
            ResultService resultService = getServiceCreator().getResultService();
            List<Result> searchResult = resultService.findByMark(from, to);
            request.setAttribute("resultList", searchResult);
            return new Forward("/result/list", false);
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        catch (NumberFormatException e) {
            LOGGER.error(e);
            response.sendError(400, "Search parameters isn't valid");
            return null;
        }
    }
}
