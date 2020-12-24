package by.training.controller;

import by.training.connection.ConnectionPool;
import by.training.di.ServiceCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.html")
public class DispatcherServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DispatcherServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatch(req, resp);
    }

    @Override
    public void init() throws ServletException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.initConnections("by/training/resources/database", 10, 50, 2);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        LOGGER.debug("REQUEST URI - " + requestURI + " CONTEXT PATH - " + contextPath);
        int postfixIndex = requestURI.lastIndexOf(".html");
        if (postfixIndex != -1) {
            requestURI = requestURI.substring(contextPath.length(), postfixIndex);
        } else {
            requestURI = requestURI.substring(contextPath.length());
        }
        Action action = ActionFactory.getAction(requestURI);
        Forward forward = null;
        if (action != null) {
            try (ServiceCreator serviceCreator = new ServiceCreator()) {
                action.setServiceCreator(serviceCreator);
                forward = action.execute(request, response);
                LOGGER.error("Action execute is done.");
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        if (forward != null) {
            response.sendRedirect(contextPath + forward.getUrl());
        } else {
            request.getRequestDispatcher("/WEB-INF/pages" + requestURI + ".jsp").forward(request, response);
        }
    }
}
