package ua.training.controller.command.post;

import ua.training.controller.command.Command;
import ua.training.controller.command.ServletUtil;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginConfirm implements Command {
    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<User> user = userService.findByEmail(email);
        ServletUtil servletUtil = new ServletUtil();

        if (!user.isPresent() || "".equals(email)) {
            return "redirect:/login.jsp?err=email";
        }

        if (!user.get().getPassword().equals(password)) {
            return "redirect:/login.jsp?err=password";
        }

        servletUtil.setUserEmailRoleToSession(request, user.get().getRole(), user.get().getEmail());
        String successLoginUrl = "redirect:" + user.get().getRole().getHomePage();
        if (servletUtil.checkUserLogged(request, email)) {
            successLoginUrl += "?logged=true";
        }

        return successLoginUrl;

    }
}
