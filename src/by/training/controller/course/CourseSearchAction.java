package by.training.controller.course;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Course;
import by.training.domain.Student;
import by.training.service.CourseService;
import by.training.service.ServiceException;
import by.training.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CourseSearchAction extends Action {
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String condition = request.getParameter("condition");
        try {
            CourseService courseService = getServiceCreator().getCourseService();
            if (condition != null && !condition.isBlank()) {
                List<Course> searchResult = courseService.findByInstructor(condition);
                request.setAttribute("coursesList", searchResult);
                return new Forward("/course/list", false);
            }
        } catch (ServiceCreationException | ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
