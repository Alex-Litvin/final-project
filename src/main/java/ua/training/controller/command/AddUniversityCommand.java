package ua.training.controller.command;

import ua.training.model.entity.University;
import ua.training.model.service.UniversityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUniversityCommand implements Command {

    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");

        if (checkUniqueness(title)) {
            request.setAttribute("universityNotUnique", "Such university already exists!");
            return "/view/adminbasic.jsp";
        }

        University university = new University();
        university.setTitle(title);
        universityService.createUniversity(university);

        request.setAttribute("universityAdded", "University was created!");

        return "/view/adminbasic.jsp";
    }

    private boolean checkUniqueness(String title) {
        return universityService.checkIsExists(title).compareTo(0L) > 0;
    }
}
