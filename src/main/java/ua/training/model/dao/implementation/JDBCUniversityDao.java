package ua.training.model.dao.implementation;

import ua.training.model.entity.University;
import ua.training.model.dao.UniversityDao;
import ua.training.model.dao.mapper.UniversityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUniversityDao implements UniversityDao {
    private Connection connection;

    public JDBCUniversityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(University entity) {
        String query = "INSERT INTO university (title) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getTitle());
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
    public boolean markAsDeleted(Long universityId) {
        String query = "UPDATE university SET deleted = TRUE WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, universityId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public University findById(Long id) {
        String query = "SELECT * FROM university WHERE id = ?";
        University university = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            UniversityMapper mapper = new UniversityMapper();
            if (rs.next()) {
                university = mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return university;
    }

    @Override
    public List<University> findAllUniversities() {
        String query = "SELECT * FROM university WHERE deleted = FALSE";
        List<University> universities = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            UniversityMapper mapper = new UniversityMapper();
            while (rs.next()) {
                universities.add(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return universities;
    }

    @Override
    public Long createUniversitySpeciality(Long universityId, Long specialityId) {
        String query = "INSERT INTO university_speciality (university_id, speciality_id) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, universityId);
            ps.setLong(2, specialityId);
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
    public Long update(University university) {
        String query = "UPDATE university SET title = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, university.getTitle());
            ps.setLong(2, university.getId());
            ps.executeUpdate();
            return university.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public University findUniversityBySpecialityId(Long specialityId) {
        String query = "SELECT * FROM university LEFT JOIN university_speciality u on university.id = u.university_id  WHERE deleted = FALSE AND speciality_id = ?";
        University university = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, specialityId);
            ResultSet rs = ps.executeQuery();
            UniversityMapper mapper = new UniversityMapper();
            if (rs.next()) {
                university = mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return university;

    }
}
