package ua.training.controller.command;

import ua.training.model.service.SpecialityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class DeleteSpecialityCommand implements Command {
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long specialityId = Long.parseLong(request.getParameter("specialityId"));
        specialityService.markAsDeleted(Collections.singletonList(specialityId));
        return "/?command=showUniversities";
    }
}
