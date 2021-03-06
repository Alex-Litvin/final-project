package ua.training.model.service;

import ua.training.model.entity.enums.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> findAll();

    List<Subject> findRequiredSubjectsBySpecialityId(Long specialityId);

    Long create(Subject subject);

    List<Long> getIdsByNames(List<String> subjectTitles);

    Long getIdByName(String title);

    List<Subject> getSubjectsByIds(List<Long> subjectIds);
}
