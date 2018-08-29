package ua.training.model.dao.implementation;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

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
                "role, password, email, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getSecondName());
            ps.setString(3, entity.getMiddleName());
            ps.setString(4, entity.getRole().name());
            ps.setString(5, entity.getPassword());
            ps.setString(6, entity.getEmail());
            ps.setString(7, entity.getStatus().name());
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
        String query = "SELECT * FROM user";
        //user_speciality u on user.id = u.user_id
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                users.add(userMapper.extractFromResultSet(rs));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
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
}
