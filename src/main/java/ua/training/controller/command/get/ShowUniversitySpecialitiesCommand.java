package ua.training.controller.command.get;

import ua.training.controller.command.Command;
import ua.training.controller.utility.Page;
import ua.training.model.entity.Speciality;
import ua.training.model.entity.University;
import ua.training.model.entity.enums.Role;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.SubjectService;
import ua.training.model.service.UniversityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ShowUniversitySpecialitiesCommand implements Command, Page {

    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role role = (Role) request.getSession().getAttribute("role");
        String universityId = request.getParameter("universityId");
        List<University> universities = universityService.findAllUniversities();

        if (universityId != null) {
            Long id = Long.parseLong(universityId);
            List<Speciality> specialities = specialityService.findAllSpecialitiesByUniversityId(id);
            List<Speciality> specialitiesWithSubjects = specialities.stream()
                    .peek(s -> s.setRequiredSubject(subjectService.findRequiredSubjectsBySpecialityId(s.getId())))
                    .collect(Collectors.toList());

            request.setAttribute("specialitiesWithSubjects", specialitiesWithSubjects);
        }
        request.setAttribute("universities", universities);

        if (role.equals(Role.USER)) {
            return USER_UNIVERSITIES_SPECIALITIES + JSP;
        }
        return ADMIN_SHOW_SPECIALITIES + JSP;
    }
}
