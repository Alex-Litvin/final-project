package ua.training.model.service;

import ua.training.model.Speciality;
import ua.training.model.University;

import java.util.List;
import java.util.Map;

public interface UniversityService {
    Long createUniversity(University university);
    boolean deleteById(Long universityId);
    Long update(University university);
    Long createUniversitySpeciality(Long universityId, Long specialityId);
    University findUniversityById(Long id);
    University findUniversityBySpecialityId(Long specialityId);
    List<University> findAllUniversities();
    List<University> getAllUniversitiesWithSpecialities();
}