package peaksoft.dao;
import org.hibernate.Session;
import org.hibernate.SessionException;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.buildSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("""
                    create table  users(
                                          "  id       serial       primary key not null," +
                                          "  name     varchar(50) not null," +
                                         "  lastname varchar(100) NOT NULL," +
                                          "  age      integer     NOT NULL)";
                    """);
            session.getTransaction().commit();
            System.out.println("Successfully created");
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.buildSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS USERS");
            System.out.println("The users table  successfully deleted");
            session.getTransaction().commit();
            session.close();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.buildSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("The data was successfully added to the users table");
            session.close();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.buildSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete User where id = :id")
                    .setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = Util.buildSessionFactory().openSession();
        try {
            session.beginTransaction();
            users = session.createQuery("""
                    from User
                    """).list();
            session.getTransaction().commit();
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession().openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("successfully cleared");
        } catch (SessionException e) {
            System.out.println(e.getMessage());
        }
    }
}

