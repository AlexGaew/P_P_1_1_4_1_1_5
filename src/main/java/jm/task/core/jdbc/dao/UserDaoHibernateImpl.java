package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    Logger log = Logger.getLogger(this.getClass().getName());
    SessionFactory sessionFactory = getSessionFactory();

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "  id BIGINT(20) NOT NULL AUTO_INCREMENT," +
                    "  name VARCHAR(45) NOT NULL," +
                    "  lastName VARCHAR(45) NOT NULL," +
                    "  age TINYINT(3) NOT NULL," +
                    "  PRIMARY KEY (id))";
            session.createSQLQuery(sql).executeUpdate();
            log.info("Created users table");
        } catch (HibernateException e) {
            log.log(Level.SEVERE, "Error creating users table", e);
        }
    }


    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sql).executeUpdate();
            log.info("DROP TABLE IF EXISTS users");
        } catch (HibernateException e) {
            log.log(Level.SEVERE, "Error dropping users table", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            log.log(Level.INFO, "User a: {0} added to table ", new Object[]{name});
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.log(Level.SEVERE, "Error saving user", e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete User where id = :id").setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.log(Level.SEVERE, "Error removing user", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            log.log(Level.SEVERE, "Error getting all users", e);
        }
        log.info("all users are taken from the table successfully...");
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.log(Level.SEVERE, "Error cleaning users table", e);
        }
    }
}