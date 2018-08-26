package ua.training.model.dao.mapper;

import ua.training.model.entity.University;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UniversityMapper {

    public University extractFromResultSet(ResultSet rs) throws SQLException {
        University university = new University();
        university.setId(rs.getLong("id"));
        university.setTitle(rs.getString("title"));

        return university;
    }
}
