package main.java.service;

import main.java.DAO.UserDAO;
import main.java.DAO.UserHibernateDAO;
import main.java.DAO.UserJdbcDAO;
import main.java.model.User;

import java.util.List;

public class UserService {
    private static UserService instance;
    private static UserDAO userHibernateDAO;
    private static UserDAO userJdbcDAO;

    private UserService() {
        userHibernateDAO = new UserHibernateDAO();
        userJdbcDAO = new UserJdbcDAO();
    }

    public static UserService instance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    public User getUserByName(String name) {
        return userJdbcDAO.getUserByName(name);
    }

    public List<User> getAllUsers() {
        return userJdbcDAO.getAllUsers();
    }

    public boolean deleteUser(String name) {
        return userJdbcDAO.deleteUser(name);
    }

    public boolean addUser(User user) {
        try {
            if (user.getName() != null && user.getPassword() != null && user.getGender() != null) {
                createTable();
                userJdbcDAO.addUser(user);
                return true;
            }
        } catch (Throwable e) {
            return false;
        }
        return false;
    }

    public void cleanUp() {
        userJdbcDAO.dropTable();
    }

    public void createTable() {
        userJdbcDAO.createTable();
    }


}

