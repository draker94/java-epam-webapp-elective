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
    private static final Map<Roles, Set<String>> permissions = new HashMap<>();

    static {
        Set<String> adminPermissions = new HashSet<>();
        adminPermissions.add("/main/logout");
        adminPermissions.add("/account/edit");
        adminPermissions.add("/account/update");
        adminPermissions.add("/account/administrator");
        adminPermissions.add("/user/list");
        adminPermissions.add("/user/edit");
        adminPermissions.add("/user/save");
        adminPermissions.add("/user/delete");
        adminPermissions.add("/instructor/list");
        adminPermissions.add("/instructor/edit");
        adminPermissions.add("/instructor/save");
        adminPermissions.add("/instructor/delete");
        adminPermissions.add("/instructor/search");

        Set<String> instructorPermissions = new HashSet<>();
        instructorPermissions.add("/main/logout");
        instructorPermissions.add("/account/edit");
        instructorPermissions.add("/account/update");
        instructorPermissions.add("/account/instructor");
        instructorPermissions.add("/course/list");
        instructorPermissions.add("/course/edit");
        instructorPermissions.add("/course/save");
        instructorPermissions.add("/course/delete");
        instructorPermissions.add("/course/search");
        instructorPermissions.add("/student/list");
        instructorPermissions.add("/student/edit");
        instructorPermissions.add("/student/save");
        instructorPermissions.add("/student/delete");
        instructorPermissions.add("/student/search");
        instructorPermissions.add("/assignment/list");
        instructorPermissions.add("/assignment/edit");
        instructorPermissions.add("/assignment/save");
        instructorPermissions.add("/assignment/delete");
        instructorPermissions.add("/assignment/search");
        instructorPermissions.add("/assignment/instructor-list");
        instructorPermissions.add("/result/list");
        instructorPermissions.add("/result/edit");
        instructorPermissions.add("/result/save");
        instructorPermissions.add("/result/delete");
        instructorPermissions.add("/result/search");

        Set<String> studentPermissions = new HashSet<>();
        studentPermissions.add("/main/logout");
        studentPermissions.add("/account/edit");
        studentPermissions.add("/account/update");
        studentPermissions.add("/account/student");
        studentPermissions.add("/course/list");
        studentPermissions.add("/course/search");
        studentPermissions.add("/assignment/student-list");
        studentPermissions.add("/assignment/enroll");
        studentPermissions.add("/assignment/save"); // подумОть о безопасности
        studentPermissions.add("/assignment/search");
        studentPermissions.add("/result/student-list");

        permissions.put(Roles.ADMINISTRATOR, adminPermissions);
        permissions.put(Roles.INSTRUCTOR, instructorPermissions);
        permissions.put(Roles.STUDENT, studentPermissions);
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
        HttpSession session = httpReq.getSession(false);
        boolean securePage = false;
        for (Set<String> set : permissions.values()) {
            if (set.contains(requestUrl)) {
                securePage = true;
                break;
            }
        }
        if (securePage) {
            if (session != null) {
                User user = (User) session.getAttribute("sessionUser");
                //Whitelist authorization
                if (user != null && permissions.get(user.getRole()).contains(requestUrl)) {
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
