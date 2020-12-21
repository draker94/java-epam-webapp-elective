package by.training.controller.user;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.User;
import by.training.service.ServiceException;
import by.training.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class UserListAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(UserListAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Method entering.");
        try {
            UserService userService = getServiceCreator().getUserService();
            List<User> users = userService.findAll();
            List<User> freeUsers = userService.findAllFreeUsers();
            request.setAttribute("users", users);
            request.setAttribute("freeUsers", freeUsers);
            return null;
        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
