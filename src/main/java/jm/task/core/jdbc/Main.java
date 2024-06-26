package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //UserServiceImpl userService = new UserServiceImpl();
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        // create Table
      //  userService.createUsersTable();
        // add 4 users
//        userService.saveUser("Nick", "Poprys", (byte) 54);
//        userService.saveUser("Roi", "Bolur", (byte) 23);
//        userService.saveUser("Pol", "Hardiskon", (byte) 34);
//        userService.saveUser("Jon", "Wey", (byte) 22);
        // print to console all users
   //     userService.getAllUsers().forEach(System.out::println);
        // clear table
    //    userService.cleanUsersTable();
        // delete table
    //    userService.dropUsersTable();

        userDao.createUsersTable();

        userDao.saveUser("Nick", "Poprys", (byte) 54);
        userDao.saveUser("Roi", "Bolur", (byte) 23);
        userDao.saveUser("Pol", "Hardiskon", (byte) 34);
        userDao.saveUser("Jon", "Wey", (byte) 22);

        userDao.getAllUsers().forEach(System.out::println);

        userDao.cleanUsersTable();
        userDao.dropUsersTable();



    }
}
