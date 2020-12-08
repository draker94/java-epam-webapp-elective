package by.training.controller.instructor.view;

import by.training.domain.Instructor;
import by.training.enums.Ranks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/instructor/view/InstructorAddView.html")
public class InstructorAddView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.write("<html> <head> <title>Add Instructor</title></head><meta charset=\"utf-8\">\"\n" +
                "<body><form method=\"post\">\n" +
                "    Rank: <select name=\"rank\">\n" +
                "    <option>Профессор</option>\n" +
                "    <option>Доцент</option>\n" +
                "    <option>Старший лектор</option>\n" +
                "    <option>Лектор</option>\n" +
                "    <option>Другое</option>\n" +
                "</select>\n" +
                "    <br><br>\n" +
                "    <label>Фамилия:\n" +
                "        <input type=\"text\" name=\"surname\"><br/>\n" +
                "    </label>\n" +
                "    <br><br>\n" +
                "    <label>Имя:\n" +
                "        <input type=\"text\" name=\"name\"><br/>\n" +
                "    </label>\n" +
                "    <br><br>\n" +
                "    <label>ID:\n" +
                "        <input type=\"text\" name=\"id\"><br/>\n" +
                "    </label>\n" +
                "    <br><br>\n" +
                "    <button type=\"submit\">Submit</button>\n" +
                "</form> </body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger logger = LogManager.getLogger(InstructorAddView.class);
        req.setCharacterEncoding("UTF-8");
        logger.info(req.getCharacterEncoding());

        Instructor instructor = new Instructor();
        instructor.setId(Long.parseLong(req.getParameter("id")));
        instructor.setSurname(req.getParameter("surname"));
        instructor.setName(req.getParameter("name"));
        String rank = req.getParameter("rank");
        for (Ranks rankEnum : Ranks.values()) {
            if (rankEnum.getName().equalsIgnoreCase(rank)) {
                instructor.setRank(rankEnum);
            }
        }

        logger.info(instructor);
        req.setAttribute("instructor", instructor);
        req.getRequestDispatcher("/instructor/InstructorAddServlet.html").forward(req, resp);
    }
}
