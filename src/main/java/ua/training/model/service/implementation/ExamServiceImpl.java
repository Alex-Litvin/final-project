package ua.training.model.service.implementation;

import ua.training.model.entity.Exam;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ExamDao;
import ua.training.model.service.ExamService;

import java.util.List;
import java.util.Map;

public class ExamServiceImpl implements ExamService {
    private ExamDao examDao = DaoFactory.getInstance().getExamDao();

    @Override
    public Long addExam(Exam exam) {
        return examDao.addExam(exam);
    }

    @Override
    public Long findExamIdByUserIdAndSubjectId(Long userId, Long subjectId) {
        return examDao.findExamIdByUserIdAndSubjectId(userId, subjectId);
    }

    @Override
    public Long countExamsByUserId(Long userId) {
        return examDao.countExamsByUserId(userId);
    }

    @Override
    public List<Exam> findAllExamsByUserId(Long userId) {
        return examDao.findAllExamsByUserId(userId);
    }

    @Override
    public List<Exam> findAllExamsByUserIds(List<Long> userIds) {
        return examDao.findAllExamsByUserIds(userIds);
    }

}
