package by.training.controller.instructor;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Instructor;
import by.training.service.InstructorService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InstructorListAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(InstructorListAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                InstructorService instructorService = getServiceCreator().getInstructorService();
                List<Instructor> instructors = instructorService.findAll();
                List<Instructor> freeInstructors = instructorService.findAllFreeInstructors();
                request.setAttribute("instructors", instructors);
                request.setAttribute("freeInstructors", freeInstructors);
                return null;
            } catch (ServiceCreationException | ServiceException e) {
                LOGGER.error(e);
                throw new ServletException(e);
            }
    }
}
