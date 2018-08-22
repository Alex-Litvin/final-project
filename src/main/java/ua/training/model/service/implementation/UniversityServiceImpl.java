package ua.training.model.service.implementation;

import ua.training.model.Speciality;
import ua.training.model.University;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UniversityDao;
import ua.training.model.service.UniversityService;

import java.util.List;
import java.util.Map;

public class UniversityServiceImpl implements UniversityService {
    private UniversityDao universityDao = DaoFactory.getInstance().createUniversityDao();

    @Override
    public Long createUniversity(University university) {
        return universityDao.create(university);
    }

    @Override
    public Long createUniversitySpeciality(Long universityId, Long specialityId) {
        return universityDao.createUniversitySpeciality(universityId, specialityId);
    }

    @Override
    public University findUniversityById(Long id) {
        return universityDao.findById(id);
    }

    @Override
    public List<University> findAllUniversities() {
        return universityDao.findAllUniversities();
    }

    @Override
    public Map<University, List<Speciality>> getAllUniversitiesWithSpecialities() {
        return null;
    }
}
