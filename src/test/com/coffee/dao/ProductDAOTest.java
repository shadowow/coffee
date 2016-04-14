package com.coffee.dao;

import com.coffee.logic.Drink;
import com.coffee.logic.Product;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by user on 14.04.2016.
 */
public class ProductDAOTest {
    ProductDAO productDAO = new ProductDAO();

    @Before
    public void setup() {

    }

    @Test
    public void save() throws Exception {
        Product drink = new Drink();
        drink.setName("Coca-Cola");
        drink.setNote("Всё будет Coca-Cola!");
        drink.setPicture("1.jpg");
        drink.setCount(50);
        drink.setPrice(new BigDecimal(15));

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            productDAO.save(drink, session);
        }
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void findByID() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

}