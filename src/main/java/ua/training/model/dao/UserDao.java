package ua.training.model.dao;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findByEmail(String email);
    boolean userIsExist(String email, String password);
    Role getRoleByEmailAndPassword(String email, String password);
    boolean checkEmail(String email);
    void create(User user);
    User findById(Long id);
    List<User> findAll();
}
