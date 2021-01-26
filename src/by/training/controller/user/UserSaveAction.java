package by.training.controller.user;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.User;
import by.training.enums.Roles;
import by.training.service.ServiceException;
import by.training.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Andrey Kliuchnikov
 * If getted the ID of the user - do update, otherwise - create a new user.
 */

public class UserSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(UserSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            Roles role = Roles.valueOf(request.getParameter("role"));
            UserService userService = getServiceCreator().getUserService();
            if (login != null && !login.isBlank() && password != null && !password.isBlank()) {
                Long id = null;
                try {
                    id = Long.parseLong(request.getParameter("id"));
                    LOGGER.debug("This is edit method!");
                } catch (NumberFormatException e) {
                    LOGGER.debug("This is create method!");
                }
                //Does a user with this login already exist?
                User userFromDb = userService.findByLogin(login);
                if (userFromDb != null && !userFromDb.getId().equals(id)) {
                    request.getSession().setAttribute("message", "user.save.error.exist");
                    return new Forward("/user/list.html");
                }
                User user = new User();
                user.setId(id);
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(role);
                userService.save(user);
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        } catch (NullPointerException | IllegalArgumentException e) {
            LOGGER.error(e);
            response.sendError(400, "Role isn't valid!");
            return null;
        }
        request.getSession().setAttribute("message", "application.message.success");
        return new Forward("/user/list.html");
    }
}
