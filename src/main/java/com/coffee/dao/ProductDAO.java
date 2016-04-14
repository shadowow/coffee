package com.coffee.dao;

import com.coffee.logic.Product;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 14.04.2016.
 */
public class ProductDAO {

    public Integer save(Product product, Session session) {
        session.beginTransaction();
        Integer id = (Integer) session.save(product);
        session.getTransaction().commit();
        return id;
    }

    public void update(Product product, Session session) {
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
    }

    public void delete(Product product, Session session) {
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
    }

    public Optional<Product> findByID(int id, Session session) {
        return Optional.ofNullable(session.get(Product.class, id));
    }

    public List<Product> findAll(Session session) {
        return session.createCriteria(Product.class).list();
    }
}
