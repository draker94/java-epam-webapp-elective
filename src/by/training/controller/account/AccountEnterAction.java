package by.training.controller.account;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Instructor;
import by.training.domain.Student;
import by.training.domain.User;
import by.training.enums.Roles;
import by.training.service.InstructorService;
import by.training.service.ServiceException;
import by.training.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Andrey Kliuchnikov
 */

public class AccountEnterAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            StudentService studentService = getServiceCreator().getStudentService();
            InstructorService instructorService = getServiceCreator().getInstructorService();
            HttpSession session = request.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("sessionUser");
                if (user != null) {
                    String surnameName = null;
                    if (user.getRole() == Roles.STUDENT) {
                        Student student = studentService.findById(user.getId());
                        surnameName = String.format("%s %s", student.getSurname(), student.getName());
                    }
                    else if (user.getRole() == Roles.INSTRUCTOR || user.getRole() == Roles.ADMINISTRATOR) {
                        Instructor instructor = instructorService.findById(user.getId());
                        surnameName = String.format("%s %s", instructor.getSurname(), instructor.getName());
                    }
                    request.setAttribute("surnameName", surnameName);
                    return null;
                }
            }
        } catch (ServiceCreationException | ServiceException e) {
            e.printStackTrace();
        }
        return new Forward("/main/login.html");
    }
}
