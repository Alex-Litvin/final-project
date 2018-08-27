package ua.training.controller.command;

import ua.training.model.entity.Exam;
import ua.training.model.service.ExamService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AddExamMarkCommand implements Command {

    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long userId = Long.parseLong(request.getParameter("userId"));
        String title = request.getParameter("examTitle");
        Integer mark = Integer.parseInt(request.getParameter("mark"));

        Exam exam = new Exam();
        exam.setUserId(userId);
        exam.setTitle(title);
        exam.setMark(mark);


        if (examService.update(exam)) {
            request.setAttribute("examMark", "Exam's mark was added!");
        }

        return "/view/adminbasic.jsp";
    }
}
