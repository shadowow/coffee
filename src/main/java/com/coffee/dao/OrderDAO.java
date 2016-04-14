package com.coffee.dao;

import com.coffee.logic.Order;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class OrderDAO {

    public Integer save(Order order, Session session) {
        session.beginTransaction();
        Integer id = (Integer) session.save(order);
        session.getTransaction().commit();
        return id;
    }

    public void update(Order order, Session session) {
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
    }

    public void delete(Order order, Session session) {
        session.beginTransaction();
        session.delete(order);
        session.getTransaction().commit();
    }

    public Optional<Order> findByID(int id, Session session) {
        return Optional.ofNullable(session.get(Order.class, id));
    }

    public List<Order> findAll(Session session) {
        return session.createCriteria(Order.class).list();
    }

}
