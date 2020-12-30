package by.training.controller.main;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainPageAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("sessionUser");
            if (user != null) {
                switch (user.getRole()) {
                    case ADMINISTRATOR:
                        return new Forward("/user/list.html");
                    case STUDENT:
                        return new Forward("/student/list.html");
                    case INSTRUCTOR:
                        return new Forward("/instructor/list.html");
                    default:
                        return new Forward("/main/login.html");
                }
            } else {
                return new Forward("/main/login.html");
            }
        } else {
            return new Forward("/main/login.html");
        }
    }
}
