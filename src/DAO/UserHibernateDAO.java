package DAO;

import com.sun.java.accessibility.util.SwingEventMonitor;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private final SessionFactory sessionFactory;

    public UserHibernateDAO() {
        sessionFactory = DBHelper.getSessionFactory();
    }

    @Override
    public boolean deleteUser(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createQuery("delete from User where name =:name")
                    .setParameter("name", name)
                    .executeUpdate();
            return true;
        } catch (NullPointerException e){
            return false;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public User getUserByName(String name) {
        Session session = sessionFactory.openSession();
        List<User> list = session.createQuery("from User where name =:name")
                .setParameter("name", name)
                .list();
        session.close();
        return list.get(0);

    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> list = session.createQuery("from User")
                .list();
        session.close();
        return list;
    }

    @Override
    public void addUser(User user) throws Throwable {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void createTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("drop table users").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("create table cars (id bigint not null auto_increment, name varchar(255), password varchar(255), gender varchar(255), primary key (id))")
                .executeUpdate();
        transaction.commit();
        session.close();
    }
}
