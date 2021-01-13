package by.training.controller.account;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.User;
import by.training.service.ServiceException;
import by.training.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Andrey Kliuchnikov
 */

public class AccountUpdateAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(AccountUpdateAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordConfirm = request.getParameter("newPasswordConfirm");
        String newMail = request.getParameter("newMail");
        String message;
        try {
            HttpSession session = request.getSession();
            UserService userService = getServiceCreator().getUserService();
            User user = (User) session.getAttribute("sessionUser");
            if (newMail != null) {
                if (newMail.equals(user.getMail())) {
                    message = "account.edit.error.current-mail";
                } else if (newMail.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\\b")) {
                    user.setMail(newMail);
                    userService.save(user);
                    session.setAttribute("sessionUser", user);
                    message = "account.edit.mail-success";
                } else {
                    message = "account.edit.error.incorrect-mail";
                }
            } else {
                if (oldPassword != null && !oldPassword.isBlank() && newPassword != null &&
                        !newPassword.isBlank() && newPasswordConfirm != null && !newPasswordConfirm.isBlank()) {
                    if (!user.getPassword().equals(oldPassword)) {
                        message = "account.edit.error.current-password";
                    } else if (!newPassword.equals(newPasswordConfirm)) {
                        message = "account.edit.error.password-not-matches";
                    } else if (user.getPassword().equals(newPassword)) {
                        message = "account.edit.error.password-equals";
                    } else {
                        user.setPassword(newPassword);
                        userService.save(user);
                        LOGGER.debug(String.format("Password %s has been updated.", user.getLogin()));
                        session.setAttribute("sessionUser", user);
                        message = "account.edit.password-success";
                    }
                } else {
                    message = "account.edit.error.password-unfilled";
                }
            }
        } catch (ServiceCreationException | ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        request.setAttribute("message", message);
        return new Forward("/account/edit", false);
    }
}
