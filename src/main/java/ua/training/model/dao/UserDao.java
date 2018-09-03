package ua.training.model.dao;

import ua.training.model.entity.User;

import java.util.List;

public interface UserDao {

    User findByEmail(String email);

    boolean checkEmail(String email);

    void create(User user);

    User findById(Long id);

    List<User> findAll();

    int getNumberOfRows();

    List<User> findUsersFromTo(int currentPage, int recordsPerPage);
}
