package ua.training.controller.command.get;

import ua.training.controller.command.Command;
import ua.training.controller.utility.Page;
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

public class ShowSpecialitiesByUniversity implements Command, Page {

    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long universityId = Long.parseLong(request.getParameter("universityId"));

        List<Speciality> specialities = specialityService.findAllSpecialitiesByUniversityId(universityId);
        List<University> universities = universityService.findAllUniversities();
        University university = universityService.findUniversityById(universityId);

        request.setAttribute("specialitiesByUniversity", specialities);
        request.setAttribute("universities", universities);
        request.setAttribute("university", university);

        return ADMIN_COMPLETE_SPECIALITY + JSP;
    }
}
