package by.training.controller.course;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Course;
import by.training.domain.Instructor;
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
 * If getted the ID of the course - do update, otherwise - create a new course.
 */

public class CourseSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(CourseSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        try {
            Long instructorId = Long.parseLong(request.getParameter("instructorId"));
            int hours = Integer.parseInt(request.getParameter("hours"));
            CourseService courseService = getServiceCreator().getCourseService();
            if (name != null && !name.isBlank() && description != null && !description.isBlank()) {
                Long id = null;
                try {
                    id = Long.parseLong(request.getParameter("id"));
                    LOGGER.debug("This is edit method!");
                } catch (NumberFormatException e) {
                    LOGGER.debug("This is create method!");
                }
                //The course with the same name already exists?
                List<Course> courseList = courseService.findAll();
                for (Course course : courseList) {
                    if (!course.getId().equals(id) && course.getName().equals(name)) {
                        request.getSession().setAttribute("message", "course.save.message.error");
                        return new Forward(request.getSession().getAttribute("backToCourseList").toString());
                    }
                }
                Course course = new Course();
                course.setId(id);
                course.setName(name);
                Instructor instructor = new Instructor();
                instructor.setId(instructorId);
                course.setInstructor(instructor);
                course.setHours(hours);
                course.setDescription(description);
                courseService.save(course);
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        } catch (NumberFormatException e) {
            LOGGER.error(e);
            response.sendError(400, "Instructor ID or hour format isn't valid!");
            return null;
        }
        request.getSession().setAttribute("message", "application.message.success");
        return new Forward(request.getSession().getAttribute("backToCourseList").toString());
    }
}
