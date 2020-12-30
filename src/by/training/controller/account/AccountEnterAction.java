package by.training.controller.account;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountEnterAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AccountEnterAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("sessionUser");
            if (user != null) {
                return null;
            }
        }
        return new Forward("/main/login.html");
    }
}
