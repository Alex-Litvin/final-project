package ua.training.controller.command;

import ua.training.model.entity.Exam;
import ua.training.model.entity.Speciality;
import ua.training.model.entity.User;
import ua.training.model.service.ExamService;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.UniversityService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;
import static ua.training.model.entity.enums.EnterSpecialityStatus.YES;
import static ua.training.model.entity.enums.SpecialityStatus.CLOSED;

public class CompleteSpecialityRegistrationCommand implements Command {

    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long specialityId = Long.parseLong(request.getParameter("specialityId"));
        Speciality speciality = specialityService.findById(specialityId);

        List<User> users = specialityService.findAllUsersBySpecialityId(specialityId);

        List<Long> userIds = users.stream()
                .map(User::getId)
                .collect(toList());

        List<Exam> exams = examService.findAllExamsByUserIds(userIds);

        Map<Long, List<Integer>> userIdExamMarks = exams.stream()
                .collect(groupingBy(Exam::getUserId, mapping(Exam::getMark, toList())));


        List<Long> enteredUserIds = userIdExamMarks.entrySet().stream()
                .filter(checkExamResults(speciality))
                .sorted(sortByTotalExamResults())
                .limit(speciality.getMaxStudentCount())
                .map(Map.Entry::getKey)
                .collect(toList());

        specialityService.updateStatus(specialityId, CLOSED);
        specialityService.setEnterSpecialityStatus(enteredUserIds, specialityId, YES);

        return "/view/adminbasic.jsp";
    }

    private Comparator<Map.Entry<Long, List<Integer>>> sortByTotalExamResults() {
        Comparator<Map.Entry<Long, List<Integer>>> comparingByTotalResultFromMinToMax = Comparator.comparing(userIdExamMark -> userIdExamMark.getValue().stream()
                .reduce(0, (mark1, mark2) -> mark1 + mark2));

        return comparingByTotalResultFromMinToMax.reversed();
    }

    private Predicate<Map.Entry<Long, List<Integer>>> checkExamResults(Speciality speciality) {
        return userIdExamMark -> userIdExamMark.getValue().stream()
                .filter(mark -> mark >= speciality.getPassmark()).count() == 3;
    }
}
