package ua.training.controller.command;

import ua.training.model.User;
import ua.training.model.service.UserService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {
    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<User> user = userService.findByEmail(email);

        if (!user.isPresent() || "".equals(email)) {
            request.setAttribute("notFound", "Invalid login or password!");
            return "/view/login.jsp";
        }
        if (user.get().getPassword().equals(password)) {
            request.getSession().setAttribute("user", user.get());
            return "/view/main.jsp";
        }else {
            request.setAttribute("notFound", "Invalid login or password!");
            return "/view/login.jsp";
        }
    }
}
