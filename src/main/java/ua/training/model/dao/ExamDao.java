package ua.training.model.dao;


import ua.training.model.entity.Exam;

import java.util.List;

public interface ExamDao {

    Long addExam(Exam exam);

    Long findExamIdByUserIdAndSubjectId(Long userId, Long subjectId);

    Long countExamsByUserId(Long userId);

    List<Exam> findAllExamsByUserId(Long userId);

    List<Exam> findAllExamsByUserIds(List<Long> userIds);

    boolean update(Exam exam);
}
