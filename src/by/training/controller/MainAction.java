package by.training.controller;

import by.training.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                switch (user.getRole()) {
                    case ADMINISTRATOR:
                        return new Forward("/user/list.html");
                    case STUDENT:
                        return new Forward("/account/student.html");
                    case INSTRUCTOR:
                        return new Forward("/account/instructor.html");
                    default:
                        return new Forward("/login.html");
                }
            } else {
                return new Forward("/login.html");
            }
        } else {
            return new Forward("/login.html");
        }
    }
}
