package ua.training.controller.command.get;

import ua.training.controller.command.Command;
import ua.training.model.entity.Speciality;
import ua.training.model.entity.User;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.UniversityService;
import ua.training.model.service.UserService;
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
    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = (String) request.getSession().getAttribute("userEmail");
        User user = userService.findByEmail(email);

        List<Speciality> specialities = specialityService.findAllSpecialitiesByUserId(user.getId());
        List<Speciality> specialityRequests = specialities.stream()
                .peek(speciality -> speciality.setUniversityTitle(universityService.findUniversityBySpecialityId(speciality.getId()).getTitle()))
                .collect(Collectors.toList());

        request.setAttribute("specialityRequests", specialityRequests);

        return "/user/speciality_rating.jsp";
    }
}
