package ua.training.model.service.implementation;

import ua.training.model.service.*;

public class ServiceFactoryImpl implements ServiceFactory {

    private static final ServiceFactoryImpl instance = new ServiceFactoryImpl();

    private final UserService userService;
    private final UniversityService universityService;
    private final SpecialityService specialityService;
    private final SubjectService subjectService;
    private final ExamService examService;

    private ServiceFactoryImpl() {
        userService = new UserServiceImpl();
        universityService = new UniversityServiceImpl();
        specialityService = new SpecialityServiceImpl();
        subjectService = new SubjectServiceImpl();
        examService = new ExamServiceImpl();
    }

    public static ServiceFactoryImpl getInstance() {
        if (instance == null) {
            return new ServiceFactoryImpl();
        }
        return instance;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public UniversityService getUniversityService() {
        return universityService;
    }

    @Override
    public SpecialityService getSpecialityService() {
        return specialityService;
    }

    @Override
    public SubjectService getSubjectService() {
        return subjectService;
    }

    @Override
    public ExamService getExamService() {
        return examService;
    }
}
