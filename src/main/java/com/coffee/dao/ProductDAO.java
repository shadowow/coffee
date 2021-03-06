package com.coffee.dao;

import com.coffee.logic.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by user on 14.04.2016.
 */
public class ProductDAO {

    private DAO dao = new DAO();

    public Integer save(Product product, Session session) {
        session.beginTransaction();
        Integer id = (Integer) session.save(product);
        session.getTransaction().commit();
        return id;
    }

    public void save(Product product) {
        try(Session session = dao.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
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
        try(Session session = dao.openSession())  {
            Product product = session.get(Product.class, id);
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BasketEntry.class);
            criteria.add(Restrictions.eq("product", product));
            List<BasketEntry> basketEntryList = criteria.list();
            if (!basketEntryList.isEmpty()) {
                Set<Integer> orderNumbersSet = new HashSet<>();
                basketEntryList.forEach(entry -> {
                    Order order = entry.getOrder();
                    orderNumbersSet.add(order.getNumber());
                    order.removeFromBasket(entry);
                    session.update(order);
                    session.delete(entry);
                });
                orderNumbersSet.forEach(orderNumber -> {
                    Order order = session.get(Order.class, orderNumber);
                    if (order.getBasket().isEmpty()) {
                        session.delete(order);
                    }
                });
            }
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public Optional<Product> findByID(int id, Session session) {
        return Optional.ofNullable(session.get(Product.class, id));
    }

    public Optional<Product> findByID(int id) {
        try(Session session = dao.openSession())  {
            return Optional.ofNullable(session.get(Product.class, id));
        }
    }

    public Optional<Bakery> findBakeryByID(int id) {
        try(Session session = dao.openSession()) {
            return Optional.ofNullable(session.get(Bakery.class, id));
        }
    }

    public Optional<Drink> findDrinkByID(int id) {
        try(Session session = dao.openSession()) {
            return Optional.ofNullable(session.get(Drink.class, id));
        }
    }

    public Optional<Desert> findDesertByID(int id) {
        try(Session session = dao.openSession())  {
            return Optional.ofNullable(session.get(Desert.class, id));
        }
    }

    public List<Product> findAll(Session session) {
        return session.createCriteria(Product.class).list();
    }

    public List<Drink> findAllDrinks(Session session) {
        return session.createCriteria(Drink.class).list();
    }

    public List<Drink> findAllDrinks() {
        try(Session session = dao.openSession())  {
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
        try(Session session = dao.openSession())  {
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
        try(Session session = dao.openSession())  {
            return session.createCriteria(Desert.class).list();
        }
    }
}
