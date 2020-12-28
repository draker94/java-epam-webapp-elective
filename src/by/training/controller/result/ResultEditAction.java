package by.training.controller.result;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.controller.course.CourseEditAction;
import by.training.di.ServiceCreationException;
import by.training.domain.Assignment;
import by.training.domain.Course;
import by.training.domain.Instructor;
import by.training.domain.Result;
import by.training.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResultEditAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(ResultEditAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            AssignmentService assignmentService = getServiceCreator().getAssignmentService();
            List<Assignment> assignmentList = assignmentService.findAll();
            request.setAttribute("assignmentList", assignmentList);
            if (id != null) {
                ResultService resultService = getServiceCreator().getResultService();
                Result result = resultService.findById(Long.parseLong(id));
                if (result != null) {
                    request.setAttribute("result", result);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e);
            response.sendError(404);
            return null;
        }
        return null;
    }
}
