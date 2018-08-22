package ua.training.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeCommand implements Command {
//    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        User currentUser = (User) request.getSession().getAttribute("user");
//        Optional<User> user = userService.findByEmail(currentUser.getEmail());
//        request.getSession().setAttribute("user", user);

        return "/view/main.jsp";
    }
}
