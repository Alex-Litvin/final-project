package ua.training.model.service.implementation;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
import ua.training.model.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public User findByEmail(String email) {
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
    public Role getRoleByEmailAndPassword(String email, String password) {
        return userDao.getRoleByEmailAndPassword(email, password);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public int getNumberOfRows() {
        return userDao.getNumberOfRows();
    }

    @Override
    public List<User> findUsersFromTo(int currentPage, int recordsPerPage) {
        return userDao.findUsersFromTo(currentPage, recordsPerPage);
    }
}
