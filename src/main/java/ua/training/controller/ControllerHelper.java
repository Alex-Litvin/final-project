package ua.training.controller;

import ua.training.controller.command.*;
import ua.training.controller.command.get.UniversitiesCommand;
import ua.training.controller.command.post.LoginConfirm;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

class ControllerHelper {

    private static Map<String, Command> commandMap = new HashMap<>();

    private static ControllerHelper ourInstance = new ControllerHelper();

    static ControllerHelper getInstance() {
        return ourInstance;
    }

    private ControllerHelper() {
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("login", new LoginConfirm());
        commandMap.put("home", new HomeCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("localization", new LocalizationCommand());
        commandMap.put("addSpeciality", new AddSpecialityCommand());
        commandMap.put("deleteSpeciality", new DeleteSpecialityCommand());
        commandMap.put("showApplicant", new ShowApplicantsCommand());
        commandMap.put("showApplicantPagination", new ShowApplicantsCommand());
        commandMap.put("registerForExam", new ExamRegistrationCommand());
        commandMap.put("showUniversities", new UniversitiesCommand());
        commandMap.put("showDepartmentCommand", new ShowSpecialityCommand());
        commandMap.put("requestForSpeciality", new AddSpecialityRequestCommand());
        commandMap.put("showAvailableSpeciality", new ShowAvailableSpeciality());
        commandMap.put("showSpecialityRating", new ShowSpecialityRatingCommand());
        commandMap.put("showSpecialityRequest", new ShowSpecialityRequestCommand());
//        commandMap.put("showSubjects", new ShowSubjectsCommand());
        commandMap.put("showUniversitiesSubjects", new ShowUniversitiesSubjectsCommand());
        commandMap.put("showAllStudents", new ShowAllStudentsCommand());
        commandMap.put("showStudentExams", new ShowStudentExamsCommand());
        commandMap.put("addExamMark", new AddExamMarkCommand());
        commandMap.put("showSpecialitiesByUniversity", new ShowSpecialitiesByUniversity());
        commandMap.put("completeSpecialityRegistration", new CompleteSpecialityRegistrationCommand());
    }

    Command getCommand(HttpServletRequest request) {
        String key = request.getParameter("command");
        return commandMap.get(key);
    }
}
