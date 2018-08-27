package ua.training.controller.command;

import ua.training.model.entity.University;
import ua.training.model.entity.User;
import ua.training.model.service.ExamService;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.UserService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ShowAllStudents implements Command {

    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();
    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> usersWithExams = userService.findAll().stream()
                .peek(user -> user.setExams(examService.findAllExamsByUserId(user.getId())))
                .collect(Collectors.toList());

        request.setAttribute("usersWithExams", usersWithExams);

        return "/view/adminbasic.jsp";
    }
}
