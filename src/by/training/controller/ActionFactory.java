package by.training.controller;

import by.training.controller.account.AccountEnterAction;
import by.training.controller.account.AccountUpdateAction;
import by.training.controller.assignment.*;
import by.training.controller.course.*;
import by.training.controller.instructor.*;
import by.training.controller.main.LoginAction;
import by.training.controller.main.LogoutAction;
import by.training.controller.main.MainPageAction;
import by.training.controller.result.*;
import by.training.controller.student.*;
import by.training.controller.user.*;
import by.training.domain.Assignment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ActionFactory.class);
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();

    static {
        actions.put("/", MainPageAction.class);
        actions.put("/index", MainPageAction.class);
        actions.put("/main/login", LoginAction.class);
        actions.put("/main/logout", LogoutAction.class);
        actions.put("/account/edit", AccountEnterAction.class);
        actions.put("/account/update", AccountUpdateAction.class);
        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
        actions.put("/instructor/list", InstructorListAction.class);
        actions.put("/instructor/edit", InstructorEditAction.class);
        actions.put("/instructor/save", InstructorSaveAction.class);
        actions.put("/instructor/delete", InstructorDeleteAction.class);
        actions.put("/instructor/search", InstructorSearchAction.class);
        actions.put("/student/list", StudentListAction.class);
        actions.put("/student/edit", StudentEditAction.class);
        actions.put("/student/save", StudentSaveAction.class);
        actions.put("/student/delete", StudentDeleteAction.class);
        actions.put("/student/search", StudentSearchAction.class);
        actions.put("/course/list", CourseListAction.class);
        actions.put("/course/edit", CourseEditAction.class);
        actions.put("/course/save", CourseSaveAction.class);
        actions.put("/course/delete", CourseDeleteAction.class);
        actions.put("/course/search", CourseSearchAction.class);
        actions.put("/assignment/list", AssignmentListAction.class);
        actions.put("/assignment/edit", AssignmentEditAction.class);
        actions.put("/assignment/save", AssignmentSaveAction.class);
        actions.put("/assignment/delete", AssignmentDeleteAction.class);
        actions.put("/assignment/search", AssignmentSearchAction.class);
        actions.put("/assignment/enroll", AssignmentEnrollAction.class);
        actions.put("/assignment/student-list", AssignmentStudentListAction.class);
        actions.put("/result/list", ResultListAction.class);
        actions.put("/result/edit", ResultEditAction.class);
        actions.put("/result/save", ResultSaveAction.class);
        actions.put("/result/delete", ResultDeleteAction.class);
        actions.put("/result/search", ResultSearchAction.class);
    }

    public static Action getAction(String url) throws ServletException {
        Class<?> aClass = actions.get(url);
        LOGGER.debug("Action - " + url);
        if (aClass != null) {
            try {
                return (Action) aClass.getConstructor().newInstance();
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                throw new ServletException(e);
            }
        }
        else {
            return null;
        }
    }
}
