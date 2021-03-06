package ua.training.model.dao.mapper;

import ua.training.model.entity.Exam;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamMapper {
    public Exam extractFromResultSet(ResultSet rs) throws SQLException {
        Exam exam = new Exam();
        exam.setId(rs.getLong("id"));
        exam.setUserId(rs.getLong("user_id"));
        exam.setSubjectId(rs.getLong("subject_id"));
        exam.setTitle(rs.getString("title"));
        exam.setMark(rs.getInt("mark"));

        return exam;
    }
}
