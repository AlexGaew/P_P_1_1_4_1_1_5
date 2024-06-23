package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends Util implements UserService {
    Connection conn = Util.getConnection();

    public void createUsersTable() {
        Statement stmt = null;


        try {
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(30), " +
                    "lastName VARCHAR(30), " +
                    "age INT)";

            stmt.executeUpdate(sql);
            System.out.println("Table successfully created...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String sql = "DROP TABLE IF EXISTS users";
            stmt.executeUpdate(sql);
            System.out.println("Table  dropped successfully...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement("INSERT INTO users(name,lastName,age)  VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            System.out.println("User " + name + "addet to table");
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
         //   System.out.println("Users by Id saved successfully...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("Users by Id removed successfully...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Statement stmt = null;

        try {
            Statement statement = conn.createStatement();
            statement.executeQuery("SELECT * FROM users");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                users.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return users;
    }

    public void cleanUsersTable() {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("TRUNCATE TABLE users");
            stmt.executeUpdate();
            System.out.println("Table  cleaner successfully...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
