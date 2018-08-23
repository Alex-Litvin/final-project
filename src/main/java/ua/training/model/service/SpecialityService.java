package ua.training.model.service;

import ua.training.model.Speciality;

import java.util.List;

public interface SpecialityService {
    Long createSpeciality(Speciality speciality);
    List<Long> createSpecialitySubjects(Long specialityId, List<Long> subjectIds);
    void markAsDeleted(List<Long> specialityId);
    List<Speciality> findAllSpecialitiesByUniversityId(Long universityId);
}
