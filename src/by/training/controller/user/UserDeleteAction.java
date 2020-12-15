package by.training.controller.user;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.service.ServiceException;
import by.training.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try {
                UserService service = getServiceCreator().getUserService();
                service.delete(id);
            } catch(ServiceCreationException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/user/list.html");
    }
}
