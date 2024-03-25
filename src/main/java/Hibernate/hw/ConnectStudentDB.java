package Hibernate.hw;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ConnectStudentDB {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public StudentNew findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(StudentNew.class, id);
        }
    }

    public void persist(StudentNew studentNew) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(studentNew);
            transaction.commit();
        }
    }

    public void merge(StudentNew studentNew) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(studentNew);
            transaction.commit();
        }
    }

    public void remove(StudentNew studentNew) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(studentNew);
            transaction.commit();
        }
    }

    public List<StudentNew> findStudentsOlderThan20() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Student s WHERE s.age > 20", StudentNew.class).list();
        }
    }
}