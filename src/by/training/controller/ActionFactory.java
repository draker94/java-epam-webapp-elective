package by.training.controller;

import by.training.controller.instructor.InstructorDeleteAction;
import by.training.controller.instructor.InstructorEditAction;
import by.training.controller.instructor.InstructorListAction;
import by.training.controller.instructor.InstructorSaveAction;
import by.training.controller.user.UserDeleteAction;
import by.training.controller.user.UserEditAction;
import by.training.controller.user.UserListAction;
import by.training.controller.user.UserSaveAction;
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
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
        actions.put("/instructor/list", InstructorListAction.class);
        actions.put("/instructor/edit", InstructorEditAction.class);
        actions.put("/instructor/save", InstructorSaveAction.class);
        actions.put("/instructor/delete", InstructorDeleteAction.class);
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
