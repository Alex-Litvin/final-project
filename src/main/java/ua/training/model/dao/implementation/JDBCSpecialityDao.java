package ua.training.model.dao.implementation;

import ua.training.model.Speciality;
import ua.training.model.User;
import ua.training.model.dao.SpecialityDao;
import ua.training.model.dao.mapper.SpecialityMapper;
import ua.training.model.dao.mapper.UserMapper;

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
    public List<Speciality> findAllSpecialitiesByUniversityIds(List<Long> universityIds) {
        StringBuilder query = new StringBuilder("SELECT * FROM speciality LEFT JOIN university_speciality u on speciality.id = u.speciality_id WHERE deleted = FALSE AND university_id IN (");
        List<Speciality> specialities = new ArrayList<>();
        for (int i = 0; i < universityIds.size(); i++) {
            query = i < (universityIds.size() - 1) ? query.append("?,") : query.append("?)");
        }
        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int i = 1;
            for (Long id : universityIds) {
                ps.setLong(i++, id);
            }
            ResultSet rs = ps.executeQuery();
            SpecialityMapper mapper = new SpecialityMapper();
            while (rs.next()) {
                specialities.add(mapper.extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

    @Override
    public List<User> findAllUsersBySpecialityId(Long specialityId) {
        String query = "SELECT * FROM user LEFT JOIN user_speciality u on user.id = u.user_id WHERE speciality_id = ?";
        List<User> users = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, specialityId);
            ResultSet rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            while (rs.next()) {
                users.add(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return users;
    }

    @Override
    public List<User> findUsersFromTo(Long specialityId, Long currentPage, Long recordsPerPage) {
        String query = "SELECT * FROM user LEFT JOIN user_speciality u on user.id = u.user_id WHERE speciality_id = ? LIMIT ?, ?";
        List<User> users = new ArrayList<>();
        Long start = currentPage * recordsPerPage - recordsPerPage;
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, specialityId);
            ps.setLong(2, start);
            ps.setLong(3, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            while (rs.next()) {
                users.add(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return users;
    }

    @Override
    public Long getNumberOfRows(Long specialityId) {
        Long numOfRows = 0L;
        String query = "SELECT COUNT(user.id) FROM user LEFT JOIN user_speciality u on user.id = u.user_id WHERE speciality_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, specialityId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                numOfRows++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return numOfRows;
    }

    @Override
    public Long createSpecialityRequest(Long userId, Long universityId, Long specialityId) {
        String query = "INSERT INTO user_speciality (user_id, speciality_id, university_id) VALUE (?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, userId);
            ps.setLong(2, specialityId);
            ps.setLong(3, universityId);
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
    public List<Speciality> findAll() {
        String query = "SELECT * FROM speciality WHERE deleted = FALSE";
        List<Speciality> specialities = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            SpecialityMapper mapper = new SpecialityMapper();
            while (rs.next()) {
                specialities.add(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return specialities;
    }
}