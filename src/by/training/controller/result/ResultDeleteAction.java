package by.training.controller.result;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.controller.instructor.InstructorDeleteAction;
import by.training.di.ServiceCreationException;
import by.training.domain.Result;
import by.training.service.InstructorService;
import by.training.service.ResultService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultDeleteAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(ResultDeleteAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] idStr = request.getParameterValues("id");
        List<Long> idStrArr = new ArrayList<>(idStr.length);
        try {
            ResultService resultService = getServiceCreator().getResultService();
            for (String id : idStr) {
                idStrArr.add(Long.valueOf(id));
            }
            resultService.delete(idStrArr);
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new ServletException(e);
        }
        return new Forward("/result/list.html");
    }
}
