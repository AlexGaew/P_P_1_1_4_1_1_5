package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/myDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";


    public static Connection getConnection() {
        Connection conn = null;
        try  {
            Class.forName(DB_Driver);
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Failed to connect to database");
            throw new RuntimeException(e);

        }
        return conn;
    }


    // реализуйте настройку соеденения с БД
}
