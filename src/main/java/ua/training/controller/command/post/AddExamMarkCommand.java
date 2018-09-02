package ua.training.controller.command.post;

import ua.training.controller.command.Command;
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

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class AddExamMarkCommand implements Command {

    private static final int MIN_MARK = 0;
    private static final int MAX_MARK = 100;
    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();
    private UserService userService =ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long userId;
        try {
            userId = parseLong(request.getParameter("userId"));
        } catch (Exception e) {
            return "redirect:/admin/exam_mark?error=userNotSelected";
        }

        String title = request.getParameter("examTitle");
        Integer mark = parseInt(request.getParameter("mark"));

        if (checkMarkBounds(mark)) {
            return "redirect:/admin/exam_mark?error=markNotValid";
        }
        if (checkTitle(title)) {
            return "redirect:/admin/exam_mark?error=examNotSelected";
        }

        Exam exam = new Exam();
        exam.setUserId(userId);
        exam.setTitle(title);
        exam.setMark(mark);

        if (examService.update(exam)) {
            request.setAttribute("examMarkAdded", "message.exam_mark_added");
        }

        List<User> users = userService.findAll();
        List<Exam> userExams = examService.findAllExamsByUserId(userId);
        User user = userService.findById(userId);

        request.setAttribute("users", users);
        request.setAttribute("userExams", userExams);
        request.setAttribute("user", user);


        return "/admin/exam_mark.jsp";
    }

    private boolean checkTitle(String title) {
        return title == null;
    }

    private boolean checkMarkBounds(Integer mark) {
        return mark < MIN_MARK || mark > MAX_MARK;
    }
}
