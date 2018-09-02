package ua.training.controller.command.post;

import ua.training.controller.command.Command;
import ua.training.model.entity.University;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.UniversityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class DeleteSpecialityCommand implements Command {

    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long specialityId = Long.parseLong(request.getParameter("specialityId"));
        specialityService.markAsDeleted(Collections.singletonList(specialityId));

        List<University> universities = universityService.findAllUniversities();
        request.setAttribute("universities", universities);

        return "/admin/show_specialities.jsp";
    }
}
