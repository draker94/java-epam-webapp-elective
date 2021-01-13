package by.training.controller.result;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Result;
import by.training.domain.User;
import by.training.service.ResultService;
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
 * The grades of the particular student.
 */

public class ResultByStudentAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(ResultByStudentAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ResultService resultService = getServiceCreator().getResultService();
            HttpSession session = request.getSession();
            User student = (User) session.getAttribute("sessionUser");
            List<Result> resultList = resultService.findByStudent(student.getId());
            request.setAttribute("resultList", resultList);
            return new Forward("/result/list", false);
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
    }
}
