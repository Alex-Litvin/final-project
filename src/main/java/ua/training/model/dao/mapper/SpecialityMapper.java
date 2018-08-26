package ua.training.model.dao.mapper;

import ua.training.model.entity.Speciality;
import ua.training.model.entity.enums.EnterSpecialityStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialityMapper {

    public Speciality extractFromResultSet(ResultSet rs) throws SQLException {
        Speciality speciality = new Speciality();
        speciality.setId(rs.getLong("id"));
        speciality.setMaxStudentCount(rs.getInt("max_student_count"));
        speciality.setTitle(rs.getString("title"));
        speciality.setPassmark(rs.getInt("passmark"));
        speciality.setStatus(EnterSpecialityStatus.valueOf(rs.getString("entered")));

        return speciality;
    }
}
