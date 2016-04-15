package com.coffee.dao;

import com.coffee.logic.Status;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 15.04.2016.
 */
public class StatusDAO {

    public Integer save(Status status, Session session) {
        session.beginTransaction();
        Integer id = (Integer) session.save(status);
        session.getTransaction().commit();
        return id;
    }

    public void update(Status status, Session session) {
        session.beginTransaction();
        session.update(status);
        session.getTransaction().commit();
    }

    public void delete(Status status, Session session) {
        session.beginTransaction();
        session.delete(status);
        session.getTransaction().commit();
    }

    public Optional<Status> findByID(int id, Session session) {
        return Optional.ofNullable(session.get(Status.class, id));
    }

    public Optional<Status> findByName(String status) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Status.class);
            criteria.add(Restrictions.eq("status", status));
            return Optional.ofNullable((Status)criteria.list().get(0));
        }
    }

    public List<Status> findAll(Session session) {
        return session.createCriteria(Status.class).list();
    }
}
