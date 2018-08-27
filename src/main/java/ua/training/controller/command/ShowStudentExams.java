package ua.training.controller.command;

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

public class ShowStudentExams implements Command {

    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long userId = Long.parseLong(request.getParameter("userId"));
        List<Exam> userExams = examService.findAllExamsByUserId(userId);

        request.setAttribute("userId", userId);
        request.setAttribute("userExams", userExams);

        return "/view/adminbasic.jsp";
    }
}
