package ua.training.model.dao.implementation;

import ua.training.model.Speciality;
import ua.training.model.User;
import ua.training.model.dao.SpecialityDao;
import ua.training.model.dao.mapper.SpecialityMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.enums.Subject;

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
    public List<Speciality> findAllSpecialitiesByUniversityId(Long universityId) {
        String query = "SELECT * FROM speciality LEFT JOIN university_speciality u on speciality.id = u.speciality_id" +
                " LEFT JOIN required_subject e on speciality.id = e.speciality_id WHERE university_id = ?";
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
    public List<Subject> findAllRequiredSubjectsBySpecialityId(Long specialityId) {
        String query = "SELECT title FROM subject LEFT JOIN required_subject rs on subject.id = rs.subject_id WHERE speciality_id = ?";
        List<Subject> subjects = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, specialityId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjects.add(Subject.valueOf(rs.getString("title")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return subjects;
    }

    @Override
    public List<User> findAllUsersForSpecialityBySpecialityId(Long specialityId) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user LEFT JOIN user_speciality u on user.id = u.user_id WHERE speciality_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, specialityId);
            ResultSet rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            while (rs.next()) {
                users.add(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return users;
    }
}
