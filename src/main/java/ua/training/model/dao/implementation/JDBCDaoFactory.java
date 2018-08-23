package ua.training.model.dao.implementation;

import ua.training.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao getUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public UniversityDao getUniversityDao() {
        return new JDBCUniversityDao(getConnection());
    }

    @Override
    public SpecialityDao getSpecialityDao() {
        return new JDBCSpecialityDao(getConnection());
    }

    @Override
    public SubjectDao getSubjectDao() {
        return new JDBCSubjectDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
