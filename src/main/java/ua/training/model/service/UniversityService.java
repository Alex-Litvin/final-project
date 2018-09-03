package ua.training.model.service;

import ua.training.model.entity.University;

import java.util.List;

public interface UniversityService {

    Long createUniversity(University university);

    boolean deleteById(Long universityId);

    Long createUniversitySpeciality(Long universityId, Long specialityId);

    University findUniversityById(Long id);

    University findUniversityBySpecialityId(Long specialityId);

    Long checkIsExists(String title);

    List<University> findAllUniversities();
}
