package ua.training.controller.command;

import ua.training.model.entity.Speciality;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.SubjectService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ShowSpecialityCommand implements Command {

    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        Long universityId = Long.parseLong(request.getParameter("universityId"));
        List<Speciality> specialities = specialityService.findAllSpecialitiesByUniversityId(universityId);
        List<Speciality> specialitiesWithSubjects = specialities.stream()
                .peek(s -> s.setRequiredSubject(subjectService.findRequiredSubjectsBySpecialityId(s.getId())))
                .collect(Collectors.toList());
        request.setAttribute("specialitiesWithSubjects", specialitiesWithSubjects);

        if (user.getRole().equals(Role.USER)) {
            return "/view/userbasic.jsp";
        }
        return "/view/adminbasic.jsp";
    }
}
