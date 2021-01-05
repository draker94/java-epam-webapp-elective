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
        String requestUrl = request.getRequestURI();
        String contextPath = request.getContextPath();
        LOGGER.debug("REQUEST URI - " + requestUrl + " CONTEXT PATH - " + contextPath);
        int postfixIndex = requestUrl.lastIndexOf(".html");
        if (postfixIndex != -1) {
            requestUrl = requestUrl.substring(contextPath.length(), postfixIndex);
        } else {
            requestUrl = requestUrl.substring(contextPath.length());
        }
        Action action = ActionFactory.getAction(requestUrl);
        Forward forward = null;
        if (action != null) {
            try (ServiceCreator serviceCreator = new ServiceCreator()) {
                action.setServiceCreator(serviceCreator);
                forward = action.execute(request, response);
                LOGGER.debug("Action execute is done.");
            } catch (Exception e) {
                LOGGER.error(e);
                request.setAttribute("error", e.getLocalizedMessage());
                request.setAttribute("url", contextPath + "/");
                requestUrl = "main/error";
            }
        }
        if (forward != null && forward.isRedirect()) {
            LOGGER.debug(contextPath + forward.getUrl());
            response.sendRedirect(contextPath + forward.getUrl());
        } else {
            if(forward != null && forward.getUrl() != null) {
                requestUrl = forward.getUrl();
            }
            request.getRequestDispatcher("/WEB-INF/pages" + requestUrl + ".jsp").forward(request, response);
        }
    }
}
