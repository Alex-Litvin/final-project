package ua.training.model.dao.implementation;

import ua.training.model.User;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        String query = "INSERT INTO user (first_name, second_name, middle_name," +
                "role, password, mobile, email, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getSecondName());
            ps.setString(3, entity.getMiddleName());
            ps.setString(4, entity.getRole().name());
            ps.setString(5, entity.getPassword());
            ps.setString(6, entity.getMobile());
            ps.setString(7, entity.getEmail());
            ps.setString(8, entity.getStatus().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public User findById(Long id) {
        String query = "SELECT * FROM user WHERE id = ?";
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        ;
        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        Optional<User> result = Optional.empty();
        try (PreparedStatement ps = connection.prepareCall(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (rs.next()) {
                result = Optional.of(userMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public boolean userIsExist(String email, String password) {
        String query = "SELECT * FROM user WHERE email = ? AND  password = ?";
        boolean result = false;
        try (PreparedStatement ps = connection.prepareCall(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public Role getRoleByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM user WHERE email = ? AND  password = ?";
        Role result = Role.GUEST;
        try (PreparedStatement ps = connection.prepareCall(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (rs.next()) {
                result = userMapper.extractFromResultSet(rs).getRole();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public boolean checkEmail(String email) {
        String query = "SELECT true FROM user WHERE email = ? LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return false;
    }

    @Override
    public boolean checkMobile(String mobile) {
        String query = "SELECT true FROM user WHERE mobile = ? LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, mobile);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}