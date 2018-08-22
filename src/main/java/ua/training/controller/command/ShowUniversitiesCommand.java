package ua.training.controller.command;

import ua.training.model.University;
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
        List<University> universities = universityService.findAllUniversities();
        request.setAttribute("universities", universities);
        return "/view/universities.jsp";
    }
}
