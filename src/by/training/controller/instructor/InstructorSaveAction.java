package by.training.controller.instructor;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Instructor;
import by.training.enums.Ranks;
import by.training.service.InstructorService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InstructorSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(InstructorSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Method entering.");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Long id = Long.parseLong(request.getParameter("id"));
        Ranks rank = null;
        try {
            rank = Ranks.valueOf(request.getParameter("rank"));
        } catch (NullPointerException | IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        try {
            InstructorService instructorService = getServiceCreator().getInstructorService();
            if (name != null && !name.isBlank() && surname != null && !surname.isBlank()) {
                Instructor instructor = new Instructor();
                instructor.setId(id);
                instructor.setName(name);
                instructor.setSurname(surname);
                instructor.setRank(rank);
                if (Boolean.parseBoolean(request.getParameter("isNewInstructor"))) {
                    instructorService.create(instructor);
                } else {
                    instructorService.update(instructor);
                }
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new ServletException(e);
        }
        return new Forward("/instructor/list.html");
    }
}
