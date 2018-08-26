package ua.training.model.dao.mapper;

import ua.training.model.entity.enums.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper {
    public Subject extractFromResultSet(ResultSet rs) throws SQLException {
        return Subject.valueOf(rs.getString("title"));
    }
}
