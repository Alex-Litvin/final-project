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
import java.util.stream.Collectors;

public class DeleteUniversityCommand implements Command {

    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long universityId = Long.parseLong(request.getParameter("universityId"));

        if (universityService.deleteById(universityId)) {
            request.setAttribute("universityDeleted", "University was deleted!");
        }

        List<Long> specialityIds = specialityService.findAllSpecialitiesByUniversityId(universityId)
                .stream()
                .map(Speciality::getId)
                .collect(Collectors.toList());
        if (!specialityIds.isEmpty()) {
            specialityService.markAsDeleted(specialityIds);
        }

        return "/view/adminbasic.jsp";
    }
}
