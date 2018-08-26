package ua.training.controller.command;

import ua.training.controller.utility.Validator;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
import ua.training.model.entity.enums.Status;
import ua.training.model.service.UserService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {
    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String middleName = request.getParameter("middleName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");

        boolean isValid = Validator.checkInputParameters(firstName, secondName, middleName, password, email, mobile);

        if (!isValid) {
            request.getSession().setAttribute("error", "Some field is empty!");
            return "/view/registration.jsp";
        }
        if (userService.checkEmail(email) || userService.checkMobile(mobile)) {
            request.getSession().setAttribute("error", "User with such email or mobile already exists!");
            return "/view/registration.jsp";
        }

        User user = User.builder()
                .firstName(firstName)
                .secondName(secondName)
                .middleName(middleName)
                .role(Role.USER)
                .password(password)
                .mobile(mobile)
                .email(email)
                .status(Status.ACTIVE)
                .build();



        ServiceFactoryImpl.getInstance().getUserService().create(user);

        request.getSession().setAttribute("user", user);

        return "/view/main.jsp";
    }
}
