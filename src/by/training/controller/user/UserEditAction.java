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
 */

public class UserEditAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(UserEditAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            UserService userService = getServiceCreator().getUserService();
            if (id != null) {
                User user = userService.findById(Long.parseLong(id));
                if (user != null) {
                    request.setAttribute("user", user);
                } else {
                    throw new IllegalArgumentException("User ID is wrong!");
                }
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        catch (IllegalArgumentException e) {
            response.sendError(404, e.getMessage());
        }
        request.setAttribute("roles", Roles.values());
        return null;
    }
}
