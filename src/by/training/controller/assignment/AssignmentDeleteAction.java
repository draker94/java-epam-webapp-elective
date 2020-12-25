package by.training.controller.assignment;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.controller.course.CourseDeleteAction;
import by.training.di.ServiceCreationException;
import by.training.service.AssignmentService;
import by.training.service.CourseService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDeleteAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AssignmentDeleteAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr[] = request.getParameterValues("id");
        List<Long> idStrArr = new ArrayList<>(idStr.length);
        try {
            AssignmentService assignmentService = getServiceCreator().getAssignmentService();
            for (String id : idStr) {
                idStrArr.add(Long.valueOf(id));
            }
            assignmentService.delete(idStrArr);
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        return new Forward("/assignment/list.html");
    }
}
