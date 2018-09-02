package ua.training.model.service;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

import java.util.List;

public interface UserService {
    void create(User user);
    User findByEmail(String email);
    boolean userIsExist(String email, String password);
    boolean checkEmail(String email);
    Role getRoleByEmailAndPassword(String email, String password);
    User findById(Long id);
    List<User> findAll();
    int getNumberOfRows();
    List<User> findUsersFromTo(int currentPage, int recordsPerPage);
}
