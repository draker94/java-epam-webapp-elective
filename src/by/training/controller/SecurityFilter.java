package by.training.controller;

import by.training.domain.User;
import by.training.enums.Roles;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter("*.html")
public class SecurityFilter implements Filter {
    private static final Map<String, Set<Roles>> permissions = new HashMap<>();

    static {
        Set<Roles> all = new HashSet<>();
        Collections.addAll(all, Roles.values());
        Set<Roles> administrators = new HashSet<>();
        administrators.add(Roles.ADMINISTRATOR);
        Set<Roles> instructors = new HashSet<>();
        instructors.add(Roles.INSTRUCTOR);
        Set<Roles> students = new HashSet<>();
        students.add(Roles.STUDENT);

        permissions.put("/index", all);
        permissions.put("/main/logout", all);
        permissions.put("/account/edit", all);
        permissions.put("/account/update", all);

        permissions.put("/account/administrator", administrators);
        permissions.put("/user/list", administrators);
        permissions.put("/user/edit", administrators);
        permissions.put("/user/save", administrators);
        permissions.put("/user/delete", administrators);
        permissions.put("/instructor/list", administrators);
        permissions.put("/instructor/edit", administrators);
        permissions.put("/instructor/save", administrators);
        permissions.put("/instructor/delete", administrators);
        permissions.put("/instructor/search", administrators);

        permissions.put("/account/instructor", instructors);
        permissions.put("/course/list", instructors);
        permissions.put("/course/edit", instructors);
        permissions.put("/course/save", instructors);
        permissions.put("/course/delete", instructors);
        permissions.put("/course/search", instructors);
        permissions.put("/student/list", instructors);
        permissions.put("/student/edit", instructors);
        permissions.put("/student/save", instructors);
        permissions.put("/student/delete", instructors);
        permissions.put("/student/search", instructors);
        permissions.put("/assignment/list", instructors);
        permissions.put("/assignment/edit", instructors);
        permissions.put("/assignment/save", instructors);
        permissions.put("/assignment/delete", instructors);
        permissions.put("/assignment/search", instructors);
        permissions.put("/result/list", instructors);
        permissions.put("/result/edit", instructors);
        permissions.put("/result/save", instructors);
        permissions.put("/result/delete", instructors);
        permissions.put("/result/search", instructors);

        permissions.put("/account/student", students);
        permissions.put("/course/list", students);
        permissions.put("/assignment/list", students);
        permissions.put("/assignment/enroll", students);
        permissions.put("/assignment/save", students); // подумОть о безопасности
        permissions.put("/result/list", students);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
        String requestUrl = httpReq.getRequestURI();
        String contextPath = httpReq.getContextPath();
        int postfixIndex = requestUrl.lastIndexOf(".html");
        if (postfixIndex != -1) {
            requestUrl = requestUrl.substring(contextPath.length(), postfixIndex);
        } else {
            requestUrl = requestUrl.substring(contextPath.length());
        }
        Set<Roles> roles = permissions.get(requestUrl);
        if (roles != null) {
            HttpSession session = httpReq.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("sessionUser");
                if (user != null && roles.contains(user.getRole())) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        httpResp.sendRedirect(contextPath + "/index.html");
    }
}
