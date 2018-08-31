package ua.training.controller.command;

import ua.training.model.entity.Speciality;
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

public class AddSpecialityCommand implements Command {

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

        List<String> subjects = Arrays.asList(firstSubject, secondSubject, thirdSubject);
        if (!checkUniqueness(subjects)) {
            request.setAttribute("notUnique", "Subjects must be unique!");
            return "/admin/specialities.jsp";
        }

        if (isSpecialityAlreadyExists(universityId, title)) {
            request.setAttribute("specialityExists", "Such speciality already exists!");
            return "/admin/specialities.jsp";
        }

        List<Long> subjectIds = subjectService.getIdsByNames(subjects);

        Speciality speciality = new Speciality();
        speciality.setTitle(title);
        speciality.setMaxStudentCount(maxStudentCount);
        speciality.setPassmark(passmark);

        Long specialityId = specialityService.createSpeciality(speciality);

        specialityService.createSpecialitySubjects(specialityId, subjectIds);

        universityService.createUniversitySpeciality(universityId, specialityId);

        request.setAttribute("message", "New speciality was added!");

        return "/admin/specialities.jsp";
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
