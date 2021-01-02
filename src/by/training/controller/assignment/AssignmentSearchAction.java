package by.training.controller.assignment;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Assignment;
import by.training.domain.Result;
import by.training.service.AssignmentService;
import by.training.service.ResultService;
import by.training.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AssignmentSearchAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate fromDate = LocalDate.parse(request.getParameter("fromDate"));
        LocalDate toDate = LocalDate.parse(request.getParameter("toDate"));
        boolean startDate = Boolean.parseBoolean(request.getParameter("startDate"));
        try {
            AssignmentService assignmentService = getServiceCreator().getAssignmentService();
            List<Assignment> searchResult = null;
            if(startDate) {
                searchResult = assignmentService.findByStartDate(fromDate, toDate);
            }
            else {
                searchResult = assignmentService.findByEndDate(fromDate, toDate);
            }
            request.setAttribute("assignmentList", searchResult);
            return new Forward("/assignment/list", false);
        } catch (ServiceCreationException | ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
