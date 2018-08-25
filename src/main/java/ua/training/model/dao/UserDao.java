package ua.training.model.dao;

import ua.training.model.User;
import ua.training.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmail(String email);
    boolean userIsExist(String email, String password);
    Role getRoleByEmailAndPassword(String email, String password);
    boolean checkEmail(String email);
    boolean checkMobile(String mobile);
}
