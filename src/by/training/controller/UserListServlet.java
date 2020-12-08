package by.training.controller;

import by.training.connection.ConnectionPool;
import by.training.connection.ConnectionPoolException;
import by.training.di.ServiceCreationException;
import by.training.di.ServiceCreator;
import by.training.domain.User;
import by.training.service.ServiceException;
import by.training.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/list.html")
public class UserListServlet extends HttpServlet {

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroy();
    }

    @Override
    public void init() throws ServletException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initConnections("by/training/resources/database", 10, 50, 2);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServiceCreator creator = new ServiceCreator()) {
            UserService userService = creator.getUserService();
            List<User> users = userService.findAll();
            req.setAttribute("list", users);
            req.getRequestDispatcher("/view/user/list.xyz").forward(req, resp); //Даём по цепточке наши req и resp
        } catch (ServiceException | ServiceCreationException e) { // только эти эксепшены, посмотреть чё там на уровне ниже
            throw new ServletException(e);
        }
    }
}
