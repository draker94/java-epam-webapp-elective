package by.training.controller.instructor;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Instructor;
import by.training.domain.User;
import by.training.service.InstructorService;
import by.training.service.ServiceException;
import by.training.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InstructorListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                InstructorService instructorService = getServiceCreator().getInstructorService();
                List<Instructor> instructors = instructorService.findAll();
                List<Instructor> freeInstructors = instructorService.findAllFreeInstructors();
                request.setAttribute("instructors", instructors);
                request.setAttribute("freeInstructors", freeInstructors);
                return null;
            } catch (ServiceCreationException | ServiceException e) {
                throw new ServletException(e);
            }
    }
}
