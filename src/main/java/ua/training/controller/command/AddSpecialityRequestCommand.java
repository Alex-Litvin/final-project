package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.UniversityService;
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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        Long specialityId = Long.parseLong(request.getParameter("specialityId"));
        Long universityId = universityService.findUniversityBySpecialityId(specialityId).getId();

        if (isCountRegistrationAvailable(request, user)) {
            return "/view/userbasic.jsp";
        }

        if (isAlreadyRegistered(request, user, specialityId, universityId)) {
            return "/view/userbasic.jsp";
        }

        Long idSpecialityRequest = specialityService.createSpecialityRequest(user.getId(), universityId, specialityId);
        if (Objects.nonNull(idSpecialityRequest)) {
            request.setAttribute("success", "Your request was added!");
        }

        return "/view/userbasic.jsp";
    }

    private boolean isAlreadyRegistered(HttpServletRequest request, User user, Long specialityId, Long universityId) {
        Long userId = specialityService.findUserIdByUniversityAndSpecialityId(universityId, specialityId);

        if (userId != null && userId.equals(user.getId())) {
            request.setAttribute("alreadyRegistered", "You already registered for this speciality!");
            return true;
        }
        return false;
    }

    private boolean isCountRegistrationAvailable(HttpServletRequest request, User user) {
        Long countSpecialityRequests = specialityService.countSpecialityRequestsByUserId(user.getId());
        if (countSpecialityRequests.compareTo(MAX_COUNT_REGISTRATION) > 0) {
            request.setAttribute("maxCountRegistration", "You already registered for max count speciality!");
            return true;
        }
        return false;
    }
}
