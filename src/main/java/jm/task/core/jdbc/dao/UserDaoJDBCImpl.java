package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class UserDaoJDBCImpl implements UserDao {
    Connection conn = Util.getConnection();
    Logger log = Logger.getLogger("UserDaoJDBCImpl");


    public void createUsersTable() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(30), " +
                    "lastName VARCHAR(30), " +
                    "age INT)";

            stmt.executeUpdate(sql);
            log.info("Table successfully created...");
        }
    }

    public void dropUsersTable() throws SQLException {

        try (Statement stmt = conn.createStatement()) {
            String sql = "DROP TABLE IF EXISTS users";
            stmt.executeUpdate(sql);
            log.info("Table  dropped successfully...");
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users(name,lastName,age)  VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            log.log(Level.INFO, "User a: {0} added to table ", new Object[]{name});

            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        }

    }

    public void removeUserById(long id) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            log.info("Users by Id removed successfully...");
        }

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Statement stmt = conn.createStatement()) {
            stmt.executeQuery("SELECT * " +
                    " FROM users");
            ResultSet resultSet = stmt.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                users.add(user);
            }
        }

        return users;
    }

    public void cleanUsersTable() throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("TRUNCATE TABLE users")) {
            stmt.executeUpdate();
            log.info("Table  cleaner successfully...");
        }
    }
}
