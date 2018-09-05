package ua.training.controller.command.get;

import ua.training.controller.command.Command;
import ua.training.controller.utility.Page;
import ua.training.model.dto.SpecialityResultDto;
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
import java.util.stream.Collectors;

public class ShowSpecialityRatingCommand implements Command, Page {

    private SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();
    private UniversityService universityService = ServiceFactoryImpl.getInstance().getUniversityService();
    private ExamService examService = ServiceFactoryImpl.getInstance().getExamService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long specialityId = Long.parseLong(request.getParameter("specialityId"));

        Speciality speciality = specialityService.findById(specialityId);
        University university = universityService.findUniversityBySpecialityId(specialityId);

        List<User> users = specialityService.findAllUsersBySpecialityId(specialityId);

        List<Long> userIds = specialityService.findAllUsersBySpecialityId(specialityId).stream()
                .mapToLong(User::getId)
                .boxed()
                .collect(Collectors.toList());

        List<Exam> userExams = examService.findAllExamsByUserIds(userIds);

        List<SpecialityResultDto> specialityResultDtos = users.stream()
                .map(u -> createSpecialityRequestDto(u, sortedUserExams(u, userExams), speciality, university))
                .collect(Collectors.toList());

        request.setAttribute("specialityResultDtos", specialityResultDtos);

        ShowSpecialityRequestCommand specialityRequestCommand = new ShowSpecialityRequestCommand();
        specialityRequestCommand.execute(request, response);

        return USER_SPECIALITY_RATING + JSP;
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
        specialityResultDto.setStatus(specialityService.getEnterSpecialityStatus(user.getId(), speciality.getId()));

        return specialityResultDto;
    }

    private Integer calculateTotalMark(List<Exam> userExams) {
        return userExams.stream()
                .mapToInt(Exam::getMark)
                .sum();
    }
}
