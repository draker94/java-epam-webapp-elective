package by.training.controller.instructor;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Instructor;
import by.training.domain.Student;
import by.training.service.InstructorService;
import by.training.service.ServiceException;
import by.training.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InstructorSearchAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String surnameForSearch = request.getParameter("surnameForSearch");
        try {
            InstructorService instructorService = getServiceCreator().getInstructorService();
            if (surnameForSearch != null && !surnameForSearch.isBlank()) {
                List<Instructor> searchResult = instructorService.findBySurname(surnameForSearch);
                request.setAttribute("instructors", searchResult);
                return new Forward("/instructor/list", false);
            }
        } catch (ServiceCreationException | ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
