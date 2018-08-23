package ua.training.model.service;

public interface ServiceFactory {
    UserService getUserService();
    UniversityService getUniversityService();
    SpecialityService getSpecialityService();
    SubjectService getSubjectService();
}
