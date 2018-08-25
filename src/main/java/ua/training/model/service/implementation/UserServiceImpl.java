package ua.training.model.service.implementation;

import ua.training.model.User;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.enums.Role;
import ua.training.model.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean userIsExist(String email, String password) {
        return userDao.userIsExist(email, password);
    }

    @Override
    public boolean checkEmail(String email) {
        return userDao.checkEmail(email);
    }

    @Override
    public boolean checkMobile(String mobile) {
        return userDao.checkMobile(mobile);
    }

    @Override
    public Role getRoleByEmailAndPassword(String email, String password) {
        return userDao.getRoleByEmailAndPassword(email, password);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }
}
