package by.training.controller.result;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Result;
import by.training.domain.Student;
import by.training.service.ResultService;
import by.training.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResultSearchAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int from = Integer.parseInt(request.getParameter("from"));
        int to = Integer.parseInt(request.getParameter("to"));
        try {
            ResultService resultService = getServiceCreator().getResultService();
            List<Result> searchResult = resultService.findByMark(from, to);
            request.setAttribute("resultList", searchResult);
            return new Forward("/result/list", false);
        } catch (ServiceCreationException | ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
