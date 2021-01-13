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

/**
 * @author Andrey Kliuchnikov
 */

public class StudentSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(StudentSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        try {
            int studyYear = Integer.parseInt(request.getParameter("studyYear"));
            StudentService studentService = getServiceCreator().getStudentService();
            if (name != null && !name.isBlank() && surname != null && !surname.isBlank() && studyYear != 0) {
                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setSurname(surname);
                student.setStudyYear(studyYear);
                if (Boolean.parseBoolean(request.getParameter("isNewStudent"))) {
                    studentService.create(student);
                } else {
                    studentService.update(student);
                }
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        } catch (NumberFormatException e) {
            LOGGER.error(e);
            response.sendError(400, "Study year value isn't valid!");
            return null;
        }
        return new Forward("/student/list.html");
    }
}
