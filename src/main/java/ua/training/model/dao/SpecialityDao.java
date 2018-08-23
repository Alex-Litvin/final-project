package ua.training.model.dao;

import ua.training.model.Speciality;

import java.util.List;

public interface SpecialityDao {
    Long createSpeciality(Speciality speciality);
    void markAsDeleted(List<Long> specialityId);
    List<Speciality> findAllSpecialitiesByUniversityId(Long universityId);
    List<Long> createSpecialitySubjects(Long specialityId, List<Long> subjectIds);
}
