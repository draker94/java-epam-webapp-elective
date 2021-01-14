package by.training.controller.course;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Course;
import by.training.service.CourseService;
import by.training.service.ServiceException;
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

public class CourseListAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(CourseListAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CourseService courseService = getServiceCreator().getCourseService();
            List<Course> coursesList = courseService.findAll();
            List<Course> freeCourses = courseService.findAllFreeCourses();
            request.setAttribute("coursesList", coursesList);
            request.setAttribute("freeCourses", freeCourses);
            request.getSession().setAttribute("backToCourseList", "/course/list.html");
            return null;
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
    }
}
