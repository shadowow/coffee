package com.coffee.dao;

import com.coffee.logic.*;
import com.coffee.service.Basket;
import com.coffee.service.BasketsRegistry;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by root on 15.04.16.
 */
public class OrderDAOTest {

    private BasketsRegistry basketsRegistry = BasketsRegistry.getInstance();

    private Basket basket;

    @Before
    public void setup(){
        Product drink = new Drink();
        Product food = new Desert();
        drink.setID(1);
        food.setID(2);
        drink.setName("Cola");
        food.setName("Chocolate");

        BasketEntry basketEntry = new BasketEntry(1, drink);
        BasketEntry basketEntry1 = new BasketEntry(2, food);
        basket = new Basket();
        basket.putInBasket(basketEntry);
        basket.putInBasket(basketEntry1);
    }

    @Test
    public void save() throws Exception {
        OrderDAO orderDAO = new OrderDAO();


        Order order = new Order(basket);
        order.setAppartment("221B");
        order.setBuilding("122");

        Status status = new Status();
        status.setStatus("Ready");
        order.setStatus(status);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            int id = orderDAO.save(order, session);

            Order order1 = orderDAO.findByID(id , session).get();
            assertFalse(order1.getBasket().getPositions().isEmpty());
        }
    }

}