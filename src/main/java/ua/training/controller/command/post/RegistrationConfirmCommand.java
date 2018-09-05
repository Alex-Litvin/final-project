package ua.training.controller.command.post;

import ua.training.controller.command.Command;
import ua.training.controller.utility.Page;
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

public class RegistrationConfirmCommand implements Command, Page {

    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String middleName = request.getParameter("middleName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        boolean isValid = Validator.checkInputParameters(firstName, secondName, middleName, password, email);

        if (!isValid) {
            return REDIRECT + REGISTRATION + "?error=emptyField";
        }

        if (!Validator.checkNames(firstName, middleName, secondName)) {
            return REDIRECT + REGISTRATION + "?error=nameNotValid";
        }

        if (!Validator.checkEmail(email)) {
            return REDIRECT + REGISTRATION + "?error=emailNotValid";
        }

        if (!Validator.checkPassword(password)) {
            return REDIRECT + REGISTRATION + "?error=passwordNotValid";
        }

        if (userService.checkEmail(email)) {
            return REDIRECT + REGISTRATION + "?error=userExists";
        }

        User user = User.builder()
                .firstName(firstName)
                .secondName(secondName)
                .middleName(middleName)
                .role(Role.USER)
                .password(password)
                .email(email)
                .status(Status.ACTIVE)
                .build();

        userService.create(user);

        return REDIRECT + VIEW_USER_USER_MENU_JSP;
    }
}
