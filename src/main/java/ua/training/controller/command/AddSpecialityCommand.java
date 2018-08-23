package ua.training.controller.command;

import ua.training.model.Speciality;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.SubjectService;
import ua.training.model.service.UniversityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AddSpecialityCommand implements Command {
    private SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long universityId = Long.parseLong(request.getParameter("universityId"));
        String title = request.getParameter("title");
        Integer maxStudentCount = Integer.parseInt(request.getParameter("maxStudentCount"));
        String firstSubject = request.getParameter("firstSubject").toUpperCase();
        String secondSubject = request.getParameter("secondSubject").toUpperCase();
        String thirdSubject = request.getParameter("thirdSubject").toUpperCase();

        List<Long> subjectIds = subjectService.getIdsByNames(Arrays.asList(firstSubject, secondSubject, thirdSubject));

        Speciality speciality = new Speciality();
        speciality.setTitle(title);
        speciality.setMaxStudentCount(maxStudentCount);

        Long specialityId = specialityService.createSpeciality(speciality);

        specialityService.createSpecialitySubjects(specialityId, subjectIds);

        universityService.createUniversitySpeciality(universityId, specialityId);

        request.setAttribute("message", "New speciality was added!");

        return "/?command=showUniversities";
    }
}
