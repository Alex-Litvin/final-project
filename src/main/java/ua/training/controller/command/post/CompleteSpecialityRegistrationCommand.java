package ua.training.controller.command.post;

import ua.training.controller.command.Command;
import ua.training.controller.utility.Page;
import ua.training.model.entity.Exam;
import ua.training.model.entity.Speciality;
import ua.training.model.entity.University;
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

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.*;
import static ua.training.model.entity.enums.EnterSpecialityStatus.YES;
import static ua.training.model.entity.enums.SpecialityStatus.CLOSED;

public class CompleteSpecialityRegistrationCommand implements Command, Page {

    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long universityId;
        try {
            universityId = parseLong(request.getParameter("universityId"));
        }catch (Exception e) {
            return REDIRECT + ADMIN_COMPLETE_SPECIALITY + "?error=universityNotSelected";
        }

        Long specialityId;
        try {
            specialityId = parseLong(request.getParameter("specialityId"));
        } catch (Exception e) {
            return REDIRECT + ADMIN_COMPLETE_SPECIALITY + "?error=specialityNotSelected";
        }

        Speciality speciality = specialityService.findById(specialityId);
        University university = universityService.findUniversityById(universityId);
        List<University> universities = universityService.findAllUniversities();
        List<Speciality> specialitiesByUniversity = specialityService.findAllSpecialitiesByUniversityId(universityId);

        request.setAttribute("university", university);
        request.setAttribute("universities", universities);
        request.setAttribute("specialitiesByUniversity", specialitiesByUniversity);

        List<User> users = specialityService.findAllUsersBySpecialityId(specialityId);

        if (users.isEmpty()) {
            specialityService.updateStatus(specialityId, CLOSED);
            request.setAttribute("specialityClosed", "message.speciality_closed");
            return ADMIN_COMPLETE_SPECIALITY + JSP;
        }

        List<Long> userIds = users.stream()
                .map(User::getId)
                .collect(toList());

        List<Exam> exams = examService.findAllExamsByUserIds(userIds);

        Map<Long, List<Integer>> userIdExamMarks = exams.stream()
                .collect(groupingBy(Exam::getUserId, mapping(Exam::getMark, toList())));

        List<Long> enteredUserIds = userIdExamMarks.entrySet().stream()
                .filter(checkExamResults(speciality))
                .sorted(sortByTotalExamResultsDesc())
                .limit(speciality.getMaxStudentCount())
                .map(Map.Entry::getKey)
                .collect(toList());

        specialityService.updateStatus(specialityId, CLOSED);
        request.setAttribute("specialityClosed", "message.speciality_closed");

        if (!enteredUserIds.isEmpty()) {
            specialityService.setEnterSpecialityStatus(enteredUserIds, specialityId, YES);
        }

        return ADMIN_COMPLETE_SPECIALITY + JSP;
    }

    private Comparator<Map.Entry<Long, List<Integer>>> sortByTotalExamResultsDesc() {
        Comparator<Map.Entry<Long, List<Integer>>> comparingByTotalResultFromMinToMax = Comparator.comparing(userIdExamMark -> userIdExamMark.getValue().stream()
                .mapToInt(mark -> mark)
                .sum());

        return comparingByTotalResultFromMinToMax.reversed();
    }

    private Predicate<Map.Entry<Long, List<Integer>>> checkExamResults(Speciality speciality) {
        return userIdExamMark -> userIdExamMark.getValue().stream()
                .filter(mark -> mark >= speciality.getPassmark()).count() == 3;
    }
}
