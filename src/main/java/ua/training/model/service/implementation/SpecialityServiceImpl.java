package ua.training.model.service.implementation;

import ua.training.model.Speciality;
import ua.training.model.User;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SpecialityDao;
import ua.training.model.enums.Subject;
import ua.training.model.service.SpecialityService;

import java.util.List;

public class SpecialityServiceImpl implements SpecialityService {
    private SpecialityDao specialityDao;// = DaoFactory.getInstance().createSpecialityDao();

    @Override
    public Long createSpeciality(Speciality speciality) {
        return specialityDao.createSpeciality(speciality);
    }

    @Override
    public List<Speciality> findAllSpecialitiesByUniversityId(Long universityId) {
        return specialityDao.findAllSpecialitiesByUniversityId(universityId);
    }

    @Override
    public List<Subject> findAllRequiredSubjectsBySpecialityId(Long specialityId) {
        return specialityDao.findAllRequiredSubjectsBySpecialityId(specialityId);
    }

    @Override
    public List<User> findAllUsersForSpecialityBySpecialityId(Long specialityId) {
        return specialityDao.findAllUsersForSpecialityBySpecialityId(specialityId);
    }
}
