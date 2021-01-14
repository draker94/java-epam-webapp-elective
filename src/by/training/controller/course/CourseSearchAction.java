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
 * Search courses by instructor surname or ID.
 */

public class CourseSearchAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(CourseSearchAction.class);

    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String condition = request.getParameter("condition");
        try {
            CourseService courseService = getServiceCreator().getCourseService();
            if (condition != null && !condition.isBlank()) {
                List<Course> searchResult = courseService.findByInstructor(condition);
                List<Course> freeCourses = courseService.findAllFreeCourses();
                request.setAttribute("coursesList", searchResult);
                request.setAttribute("freeCourses", freeCourses);
                request.getSession().setAttribute("backToCourseList", String.format("/course/search.html?condition=%s", condition));
                return new Forward("/course/list", false);
            }
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
