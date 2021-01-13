package by.training.controller;

import by.training.connection.ConnectionPool;
import by.training.connection.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Andrey Kliuchnikov
 */

@WebListener
public class WebApplicationInitializerListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(WebApplicationInitializerListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initConnections("by/training/resources/database", 10, 50, 2);
            LOGGER.info("The application started!");
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
        LOGGER.info("The application stopped!");
    }
}
