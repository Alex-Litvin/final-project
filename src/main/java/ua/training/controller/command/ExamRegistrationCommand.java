package ua.training.controller.command;

import ua.training.model.entity.Exam;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Subject;
import ua.training.model.service.ExamService;
import ua.training.model.service.SubjectService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExamRegistrationCommand implements Command {
    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();
    private SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("command");
        if (action.equals("registerForExam")) {
            registerForExam(request);
        }

        List<Subject> subjects = subjectService.findAll();
        request.setAttribute("subjects", subjects);

        return "/view/userbasic.jsp";
    }

    private String registerForExam(HttpServletRequest request) {
        String subjectTitle = request.getParameter("subjectTitle").toUpperCase();
        User user = (User) request.getSession().getAttribute("user");
        Long subjectId = subjectService.getIdByName(subjectTitle);

        Exam exam = new Exam();
        exam.setUserId(user.getId());
        exam.setSubjectId(subjectId);
        exam.setTitle(subjectService.getSubjectById(subjectId).name());

        if (!isAvailableRegistration(user.getId())) {
            request.setAttribute("examCount", "You already register for max exams!");
            return "/view/userbasic.jsp";
        }

        if (checkExamUniqueness(user.getId(), subjectId)) {
            request.setAttribute("examNotUnique", "You already register for this exam!");
            return "/view/userbasic.jsp";
        }

        Long examId = examService.addExam(exam);

        if (examId != null) {
            request.setAttribute("examAdded", "Exam was added!");
        }
        return subjectTitle;
    }

    private boolean isAvailableRegistration(Long userId) {
        return examService.countExamsByUserId(userId) < 3;
    }

    private boolean checkExamUniqueness(Long userId, Long subjectId) {
        return examService.findExamIdByUserIdAndSubjectId(userId, subjectId) != null;
    }
}
