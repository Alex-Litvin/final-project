package ua.training.model.service;

import ua.training.model.enums.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> findAll();
    Long create(Subject subject);
    List<Long> getIdsByNames(List<String> subjectTitles);
}
