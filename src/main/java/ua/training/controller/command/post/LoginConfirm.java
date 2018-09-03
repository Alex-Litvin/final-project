package ua.training.controller.command.post;

import ua.training.controller.command.Command;
import ua.training.controller.command.ServletUtil;
import ua.training.controller.utility.Page;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginConfirm implements Command, Page {

    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        ServletUtil servletUtil = new ServletUtil();

        if (email == null || "".equals(email)) {
            return REDIRECT + LOGIN + "?error=email";
        }

        if (password == null || "".equals(password)) {
            return REDIRECT + LOGIN + "?error=password";
        }

        User user = userService.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            return REDIRECT + LOGIN + "?error=authError";
        }

        servletUtil.setUserEmailRoleToSession(request, user.getRole(), user.getEmail());
        String successLoginUrl = REDIRECT + user.getRole().getHomePage();

        if (servletUtil.checkUserLogged(request, email)) {
            successLoginUrl += "?logged=true";
        }

        return successLoginUrl;
    }
}
