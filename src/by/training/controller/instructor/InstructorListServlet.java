package by.training.controller.instructor;

import by.training.connection.ConnectionPool;
import by.training.di.ServiceCreationException;
import by.training.di.ServiceCreator;
import by.training.domain.Instructor;
import by.training.service.InstructorService;
import by.training.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/instructor/InstructorListServlet.html")
public class InstructorListServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.initConnections("by/training/resources/database", 10, 50, 2);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServiceCreator serviceCreator = new ServiceCreator()) {
            InstructorService instructorService = serviceCreator.getInstructorService();
            List<Instructor> instructors = instructorService.findAll();
            req.setAttribute("instructorList", instructors);
            req.getRequestDispatcher("/instructor/view/InstructorListView.html").forward(req, resp);
        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroy();
    }


}
