package ua.training.model.dao;

import ua.training.model.Speciality;
import ua.training.model.User;

import java.util.List;

public interface SpecialityDao {
    Long createSpeciality(Speciality speciality);
    void markAsDeleted(List<Long> specialityId);
    List<Speciality> findAllSpecialitiesByUniversityId(Long universityId);
    List<Speciality> findAllSpecialitiesByUniversityIds(List<Long> universityIds);
    List<Long> createSpecialitySubjects(Long specialityId, List<Long> subjectIds);
    List<User> findAllUsersBySpecialityId(Long specialityId);
    List<User> findUsersFromTo(Long specialityId, Long currentPage, Long recordsPerPage);
    Long getNumberOfRows(Long specialityId);
    Long createSpecialityRequest(Long userId, Long universityId, Long specialityId);
    List<Speciality> findAll();

    Long findUserIdByUniversityAndSpecialityId(Long universityId, Long specialityId);

    Long countSpecialityRequestsByUserId(Long userId);
}
