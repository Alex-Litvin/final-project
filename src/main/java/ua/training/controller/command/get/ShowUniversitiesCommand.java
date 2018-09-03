package ua.training.controller.command.get;

import ua.training.controller.command.Command;
import ua.training.controller.utility.Page;
import ua.training.model.entity.University;
import ua.training.model.entity.enums.Role;
import ua.training.model.service.UniversityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowUniversitiesCommand implements Command, Page {

    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role role = (Role) request.getSession().getAttribute("role");
        List<University> universities = universityService.findAllUniversities();
        request.setAttribute("universities", universities);

        if (role.equals(Role.ADMIN)) {
            return ADMIN_COMPLETE_SPECIALITY + JSP;
        }
        return USER_UNIVERSITIES_SPECIALITIES + JSP;
    }
}
