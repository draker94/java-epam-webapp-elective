package by.training.controller.user;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.User;
import by.training.service.ServiceException;
import by.training.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class UserListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserService userService = getServiceCreator().getUserService();
            List<User> users = userService.findAll();
            request.setAttribute("users", users);
            return null;
        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
