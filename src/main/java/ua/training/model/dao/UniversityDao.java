package ua.training.model.dao;

import ua.training.model.University;

import java.util.List;

public interface UniversityDao {
    Long create(University university);
    boolean markAsDeleted(Long universityId);
    University findById(Long id);
    List<University> findAllUniversities();
    Long createUniversitySpeciality(Long universityId, Long specialityId);
    Long update(University university);

    University findUniversityBySpecialityId(Long specialityId);
}
