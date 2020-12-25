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

public class CourseSaveAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(CourseSaveAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Method entering.");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Long instructorId = Long.parseLong(request.getParameter("instructorId"));
        int hours = Integer.parseInt(request.getParameter("hours"));
        try {
            CourseService courseService = getServiceCreator().getCourseService();
            if (name != null && !name.isBlank() && description != null && !description.isBlank()) {
                Long id = null;
                try {
                    id = Long.parseLong(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    LOGGER.error(e.getLocalizedMessage());
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
        }
        return new Forward("/course/list.html");
    }
}
