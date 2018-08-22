package ua.training.model.service;

import ua.training.model.Speciality;
import ua.training.model.User;
import ua.training.model.enums.Subject;

import java.util.List;

public interface SpecialityService {
    Long createSpeciality(Speciality speciality);
    List<Speciality> findAllSpecialitiesByUniversityId(Long universityId);
    List<Subject> findAllRequiredSubjectsBySpecialityId(Long specialityId);
    List<User> findAllUsersForSpecialityBySpecialityId(Long specialityId);
}
