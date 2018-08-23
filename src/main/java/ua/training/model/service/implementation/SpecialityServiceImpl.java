package ua.training.model.service.implementation;

import ua.training.model.Speciality;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SpecialityDao;
import ua.training.model.service.SpecialityService;

import java.util.List;

public  class SpecialityServiceImpl implements SpecialityService {
    private SpecialityDao specialityDao = DaoFactory.getInstance().getSpecialityDao();

    @Override
    public Long createSpeciality(Speciality speciality) {
        return specialityDao.createSpeciality(speciality);
    }

    @Override
    public List<Long> createSpecialitySubjects(Long specialityId, List<Long> subjectIds) {
        return specialityDao.createSpecialitySubjects(specialityId, subjectIds);
    }

    @Override
    public void markAsDeleted(List<Long> specialityId) {
        specialityDao.markAsDeleted(specialityId);
    }

    @Override
    public List<Speciality> findAllSpecialitiesByUniversityId(Long universityId) {
        return specialityDao.findAllSpecialitiesByUniversityId(universityId);
    }
}
