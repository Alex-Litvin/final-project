package ua.training.model.dao;

import ua.training.model.entity.enums.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> findAll();
    List<Long> getIdsByNames(List<String> subjectTitles);
    Long create(Subject subject);
    List<Subject> findRequiredSubjectsBySpecialityId(Long specialityId);
    Long getIdByName(String title);
    Subject getSubjectById(Long subjectId);
    List<Subject> getSubjectsByIds(List<Long> subjectIds);
}
