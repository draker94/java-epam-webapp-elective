package by.training.controller.main;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.controller.instructor.InstructorSearchAction;
import by.training.di.ServiceCreationException;
import by.training.domain.User;
import by.training.service.ServiceException;
import by.training.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LoginAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(LoginAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            UserService service = getServiceCreator().getUserService();
            if (login != null && password != null) {
                User user = service.findByLoginAndPass(login, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("sessionUser", user);
                    return new Forward("/index.html");
                } else {
                    return new Forward("/main/login.html?message=main.login.error.incorrect");
                }
            } else {
                return null;
            }
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
    }
}
