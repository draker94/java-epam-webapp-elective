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

public class UserEditAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            try {
                UserService userService = getServiceCreator().getUserService();
                User user = userService.findById(Long.parseLong(id));
                if (user != null) {
                    request.setAttribute("user", user);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (ServiceException | ServiceCreationException e) {
                throw new ServletException(e);
            } catch (IllegalArgumentException e) {
                response.sendError(404);
                return null;
            }
        }
        return null;
    }
}
