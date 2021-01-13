package by.training.controller.student;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.service.ServiceException;
import by.training.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class StudentDeleteAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(StudentDeleteAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] idStr = request.getParameterValues("id");
        try {
            if (idStr != null) {
                List<Long> idStrArr = new ArrayList<>(idStr.length);
                StudentService studentService = getServiceCreator().getStudentService();
                for (String id : idStr) {
                    idStrArr.add(Long.valueOf(id));
                }
                studentService.delete(idStrArr);
            }
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        return new Forward("/student/list.html");
    }
}
