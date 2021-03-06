package ua.training.model.service;

import ua.training.model.entity.Exam;

import java.util.List;

public interface ExamService {

    Long addExam(Exam exam);

    boolean update(Exam exam);

    Long findExamIdByUserIdAndSubjectId(Long userId, Long subjectId);

    Long countExamsByUserId(Long userId);

    List<Exam> findAllExamsByUserId(Long userId);

    List<Exam> findAllExamsByUserIds(List<Long> userIds);
}
