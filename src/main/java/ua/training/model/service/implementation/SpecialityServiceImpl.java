package ua.training.model.service.implementation;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SpecialityDao;
import ua.training.model.dao.SubjectDao;
import ua.training.model.entity.Speciality;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.EnterSpecialityStatus;
import ua.training.model.entity.enums.SpecialityStatus;
import ua.training.model.service.SpecialityService;

import java.util.List;
import java.util.stream.Collectors;

public  class SpecialityServiceImpl implements SpecialityService {

    private SpecialityDao specialityDao = DaoFactory.getInstance().getSpecialityDao();
    private SubjectDao subjectDao = DaoFactory.getInstance().getSubjectDao();

    @Override
    public Long createSpeciality(Speciality speciality) {
        return specialityDao.createSpeciality(speciality);
    }

    @Override
    public Speciality findById(Long specialityId) {
        return specialityDao.findById(specialityId);
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

    @Override
    public List<Speciality> findAllSpecialitiesByUniversityIds(List<Long> universityIds) {
        return specialityDao.findAllSpecialitiesByUniversityIds(universityIds);
    }

    @Override
    public List<User> findAllUsersBySpecialityId(Long specialityId) {
        return specialityDao.findAllUsersBySpecialityId(specialityId);
    }

    @Override
    public List<Speciality> findAll() {
        return specialityDao.findAll();
    }

    @Override
    public List<Speciality> findAllSpecialitiesWithSubjectsByUniversityIds(List<Long> universityIds) {
        return findAllSpecialitiesByUniversityIds(universityIds).stream()
                .peek(s -> s.setRequiredSubject(subjectDao.findRequiredSubjectsBySpecialityId(s.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public Long createSpecialityRequest(Long userId, Long universityId, Long specialityId) {
        return specialityDao.createSpecialityRequest(userId, universityId, specialityId);
    }

    @Override
    public Long findUserIdByUniversityAndSpecialityId(Long universityId, Long specialityId) {
        return specialityDao.findUserIdByUniversityAndSpecialityId(universityId, specialityId);
    }

    @Override
    public Long countSpecialityRequestsByUserId(Long userId) {
        return specialityDao.countSpecialityRequestsByUserId(userId);
    }

    @Override
    public List<Speciality> findAllSpecialitiesByUserId(Long userId) {
        return specialityDao.findAllSpecialitiesByUserId(userId);
    }

    @Override
    public EnterSpecialityStatus getEnterSpecialityStatus(Long userId, Long specialityId) {
        return specialityDao.getEnterSpecialityStatus(userId, specialityId);
    }

    @Override
    public void setEnterSpecialityStatus(List<Long> userIds, Long specialityId, EnterSpecialityStatus status) {
        specialityDao.setEnterSpecialityStatus(userIds, specialityId, status);
    }

    @Override
    public void updateStatus(Long specialityId, SpecialityStatus status) {
        specialityDao.updateStatus(specialityId, status);
    }
}
