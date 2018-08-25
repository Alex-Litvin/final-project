package ua.training.model.service.implementation;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SubjectDao;
import ua.training.model.enums.Subject;
import ua.training.model.service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private SubjectDao subjectDao = DaoFactory.getInstance().getSubjectDao();

    @Override
    public List<Subject> findAll() {
        return subjectDao.findAll();
    }

    @Override
    public List<Subject> findRequiredSubjectsBySpecialityId(Long specialityId) {
        return subjectDao.findRequiredSubjectsBySpecialityId(specialityId);
    }

    @Override
    public Long create(Subject subject) {
        return subjectDao.create(subject);
    }

    @Override
    public List<Long> getIdsByNames(List<String> subjectTitles) {
        return subjectDao.getIdsByNames(subjectTitles);
    }

    @Override
    public Long getIdByName(String title) {
        return subjectDao.getIdByName(title);
    }

    @Override
    public Subject getSubjectById(Long subjectId) {
        return subjectDao.getSubjectById(subjectId);
    }

    @Override
    public List<Subject> getSubjectsByIds(List<Long> subjectIds) {
        return subjectDao.getSubjectsByIds(subjectIds);
    }
}
