package by.training.controller.instructor.view;

import by.training.domain.Instructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/instructor/view/InstructorListView.html")
public class InstructorListView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h1>Instructor List</h1>");
        List<Instructor> instructors = (List<Instructor>) req.getAttribute("instructorList");
        printWriter.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head>\n" +
                "  <meta charset=\"utf-8\">");
        for (Instructor instructor : instructors) {
            printWriter.println("<p>" + instructor.getSurname() + "</p>");
        }
        //printWriter.println("<p><a href=\"${pageContext.request.contextPath}/instructor/view/InstructorAddView.html\">Add New Instructor</a></p>");
        printWriter.println(" </head>\n" +
                "</html>");
    }
}
