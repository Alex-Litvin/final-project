package ua.training.model.service;

import ua.training.model.Exam;

import java.util.List;

public interface ExamService {
    Long addExam(Exam exam);
    Long findExamIdByUserIdAndSubjectId(Long userId, Long subjectId);
    Long countExamsByUserId(Long userId);
    List<Exam> findAllExamsByUserId(Long userId);
}
