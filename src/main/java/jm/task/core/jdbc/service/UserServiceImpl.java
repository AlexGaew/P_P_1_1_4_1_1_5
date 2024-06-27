package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
    Logger log = Logger.getLogger("UserServiceImpl");


    public void createUsersTable() {
        userDao.createUsersTable();
        log.info("Table successfully created...");
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
        log.info("Table  dropped successfully...");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        log.log(Level.INFO, "User a: {0} added to table ", new Object[]{name});

    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        log.log(Level.INFO, "User removed from table : {0}", new Object[]{id});

    }

    public List<User> getAllUsers() {
        log.info("all users are taken from the table successfully...");
        return userDao.getAllUsers();


    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        log.info("Table  cleaner successfully...");
    }

}
