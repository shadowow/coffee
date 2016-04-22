package com.coffee.dao;

import com.coffee.logic.Order;
import com.coffee.logic.Product;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAO {

    public Integer save(Order order, Session session) {
        session.beginTransaction();
        order.getBasket().forEach(pos -> {
            Product product = pos.getProduct();
            product.setCount(product.getCount() - pos.getCount());
            session.update(product);
        });
        Integer id = (Integer) session.save(order);
        session.getTransaction().commit();
        return id;
    }

    public Integer save(Order order) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            order.getBasket().forEach(pos -> {
                Product product = pos.getProduct();
                if (product.getCount() != -1) {
                    product.setCount(product.getCount() - pos.getCount());
                }
                session.update(product);
            });
            Integer id = (Integer) session.save(order);
            session.getTransaction().commit();
            return id;
        }
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

    public void delete(int id, Session session) {
        Order order = session.get(Order.class, id);
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

    public List<Order> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createCriteria(Order.class).list();
        }
    }
}
