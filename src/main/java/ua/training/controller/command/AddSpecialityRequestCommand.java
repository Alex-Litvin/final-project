package ua.training.controller.command;

import ua.training.model.User;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;
import ua.training.model.service.implementation.SpecialityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AddSpecialityRequestCommand implements Command {
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        Long universityId = Long.parseLong(request.getParameter("universityId"));
        Long specialityId = Long.parseLong(request.getParameter("specialityId"));

        Long idSpecialityRequest = specialityService.createSpecialityRequest(user.getId(), universityId, specialityId);
        if (!Objects.nonNull(idSpecialityRequest)) {
            request.setAttribute("specialityRequest", "Your request was added!");
        }

        return "/view/userbasic.jsp";
    }
}
