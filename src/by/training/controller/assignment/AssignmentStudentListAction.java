package by.training.controller.assignment;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Assignment;
import by.training.domain.User;
import by.training.service.AssignmentService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AssignmentStudentListAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AssignmentListAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AssignmentService assignmentService = getServiceCreator().getAssignmentService();
            HttpSession session = request.getSession();
            User student = (User) session.getAttribute("sessionUser");
            List<Assignment> assignmentList = assignmentService.findByStudent(student.getId());
            request.setAttribute("assignmentList", assignmentList);
            return new Forward("/assignment/studentList", false);
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
    }
}
