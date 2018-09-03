package ua.training.controller.command.post;

import ua.training.controller.command.Command;
import ua.training.controller.utility.Page;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class AddSpecialityCommand implements Command, Page {

    private static final int REQUIRED_COUNT_SUBJECTS = 3;

    private SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long universityId = Long.parseLong(request.getParameter("universityId"));
        String title = request.getParameter("title");
        Integer maxStudentCount = Integer.parseInt(request.getParameter("maxStudentCount"));
        Integer passmark = Integer.parseInt(request.getParameter("passmark"));
        String firstSubject = request.getParameter("firstSubject").toUpperCase();
        String secondSubject = request.getParameter("secondSubject").toUpperCase();
        String thirdSubject = request.getParameter("thirdSubject").toUpperCase();

        List<University> universities = universityService.findAllUniversities();
        List<Subject> subjects = subjectService.findAll();

        request.setAttribute("universities", universities);
        request.setAttribute("subjects", subjects);

        List<String> requiredSubjects = Arrays.asList(firstSubject, secondSubject, thirdSubject);
        if (!checkUniqueness(requiredSubjects)) {
            request.setAttribute("notUniqueSubject", "message.not_unique_subject");
            return ADMIN_SPECIALITIES + JSP;
        }

        if (isSpecialityAlreadyExists(universityId, title)) {
            request.setAttribute("specialityExists", "message.speciality_exists");
            return ADMIN_SPECIALITIES + JSP;
        }

        List<Long> subjectIds = subjectService.getIdsByNames(requiredSubjects);

        Speciality speciality = new Speciality();
        speciality.setTitle(title);
        speciality.setMaxStudentCount(maxStudentCount);
        speciality.setPassmark(passmark);

        Long specialityId = specialityService.createSpeciality(speciality);
        specialityService.createSpecialitySubjects(specialityId, subjectIds);
        Long universitySpecialityId = universityService.createUniversitySpeciality(universityId, specialityId);

        if (universitySpecialityId != null) {
            request.setAttribute("specialityAdded", "message.speciality_added");
        }

        return ADMIN_SPECIALITIES + JSP;
    }

    private boolean checkUniqueness(List<String> subjects) {
        return new HashSet<>(subjects).size() == REQUIRED_COUNT_SUBJECTS;
    }

    private boolean isSpecialityAlreadyExists(Long universityId,String title) {
        return specialityService.findAllSpecialitiesByUniversityId(universityId).stream()
                .map(Speciality::getTitle)
                .anyMatch(title::equals);
    }
}
