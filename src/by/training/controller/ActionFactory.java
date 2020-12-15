package by.training.controller;

import by.training.controller.user.UserEditAction;
import by.training.controller.user.UserListAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ActionFactory.class);
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();

    static {
        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
    }

    public static Action getAction(String url) throws ServletException {
        Class<?> aClass = actions.get(url);
        LOGGER.debug("Action - " + url);
        if (aClass != null) {
            try {
                return (Action) aClass.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                throw new ServletException(e);
            }
        }
        else {
            return null;
        }
    }
}
