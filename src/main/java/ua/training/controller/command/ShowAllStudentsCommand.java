package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllStudentsCommand implements Command {

    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> users = userService.findAll();

        request.setAttribute("users", users);

        return "/view/adminbasic.jsp";
    }
}
