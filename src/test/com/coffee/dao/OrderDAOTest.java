package com.coffee.dao;

import com.coffee.logic.*;
import com.coffee.service.Basket;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by root on 15.04.16.
 */
public class OrderDAOTest {

    private Basket basket;

    private OrderDAO orderDAO = new OrderDAO();

    @Before
    public void setup(){
        Drink drink;
        List<Bakery> bakeryList;
        ProductDAO productDAO = new ProductDAO();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            drink = productDAO.findAllDrinks(session).get(0);
            bakeryList = productDAO.findAllBakery(session);
        }
        BasketEntry basketEntry = new BasketEntry(1, drink);
        basket = new Basket();
        basket.putInBasket(basketEntry);

        bakeryList.forEach(product -> {
            BasketEntry basketEntry1 = new BasketEntry(1, product);
            basket.putInBasket(basketEntry1);
        });
    }

    @Test
    public void save() throws Exception {
        Order order = new Order(basket);
        order.setAppartment("221B");
        order.setBuilding("122");


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StatusDAO statusDAO = new StatusDAO();
            order.setStatus(statusDAO.findAll(session).get(0));

            int orderID = orderDAO.save(order, session);
            Order order1 = orderDAO.findByID(orderID , session).get();
            assertFalse(order1.getBasket().getPositions().isEmpty());
            orderDAO.delete(order1, session);
            assertFalse(orderDAO.findByID(orderID, session).isPresent());
        }
    }
}