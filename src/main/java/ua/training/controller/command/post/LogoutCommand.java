package ua.training.controller.command.post;

import ua.training.controller.command.Command;
import ua.training.controller.command.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletUtil servletUtil = new ServletUtil();
        servletUtil.deleteUserFromContextAndSession(request);

        return "redirect:/login";
    }
}
