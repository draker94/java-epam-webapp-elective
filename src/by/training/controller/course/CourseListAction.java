package by.training.controller.course;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Course;
import by.training.domain.Instructor;
import by.training.service.CourseService;
import by.training.service.InstructorService;
import by.training.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CourseService courseService = getServiceCreator().getCourseService();
            InstructorService instructorService = getServiceCreator().getInstructorService();
            List<Course> coursesList = courseService.findAll();
            List<Course> freeCourses = courseService.findAllFreeCourses();
            List<Instructor> instructorList = instructorService.findAll();
            Map<Course, Instructor> courseInstructorMap = new HashMap<>();
            for(Course course : coursesList) {
                for(Instructor instructor : instructorList) {
                    if(course.getInstructor().getId().equals(instructor.getId())) {
                        courseInstructorMap.put(course, instructor);
                    }
                }
            }
            request.setAttribute("freeCourses", freeCourses);
            request.setAttribute("courseInstructorMap", courseInstructorMap);
            return null;
        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
