package ua.training.controller.command;

import ua.training.model.entity.*;
import ua.training.model.entity.enums.EnterSpecialityStatus;
import ua.training.model.entity.enums.Subject;
import ua.training.model.service.*;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShowSpecialityRatingCommand implements Command {

    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();
    private SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        Long specialityId = Long.parseLong(request.getParameter("specialityId"));

        Speciality speciality = specialityService.findById(specialityId);
        University university = universityService.findUniversityBySpecialityId(specialityId);

        List<User> users = specialityService.findAllUsersBySpecialityId(specialityId);

        List<Long> userIds = specialityService.findAllUsersBySpecialityId(specialityId).stream()
                .mapToLong(User::getId)
                .boxed()
                .collect(Collectors.toList());

        List<Exam> userExams = examService.findAllExamsByUserIds(userIds);

//        List<Exam> requiredExams = specialityService.findRequiredExamsById(specialityId);

        List<SpecialityResultDto> specialityResultDtos = users.stream()
                .map(u -> createSpecialityRequestDto(u, sortedUserExams(u, userExams), speciality, university))
                .collect(Collectors.toList());

        specialityResultDtos.forEach(System.out::println);

        request.setAttribute("specialityResultDtos", specialityResultDtos);

        return "/view/userbasic.jsp";
    }

    private List<Exam> sortedUserExams(User user, List<Exam> exams) {
        return exams.stream()
                .filter(exam -> exam.getUserId().equals(user.getId()))
                .sorted(Comparator.comparing(Exam::getTitle))
                .collect(Collectors.toList());
    }

    private SpecialityResultDto createSpecialityRequestDto(User user, List<Exam> userExams, Speciality speciality, University university) {
        SpecialityResultDto specialityResultDto = new SpecialityResultDto();
        specialityResultDto.setFirstName(user.getFirstName());
        specialityResultDto.setMiddleName(user.getMiddleName());
        specialityResultDto.setSecondName(user.getSecondName());
        specialityResultDto.setSpecialityTitle(speciality.getTitle());
        specialityResultDto.setUniversityTitle(university.getTitle());
        specialityResultDto.setMaxStudentCount(speciality.getMaxStudentCount());
        specialityResultDto.setSpecialityPassmark(speciality.getPassmark());
        specialityResultDto.setUserExams(userExams);
        specialityResultDto.setTotalUserMark(calculateTotalMark(userExams));
        specialityResultDto.setStatus(speciality.getStatus());

        return specialityResultDto;
    }

    private Integer calculateTotalMark(List<Exam> userExams) {
        return userExams.stream()
                .mapToInt(Exam::getMark)
                .sum();
    }
}
