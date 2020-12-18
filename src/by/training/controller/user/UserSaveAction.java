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

public class UserSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(UserSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");
        Roles role = null;
        try {
            role = Roles.valueOf(request.getParameter("role"));
        } catch (NullPointerException | IllegalArgumentException e) {
        }

        if (login != null && !login.isBlank() && password != null && !password.isBlank() && role != null) {
            Long id = null;
            try {
                id = Long.parseLong(request.getParameter("id"));
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
            User user = new User();
            user.setId(id);
            user.setLogin(login);
            user.setPassword(password);
            user.setMail(mail);
            user.setRole(role);
            try {
                UserService userService = getServiceCreator().getUserService();
                LOGGER.debug(user);
                userService.save(user);
            } catch (ServiceException | ServiceCreationException e) {
                e.printStackTrace();
            }
        }
        return new Forward("/user/list.html");
    }
}
