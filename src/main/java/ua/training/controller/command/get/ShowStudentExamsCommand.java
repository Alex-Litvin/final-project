package ua.training.controller.command.get;

import ua.training.controller.command.Command;
import ua.training.controller.utility.Page;
import ua.training.model.entity.Exam;
import ua.training.model.entity.User;
import ua.training.model.service.ExamService;
import ua.training.model.service.UserService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowStudentExamsCommand implements Command, Page {

    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();
    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long userId = Long.parseLong(request.getParameter("userId"));
        List<Exam> userExams = examService.findAllExamsByUserId(userId);
        List<User> users = userService.findAll();
        User user = userService.findById(userId);

        request.setAttribute("user", user);
        request.setAttribute("userExams", userExams);
        request.setAttribute("users", users);

        return ADMIN_EXAM_MARK + JSP;
    }
}
