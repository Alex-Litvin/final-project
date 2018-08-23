package ua.training.model.dao.implementation;

import ua.training.model.dao.SubjectDao;
import ua.training.model.dao.mapper.SubjectMapper;
import ua.training.model.enums.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSubjectDao implements SubjectDao {
    private Connection connection;

    public JDBCSubjectDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Subject> findAll() {
        String query = "SELECT * FROM subject";
        List<Subject> subjects = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            SubjectMapper mapper = new SubjectMapper();
            while (rs.next()) {
                subjects.add(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return subjects;
    }

    @Override
    public List<Long> getIdsByNames(List<String> subjectTitles) {
        List<Long> subjectIds = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT id FROM subject WHERE title IN (");
        for (int i = 0; i < subjectTitles.size(); i++) {
            query = i < (subjectTitles.size() - 1) ? query.append("?,") : query.append("?)");
        }
        try(PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int i = 1;
            for (String id : subjectTitles) {
                ps.setString(i++, id);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjectIds.add(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return subjectIds;
    }

    @Override
    public Long create(Subject subject) {
        return null;
    }
}
