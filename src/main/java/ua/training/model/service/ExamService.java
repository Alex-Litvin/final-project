package ua.training.model.service;

import ua.training.model.entity.Exam;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Map;

public interface ExamService {
    Long addExam(Exam exam);
    Long findExamIdByUserIdAndSubjectId(Long userId, Long subjectId);
    Long countExamsByUserId(Long userId);
    List<Exam> findAllExamsByUserId(Long userId);
    List<Exam> findAllExamsByUserIds(List<Long> userIds);
}
