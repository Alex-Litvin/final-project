package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
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

        if (!user.isPresent() || "".equals(email) || "".equals(password)) {
            request.setAttribute("notFound", "Invalid login or password!");
            return "/view/login.jsp";
        } else {
            User currentUser = user.get();
            if (currentUser.getPassword().equals(password)) {
                request.getSession().setAttribute("user", user.get());
                if (currentUser.getRole().equals(Role.USER)) {
                    return "/view/userbasic.jsp";
                } else {
                    return "/view/main.jsp";
                }
            }
        }
        return "/view/login.jsp";
    }
}
