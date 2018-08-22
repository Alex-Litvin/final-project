package ua.training.model.dao.mapper;

import ua.training.model.Speciality;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialityMapper {

    public Speciality extractFromResultSet(ResultSet rs) throws SQLException {
        Speciality speciality = new Speciality();
        speciality.setId(rs.getLong("id"));
        speciality.setTitle(rs.getString("title"));
        speciality.setMaxStudentCount(rs.getInt("max_student_count"));

        return speciality;
    }
}
