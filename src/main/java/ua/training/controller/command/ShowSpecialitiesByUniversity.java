package ua.training.controller.command;

import ua.training.model.entity.Speciality;
import ua.training.model.entity.University;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.UniversityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowSpecialitiesByUniversity implements Command {

    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long universityId = Long.parseLong(request.getParameter("universityId"));
        List<Speciality> specialities = specialityService.findAllSpecialitiesByUniversityId(universityId);

        List<University> universities = universityService.findAllUniversities();

        request.setAttribute("specialitiesByUniversity", specialities);
        request.setAttribute("universities", universities);

        return "/view/adminbasic.jsp";
    }
}
