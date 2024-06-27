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


    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(30), " +
                "lastName VARCHAR(30), " +
                "age INT)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            log.info("Table successfully created...");
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error creating table", e);
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DROP TABLE IF EXISTS users")) {
            preparedStatement.executeUpdate();
            log.info("Table  dropped successfully...");
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error creating table", e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users(name,lastName,age)  VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            log.log(Level.INFO, "User a: {0} added to table ", new Object[]{name});
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            log.log(Level.INFO, "User removed from table : {0}", new Object[]{id});
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM users")) {
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = conn.prepareStatement("TRUNCATE TABLE users")) {
            preparedStatement.executeUpdate();
            log.info("Table  cleaner successfully...");
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
