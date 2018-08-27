package ua.training.controller.command;

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

public class ShowUniversitiesSubjectsCommand implements Command {

    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<University> universities = universityService.findAllUniversities();
        List<Subject> subjects = subjectService.findAll();

        request.setAttribute("universities", universities);
        request.setAttribute("subjects", subjects);

        return "/view/adminbasic.jsp";
    }
}
