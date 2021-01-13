package by.training.controller.student;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Student;
import by.training.domain.User;
import by.training.enums.Roles;
import by.training.service.ServiceException;
import by.training.service.StudentService;
import by.training.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class StudentEditAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(StudentEditAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            StudentService studentService = getServiceCreator().getStudentService();
            if (id != null) {
                Student student = studentService.findById(Long.parseLong(id));
                if (student != null) {
                    request.setAttribute("student", student);
                } else {
                    throw new IllegalArgumentException("Student ID is wrong!");
                }
            } else {
                UserService userService = getServiceCreator().getUserService();
                List<User> freeUsersList = userService.findAllFreeUsers();
                freeUsersList.removeIf(user -> user.getRole() == Roles.ADMINISTRATOR || user.getRole() == Roles.INSTRUCTOR);
                LOGGER.debug("Free Users List cases (for Students): ");
                freeUsersList.forEach(LOGGER::debug);
                request.setAttribute("freeUsersList", freeUsersList);
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        catch (IllegalArgumentException e) {
            response.sendError(404, e.getMessage());
        }
        return null;
    }
}
