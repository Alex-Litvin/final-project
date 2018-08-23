package ua.training.model.dao.implementation;

import ua.training.model.Speciality;
import ua.training.model.dao.SpecialityDao;
import ua.training.model.dao.mapper.SpecialityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCSpecialityDao implements SpecialityDao {
    private Connection connection;

    public JDBCSpecialityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long createSpeciality(Speciality speciality) {
        String query = "INSERT INTO speciality (title, max_student_count) VALUES (?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, speciality.getTitle());
            ps.setInt(2, speciality.getMaxStudentCount());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void markAsDeleted(List<Long> specialityId) {
        StringBuilder query = new StringBuilder("UPDATE speciality SET deleted = TRUE WHERE id IN (");
        for( int i = 0 ; i < specialityId.size(); i++ ) {
            query = i < (specialityId.size() - 1) ? query.append("?,") : query.append("?)");
        }
        try(PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int i = 1;
            for (Long id : specialityId) {
                ps.setLong(i++, id);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Speciality> findAllSpecialitiesByUniversityId(Long universityId) {
        String query = "SELECT * FROM speciality LEFT JOIN university_speciality u on speciality.id = u.speciality_id WHERE deleted = FALSE AND university_id = ?";
        List<Speciality> specialities = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, universityId);
            ResultSet rs = ps.executeQuery();
            SpecialityMapper mapper = new SpecialityMapper();
            while (rs.next()) {
                specialities.add(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return specialities;
    }

    @Override
    public List<Long> createSpecialitySubjects(Long specialityId, List<Long> subjectIds) {
        StringBuilder query = new StringBuilder("INSERT INTO speciality_subject (speciality_id, subject_id) VALUES ");
        for (int i = 0; i < subjectIds.size(); i++) {
            query = i < (subjectIds.size() - 1) ? query.append("("+specialityId+", "+subjectIds.get(i)+"),") : query.append("("+specialityId+", "+subjectIds.get(i)+")");
        }
        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return subjectIds;
    }
}
