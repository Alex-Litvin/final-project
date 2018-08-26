package ua.training.model.dao.mapper;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
import ua.training.model.entity.enums.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setSecondName(rs.getString("second_name"));
        user.setMiddleName(rs.getString("middle_name"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setPassword(rs.getString("password"));
        user.setMobile(rs.getString("mobile"));
        user.setEmail(rs.getString("email"));
        user.setStatus(Status.valueOf(rs.getString("status")));

        return user;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User entity) {
        return null;
    }
}
