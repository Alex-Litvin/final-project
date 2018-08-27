package ua.training.controller.command;

import ua.training.model.entity.University;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
import ua.training.model.service.UniversityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



public class ShowUniversitiesCommand implements Command {

    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        List<University> universities = universityService.findAllUniversities();
        request.setAttribute("universities", universities);

        if (user.getRole().equals(Role.USER)) {
            return "/view/userbasic.jsp";
        }
        return "/view/adminbasic.jsp";
    }
}
