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
        Ranks rank = null;
        try {
            rank = Ranks.valueOf(request.getParameter("rank"));
        } catch (NullPointerException | IllegalArgumentException e) {
        }
        if (name != null && !name.isBlank() && surname != null && !surname.isBlank() && rank != null) {
            Long id = null;
            try {
                id = Long.parseLong(request.getParameter("id"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Instructor instructor = new Instructor();
            instructor.setId(id);
            instructor.setName(name);
            instructor.setSurname(surname);
            instructor.setRank(rank);
            LOGGER.debug(instructor);
            try {
                InstructorService instructorService = getServiceCreator().getInstructorService();
                LOGGER.debug(request.getParameter("isNewInstructor"));
                if (Boolean.parseBoolean(request.getParameter("isNewInstructor"))) {
                    instructorService.create(instructor);
                } else {
                    instructorService.update(instructor);
                }
            } catch (ServiceException | ServiceCreationException e) {
                e.printStackTrace();
            }
        }
        return new Forward("/instructor/list.html");
    }
}
