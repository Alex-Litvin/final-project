package ua.training.model.dao;

import ua.training.model.Speciality;
import ua.training.model.User;
import ua.training.model.enums.Subject;

import java.util.List;

public interface SpecialityDao {
    Long createSpeciality(Speciality speciality);
    List<Speciality> findAllSpecialitiesByUniversityId(Long universityId);
    List<Subject> findAllRequiredSubjectsBySpecialityId(Long specialityId);
    List<User> findAllUsersForSpecialityBySpecialityId(Long specialityId);
}
