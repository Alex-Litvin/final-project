package ua.training.controller.command.get;

import ua.training.controller.command.Command;
import ua.training.model.entity.Exam;
import ua.training.model.entity.Speciality;
import ua.training.model.entity.University;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Subject;
import ua.training.model.service.*;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShowAvailableSpeciality implements Command {
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();
    private SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();
    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = (String) request.getSession().getAttribute("userEmail");
        User user = userService.findByEmail(email);

        List<Long> subjectIds = examService.findAllExamsByUserId(user.getId()).stream()
                .map(Exam::getSubjectId)
                .collect(Collectors.toList());

        List<Subject> userSubjects = subjectService.getSubjectsByIds(subjectIds);

        List<Long> universityIds = universityService.findAllUniversities().stream()
                .map(University::getId)
                .collect(Collectors.toList());

        List<Speciality> allSpecialitiesWithSubjects = specialityService.findAllSpecialitiesWithSubjectsByUniversityIds(universityIds);

        List<Speciality> allAvailableSpecialities = new ArrayList<>();
        for (Speciality speciality : allSpecialitiesWithSubjects) {
            University university = universityService.findUniversityBySpecialityId(speciality.getId());
            speciality.setUniversityTitle(university.getTitle());

            Collections.sort(userSubjects);
            Collections.sort(speciality.getRequiredSubject());
            if (userSubjects.equals(speciality.getRequiredSubject())) {
                allAvailableSpecialities.add(speciality);
            }
        }

        request.setAttribute("allAvailableSpecialities", allAvailableSpecialities);

        return "/user/speciality_request.jsp";
    }
}
