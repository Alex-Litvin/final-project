package ua.training.controller.command.post;

import ua.training.controller.command.Command;
import ua.training.model.entity.Exam;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Subject;
import ua.training.model.service.ExamService;
import ua.training.model.service.SubjectService;
import ua.training.model.service.UserService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExamRegistrationCommand implements Command {

    private static final int MAX_QUANTITY_EXAMS = 3;
    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();
    private SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();
    private UserService userService = ServiceFactoryImpl.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String subjectTitle = request.getParameter("subjectTitle").toUpperCase();
        Long subjectId = subjectService.getIdByName(subjectTitle);

        String email = (String) request.getSession().getAttribute("userEmail");
        User user = userService.findByEmail(email);

        List<Subject> subjects = subjectService.findAll();
        request.setAttribute("subjects", subjects);

        if (checkQuantityExams(user.getId())) {
            request.setAttribute("examQuantity", "message.exam_max_quantity");
            return "/user/exam_registration.jsp";
        }

        if (checkExamUniqueness(user.getId(), subjectId)) {
            request.setAttribute("examNotUnique", "message.not_unique_exam");
            return "/user/exam_registration.jsp";
        }

        Exam exam = new Exam();
        exam.setUserId(user.getId());
        exam.setSubjectId(subjectId);
        exam.setTitle(subjectTitle);

        Long examId = examService.addExam(exam);

        if (examId != null) {
            request.setAttribute("examAdded", "message.exam_added");
        }

        return "/user/exam_registration.jsp";
    }

    private boolean checkQuantityExams(Long userId) {
        return examService.countExamsByUserId(userId) >= MAX_QUANTITY_EXAMS;
    }

    private boolean checkExamUniqueness(Long userId, Long subjectId) {
        return examService.findExamIdByUserIdAndSubjectId(userId, subjectId) != null;
    }
}
