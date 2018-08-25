package ua.training.controller.command;

import ua.training.model.User;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowApplicantsCommand implements Command {
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long specialityId = Long.parseLong(request.getParameter("specialityId"));
        Long currentPage = Long.parseLong(request.getParameter("currentPage"));
        Long recordsPerPage = Long.parseLong(request.getParameter("recordsPerPage"));

        List<User> users = specialityService.findUsersFromTo(specialityId, currentPage, recordsPerPage);
        Long rows = specialityService.getNumberOfRows(specialityId);

        Long nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("users", users);
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return "/view/applicants.jsp";
    }
}
