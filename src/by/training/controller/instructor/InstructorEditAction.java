package by.training.controller.instructor;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Instructor;
import by.training.domain.User;
import by.training.enums.Ranks;
import by.training.enums.Roles;
import by.training.service.InstructorService;
import by.training.service.ServiceException;
import by.training.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InstructorEditAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(InstructorEditAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            InstructorService instructorService = getServiceCreator().getInstructorService();
            if (id != null) {
                Instructor instructor = instructorService.findById(Long.parseLong(id));
                if (instructor != null) {
                    request.setAttribute("instructor", instructor);
                } else {
                    throw new IllegalArgumentException("Instructor ID is wrong!");
                }
            }
            else {
                UserService userService = getServiceCreator().getUserService();
                List<User> freeUsersList = userService.findAllFreeUsers();
                freeUsersList.removeIf(user -> user.getRole() == Roles.STUDENT);
                LOGGER.debug("Free Users List cases (for Instructors): ");
                freeUsersList.forEach(LOGGER::debug);
                request.setAttribute("freeUsersList", freeUsersList);
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        catch (IllegalArgumentException e) {
            response.sendError(404, e.getMessage());
        }
        request.setAttribute("ranks", Ranks.values());
        return null;
    }
}
