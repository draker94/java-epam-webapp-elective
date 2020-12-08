package by.training.controller;

import by.training.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/view/user/list.xyz")
public class UserListViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8"); //Нужно ещё про кодировку браузеру сказать
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h1>UserList</h1>");
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) req.getAttribute("list");
        users.forEach(printWriter::println);
    }
}
