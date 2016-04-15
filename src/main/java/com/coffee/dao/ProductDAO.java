package com.coffee.dao;

import com.coffee.logic.Bakery;
import com.coffee.logic.Desert;
import com.coffee.logic.Drink;
import com.coffee.logic.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
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

    public Integer save(Product product) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Integer id = (Integer) session.save(product);
            session.getTransaction().commit();
            return id;
        }
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

    public void delete(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product product = session.get(Product.class, id);
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public Optional<Product> findByID(int id, Session session) {
        return Optional.ofNullable(session.get(Product.class, id));
    }

    public Optional<Product> findByID(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Product.class, id));
        }
    }

    public List<Product> findAll(Session session) {
        return session.createCriteria(Product.class).list();
    }

    public List<Drink> findAllDrinks(Session session) {
        return session.createCriteria(Drink.class).list();
    }

    public List<Drink> findAllDrinks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createCriteria(Drink.class).list();
        }
    }

    public List<Drink> findDrinksByHot(boolean hot, Session session) {
        Criteria criteria = session.createCriteria(Drink.class);
        criteria.add(Restrictions.eq("hot", hot));
        return criteria.list();
    }

    public List<Bakery> findAllBakery(Session session) {
        return session.createCriteria(Bakery.class).list();
    }

    public List<Bakery> findAllBakery() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createCriteria(Bakery.class).list();
        }
    }

    public List<Bakery> findBakeryByDate(Timestamp date, Session session) {
        Criteria criteria = session.createCriteria(Bakery.class);
        criteria.add(Restrictions.eq("date", date));
        return criteria.list();
    }

    public List<Desert> findAllDeserts(Session session) {
        return session.createCriteria(Desert.class).list();
    }

    public List<Desert> findAllDeserts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createCriteria(Desert.class).list();
        }
    }
}
