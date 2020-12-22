package by.training.controller.course;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.controller.instructor.InstructorDeleteAction;
import by.training.di.ServiceCreationException;
import by.training.service.CourseService;
import by.training.service.InstructorService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseDeleteAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(CourseDeleteAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr[] = request.getParameterValues("id");
        List<Long> idStrArr = new ArrayList<>(idStr.length);
        try {
            CourseService courseService = getServiceCreator().getCourseService();
            for (String id : idStr) {
                idStrArr.add(Long.valueOf(id));
            }
            courseService.delete(idStrArr);
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return new Forward("/course/list.html");
    }
}
