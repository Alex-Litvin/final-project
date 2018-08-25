package ua.training.controller.command;

import ua.training.model.Speciality;
import ua.training.model.University;
import ua.training.model.User;
import ua.training.model.dao.UniversityDao;
import ua.training.model.enums.Role;
import ua.training.model.enums.Subject;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.UniversityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ShowUniversityCommand implements Command {
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        List<University> universities = universityService.findAllUniversities();
        request.setAttribute("universities", universities);

        if (user.getRole().equals(Role.USER)) {
            return "/view/userbasic.jsp";
        }
        return "/view/universities.jsp";
    }
}
