package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Util {
    static Logger log = Logger.getLogger(Util.class.getName());

    private Util() {
    }

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/myDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";


    public static SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration().addAnnotatedClass(User.class);
        return cfg.buildSessionFactory();
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            log.info("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            log.info("Failed to connect to database");
        }
        return conn;
    }


    // реализуйте настройку соеденения с БД
}
