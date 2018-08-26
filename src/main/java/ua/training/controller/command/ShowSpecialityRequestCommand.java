package ua.training.controller.command;

import ua.training.model.entity.Speciality;
import ua.training.model.entity.User;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.UniversityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ShowSpecialityRequestCommand implements Command {

    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        List<Speciality> specialities = specialityService.findAllSpecialitiesByUserId(user.getId());
        List<Speciality> specialityRequests = specialities.stream()
                .peek(speciality -> speciality.setUniversityTitle(universityService.findUniversityBySpecialityId(speciality.getId()).getTitle()))
                .collect(Collectors.toList());

        request.setAttribute("specialityRequests", specialityRequests);

        return "/view/userbasic.jsp";
    }
}
