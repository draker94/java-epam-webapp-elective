package by.training.controller.instructor;

import by.training.dao.InstructorDao;
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

@WebServlet("/instructor/InstructorAddServlet.html")
public class InstructorAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(ServiceCreator serviceCreator = new ServiceCreator()) {
            InstructorService instructorDao = serviceCreator.getInstructorService();
            instructorDao.create((Instructor) req.getAttribute("instructor"));
            resp.sendRedirect("/instructor/view/InstructorListView.html");
        }
        catch (ServiceCreationException | ServiceException e) {

        }
    }
}
