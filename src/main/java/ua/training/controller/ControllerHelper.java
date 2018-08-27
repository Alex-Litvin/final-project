package ua.training.controller;

import ua.training.controller.command.*;

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
        commandMap.put("login", new LoginCommand());
        commandMap.put("home", new HomeCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("localization", new LocalizationCommand());
        commandMap.put("addUniversity", new AddUniversityCommand());
        commandMap.put("addSpeciality", new AddSpecialityCommand());
        commandMap.put("deleteSpeciality", new DeleteSpecialityCommand());
        commandMap.put("showApplicant", new ShowApplicantsCommand());
        commandMap.put("showApplicantPagination", new ShowApplicantsCommand());
        commandMap.put("registerForExam", new ExamRegistrationCommand());
        commandMap.put("showUniversities", new ShowUniversitiesCommand());
        commandMap.put("showDepartmentCommand", new ShowSpecialityCommand());
        commandMap.put("requestForSpeciality", new AddSpecialityRequestCommand());
        commandMap.put("showAvailableSpeciality", new ShowAvailableSpeciality());
        commandMap.put("showSpecialityRating", new ShowSpecialityRatingCommand());
        commandMap.put("showSpecialityRequest", new ShowSpecialityRequestCommand());
        commandMap.put("addUniversityCommand", new AddUniversityCommand());
        commandMap.put("deleteUniversity", new DeleteUniversityCommand());
//        commandMap.put("showSubjects", new ShowSubjectsCommand());
        commandMap.put("showUniversitiesSubjects", new ShowUniversitiesSubjectsCommand());
        commandMap.put("showAllStudents", new ShowAllStudents());
        commandMap.put("showStudentExams", new ShowStudentExams());
        commandMap.put("addExamMark", new AddExamMarkCommand());
    }

    Command getCommand(HttpServletRequest request) {
        String key = request.getParameter("command");
        return commandMap.get(key);
    }
}
