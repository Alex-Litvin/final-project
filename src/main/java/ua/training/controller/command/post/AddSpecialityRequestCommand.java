package ua.training.controller.command.post;

import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.UniversityService;
import ua.training.model.service.UserService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AddSpecialityRequestCommand implements Command {

    private static final Long MAX_COUNT_REGISTRATION = 3L;

    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = (String) request.getSession().getAttribute("userEmail");
        Long specialityId = Long.parseLong(request.getParameter("specialityId"));
        Long universityId = universityService.findUniversityBySpecialityId(specialityId).getId();

        User user = userService.findByEmail(email);

        if (isCountRegistrationAvailable(user)) {
            return "redirect:/user/speciality_request?error=maxCountRegistration";
        }

        if (isAlreadyRegistered(user, specialityId, universityId)) {
            return "redirect:/user/speciality_request?error=alreadyRegistered";
        }

        Long idSpecialityRequest = specialityService.createSpecialityRequest(user.getId(), universityId, specialityId);
        if (Objects.nonNull(idSpecialityRequest)) {
            return "redirect:/user/speciality_request?message=successRegistration";
        }

        return "/user/speciality_request.jsp";
    }

    private boolean isAlreadyRegistered(User user, Long specialityId, Long universityId) {
        Long userId = specialityService.findUserIdByUniversityAndSpecialityId(universityId, specialityId);
        return userId != null && userId.equals(user.getId());
    }

    private boolean isCountRegistrationAvailable(User user) {
        Long countSpecialityRequests = specialityService.countSpecialityRequestsByUserId(user.getId());
        return countSpecialityRequests.compareTo(MAX_COUNT_REGISTRATION) > 0;
    }
}
