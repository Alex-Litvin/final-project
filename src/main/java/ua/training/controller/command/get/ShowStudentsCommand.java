package ua.training.controller.command.get;

import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.UserService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowStudentsCommand implements Command {

    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int recordsPerPage = 5;//Integer.parseInt(request.getParameter("recordsPerPage"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        List<User> users = userService.findUsersFromTo(currentPage, recordsPerPage);
        int rows = userService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        System.out.println("nOfPages: " + nOfPages);
        System.out.println("recordsPerPage " + recordsPerPage);

        request.setAttribute("users", users);
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return "/admin/students.jsp";
    }
}
