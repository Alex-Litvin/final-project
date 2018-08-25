package ua.training.model.service;

import ua.training.model.Speciality;
import ua.training.model.User;

import java.util.List;

public interface SpecialityService {
    Long createSpeciality(Speciality speciality);
    List<Long> createSpecialitySubjects(Long specialityId, List<Long> subjectIds);
    void markAsDeleted(List<Long> specialityId);
    List<Speciality> findAllSpecialitiesByUniversityId(Long universityId);
    List<Speciality> findAllSpecialitiesByUniversityIds(List<Long> universityIds);
    List<User> findAllUsersBySpecialityId(Long specialityId);
    List<User> findUsersFromTo(Long specialityId, Long currentPage, Long recordsPerPage);
    Long getNumberOfRows(Long specialityId);
    List<Speciality> findAll();
    List<Speciality> findAllSpecialitiesWithSubjectsByUniversityIds(List<Long> universityId);
    Long createSpecialityRequest(Long userId, Long universityId, Long specialityId);
}
