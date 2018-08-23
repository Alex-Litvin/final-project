package ua.training.controller.command;

import ua.training.model.Speciality;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SpecialityCommand implements Command {
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long universityId = Long.parseLong(request.getParameter("universityId"));
        List<Speciality> specialities = specialityService.findAllSpecialitiesByUniversityId(universityId);
        request.setAttribute("specialities", specialities);

        return "/?command=showUniversities";
    }
}
