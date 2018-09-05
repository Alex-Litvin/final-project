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
import java.util.stream.Collectors;


public class UniversitiesCommand implements Command, Page {

    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String universityId = request.getParameter("universityId");
        String universityTitle = request.getParameter("title");

        System.out.println(universityTitle);

        if (universityId != null) {
            deleteUniversityAndSpecialities(request, universityId);
        }

        if (universityTitle != null) {
            create(universityTitle, request);
        }

        List<University> universities = universityService.findAllUniversities();
        request.setAttribute("universities", universities);

        return ADMIN_UNIVERSITIES + JSP;
    }

    private void create(String title, HttpServletRequest request) {
        if (checkUniqueness(title)) {
            request.setAttribute("universityExists", "message.university_exists");
            return;
        }

        University university = new University();
        university.setTitle(title);
        Long newUniversityId = universityService.createUniversity(university);

        if (newUniversityId != null) {
            request.setAttribute("universityAdded", "message.university_added");
        }
    }

    private boolean checkUniqueness(String title) {
        return universityService.checkIsExists(title) > 0;
    }

    private void deleteUniversityAndSpecialities(HttpServletRequest request, String universityId) {
        Long id = Long.parseLong(universityId);

        if (universityService.deleteById(id)) {
            request.setAttribute("universityDeleted", "message.university_deleted");
        }

        List<Long> specialityIds = specialityService.findAllSpecialitiesByUniversityId(id)
                .stream()
                .map(Speciality::getId)
                .collect(Collectors.toList());

        if (!specialityIds.isEmpty()) {
            specialityService.markAsDeleted(specialityIds);
        }
    }
}
