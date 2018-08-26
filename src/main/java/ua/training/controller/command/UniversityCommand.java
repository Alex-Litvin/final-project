package ua.training.controller.command;

import ua.training.model.entity.Speciality;
import ua.training.model.entity.University;
import ua.training.model.entity.enums.Subject;
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

public class UniversityCommand implements Command {
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("command");
        String universityId = request.getParameter("universityId");
        if (action.equals("deleteUniversity")) {
            deleteUniversity(universityId);
        }
        if (action.equals("editUniversity")) {
            editUniversity(request, universityId);
        }
        if (action.equals("addUniversity")) {
            addUniversity(request);
        }

        List<University> universities = universityService.findAllUniversities();
        List<Subject> subjects = subjectService.findAll();
        request.setAttribute("subjects", subjects);
        request.setAttribute("universities", universities);

        return "/view/universities.jsp";
    }

    private void addUniversity(HttpServletRequest request) {
        University university = new University();
        university.setTitle(request.getParameter("title"));
        universityService.createUniversity(university);
    }

    private void editUniversity(HttpServletRequest request, String id) {
        Long universityId = Long.parseLong(id);
        University university = universityService.findUniversityById(universityId);
        university.setTitle(request.getParameter("universityTitle"));
        universityService.update(university);
    }

    private void deleteUniversity(String id) {
        Long universityId = Long.parseLong(id);
        universityService.deleteById(universityId);
        List<Long> specialityIds = specialityService.findAllSpecialitiesByUniversityId(universityId)
                .stream()
                .map(Speciality::getId)
                .collect(Collectors.toList());
        if (!specialityIds.isEmpty()) {
            specialityService.markAsDeleted(specialityIds);
        }
    }
}
