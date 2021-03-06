package ua.training.model.service.implementation;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UniversityDao;
import ua.training.model.entity.University;
import ua.training.model.service.UniversityService;

import java.util.List;

public class UniversityServiceImpl implements UniversityService {

    private UniversityDao universityDao = DaoFactory.getInstance().getUniversityDao();

    @Override
    public Long createUniversity(University university) {
        return universityDao.create(university);
    }

    @Override
    public boolean deleteById(Long universityId) {
        return universityDao.markAsDeleted(universityId);
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
    public University findUniversityBySpecialityId(Long specialityId) {
        return universityDao.findUniversityBySpecialityId(specialityId);
    }

    @Override
    public Long checkIsExists(String title) {
        return universityDao.checkIsExists(title);
    }

    @Override
    public List<University> findAllUniversities() {
        return universityDao.findAllUniversities();
    }

}
