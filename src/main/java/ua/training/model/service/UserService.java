package ua.training.model.service;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void create(User user);
    Optional<User> findByEmail(String email);
    boolean userIsExist(String email, String password);
    boolean checkEmail(String email);
    boolean checkMobile(String mobile);
    Role getRoleByEmailAndPassword(String email, String password);
    User findById(Long id);
    List<User> findAll();
}
