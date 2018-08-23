package ua.training.model.dao;

import ua.training.model.enums.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> findAll();
    List<Long> getIdsByNames(List<String> subjectTitles);
    Long create(Subject subject);
}
