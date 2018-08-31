package ua.training.controller.command.get;

import ua.training.controller.command.Command;
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




public class UniversitiesCommand implements Command {

    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String universityId = request.getParameter("universityId");
        String universityTitle = request.getParameter("title");

        if (universityId != null) {
            deleteUniversityAndSpecialities(request, universityId);
        }

        if (universityTitle != null) {
            create(universityTitle, request);
        }

        List<University> universities = universityService.findAllUniversities();
        request.setAttribute("universities", universities);

        universities.forEach(System.out::println);

        return "/universities.jsp";
    }

    private void create(String title, HttpServletRequest request) {
        if (checkUniqueness(title)) {
            request.setAttribute("universityNotUnique", "Such university already exists!");
            return;
        }

        University university = new University();
        university.setTitle(title);
        universityService.createUniversity(university);

        request.setAttribute("universityAdded", "University was created!");

    }

    private boolean checkUniqueness(String title) {
        return universityService.checkIsExists(title).compareTo(0L) > 0;
    }

    private void deleteUniversityAndSpecialities(HttpServletRequest request, String universityId) {
        Long id = Long.parseLong(universityId);

        if (universityService.deleteById(id)) {
            request.setAttribute("universityDeleted", "University was deleted!");
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
