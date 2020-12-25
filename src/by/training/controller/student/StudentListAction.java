package by.training.controller.student;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.controller.instructor.InstructorSaveAction;
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
import java.util.List;

public class StudentListAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(StudentListAction.class);


    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            StudentService studentService = getServiceCreator().getStudentService();
            List<Student> students = studentService.findAll();
            List<Student> freeStudents = studentService.findAllFreeStudents();
            request.setAttribute("students", students);
            request.setAttribute("freeStudents", freeStudents);
            return null;
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
    }
}
