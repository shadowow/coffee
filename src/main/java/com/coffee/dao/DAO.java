package com.coffee.dao;

/**
 * Created by Юленька on 28.03.2016.
 */

import org.hibernate.Session;
import java.util.List;

public class DAO {

    private Session session;

    public Session openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }

    public void closeSession() {
        if (session.isOpen()) {
            session.close();
        }
    }

    /*public QueryResult add(Object object) {
        int id;
        try (Session session = openSession()) {
            session.beginTransaction();
            id = (Integer) session.save(object);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, id);
    }

    public QueryResult update(Object object) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, null);
    }

    public QueryResult delete(Object object) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, null);
    }

    public QueryResult getById(int id, Class objClass) {
        Object object;
        try {
            object = session.get(objClass, id);
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, object);
    }

    public QueryResult listAll(Class objClass) {
        List<Object> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            list = session.createCriteria(objClass).list();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }*/
}
