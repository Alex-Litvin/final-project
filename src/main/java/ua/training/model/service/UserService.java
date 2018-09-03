package ua.training.model.service;

import ua.training.model.entity.User;

import java.util.List;

public interface UserService {

    void create(User user);

    User findByEmail(String email);

    boolean checkEmail(String email);

    User findById(Long id);

    List<User> findAll();

    int getNumberOfRows();

    List<User> findUsersFromTo(int currentPage, int recordsPerPage);
}
