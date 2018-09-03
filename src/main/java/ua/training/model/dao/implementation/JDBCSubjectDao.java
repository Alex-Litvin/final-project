package ua.training.model.dao.implementation;

import ua.training.model.dao.SubjectDao;
import ua.training.model.dao.mapper.SubjectMapper;
import ua.training.model.entity.enums.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSubjectDao implements SubjectDao {

    private Connection connection;

    JDBCSubjectDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Subject> findAll() {
        String query = "SELECT * FROM subject";
        List<Subject> subjects = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            SubjectMapper mapper = new SubjectMapper();
            while (rs.next()) {
                subjects.add(mapper.extractFromResultSet(rs));
            }
            return subjects;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Long> getIdsByNames(List<String> subjectTitles) {
        List<Long> subjectIds = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT id FROM subject WHERE title IN (");
        for (int i = 0; i < subjectTitles.size(); i++) {
            query = i < (subjectTitles.size() - 1) ? query.append("?,") : query.append("?)");
        }
        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int i = 1;
            for (String id : subjectTitles) {
                ps.setString(i++, id);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjectIds.add(rs.getLong("id"));
            }
            return subjectIds;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Long create(Subject subject) {
        return null;
    }

    @Override
    public List<Subject> findRequiredSubjectsBySpecialityId(Long specialityId) {
        String query = "SELECT * FROM subject LEFT JOIN speciality_subject ss on subject.id = ss.subject_id WHERE speciality_id = ?";
        List<Subject> subjects = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, specialityId);
            ResultSet rs = ps.executeQuery();
            SubjectMapper mapper = new SubjectMapper();
            while (rs.next()) {
                subjects.add(mapper.extractFromResultSet(rs));
            }
            return subjects;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Long getIdByName(String title) {
        String query = "SELECT id FROM subject WHERE title = ?";
        Long subjectId = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subjectId = rs.getLong("id");
            }
            return subjectId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Subject> getSubjectsByIds(List<Long> subjectIds) {
        StringBuilder query = new StringBuilder("SELECT * FROM subject WHERE id IN (");
        List<Subject> subjects = new ArrayList<>();
        for (int i = 0; i < subjectIds.size(); i++) {
            query = i < (subjectIds.size() - 1) ? query.append("?,") : query.append("?)");
        }
        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int i = 1;
            for (Long id : subjectIds) {
                ps.setLong(i++, id);
            }
            ResultSet rs = ps.executeQuery();
            SubjectMapper mapper = new SubjectMapper();
            while (rs.next()) {
                subjects.add(mapper.extractFromResultSet(rs));
            }
            return subjects;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
