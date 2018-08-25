package ua.training.model.dao.implementation;

import ua.training.model.Exam;
import ua.training.model.dao.ExamDao;
import ua.training.model.dao.mapper.ExamMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCExamDao implements ExamDao {
    private Connection connection;

    public JDBCExamDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long addExam(Exam exam) {
        String query = "INSERT INTO exam (user_id, subject_id) VALUES (?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, exam.getUserId());
            ps.setLong(2, exam.getSubjectId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Long findExamIdByUserIdAndSubjectId(Long userId, Long subjectId) {
        String query = "SELECT id FROM exam WHERE user_id = ? AND subject_id = ?";
        Long examId = null;
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, userId);
            ps.setLong(2, subjectId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                examId = rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return examId;
    }

    @Override
    public Long countExamsByUserId(Long userId) {
        String query = "SELECT COUNT(user_id) FROM exam WHERE user_id = ?";
        long count;
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return count;
    }

    @Override
    public List<Exam> findAllExamsByUserId(Long userId) {
        String query = "SELECT * FROM exam WHERE user_id = ?";
        List<Exam> exams = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            ExamMapper mapper = new ExamMapper();
            while (rs.next()) {
                exams.add(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return exams;
    }
}
