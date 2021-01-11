package by.training.controller.student;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Student;
import by.training.service.ServiceException;
import by.training.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentSearchAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(StudentSearchAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String surnameForSearch = request.getParameter("surnameForSearch");
        try {
            StudentService studentService = getServiceCreator().getStudentService();
            if (surnameForSearch != null && !surnameForSearch.isBlank()) {
                List<Student> searchResult = studentService.findBySurname(surnameForSearch);
                request.setAttribute("students", searchResult);
                return new Forward("/student/list", false);
            }
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
