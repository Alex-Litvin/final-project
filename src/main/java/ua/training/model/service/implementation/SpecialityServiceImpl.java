package ua.training.model.service.implementation;

import ua.training.model.Speciality;
import ua.training.model.User;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SpecialityDao;
import ua.training.model.dao.SubjectDao;
import ua.training.model.enums.Subject;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.SubjectService;

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
    public List<User> findUsersFromTo(Long specialityId, Long currentPage, Long recordsPerPage) {
        return specialityDao.findUsersFromTo(specialityId, currentPage, recordsPerPage);
    }

    @Override
    public Long getNumberOfRows(Long specialityId) {
        return specialityDao.getNumberOfRows(specialityId);
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
}
