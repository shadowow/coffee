package com.coffee.dao;

import com.coffee.logic.Desert;
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
    private ProductDAO productDAO = new ProductDAO();

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
    public void findByID() throws Exception {
        Desert desert = new Desert();
        desert.setName("Choco");
        desert.setNote("Sweet!");
        desert.setPicture("2.jpg");
        desert.setCount(50);
        desert.setPrice(new BigDecimal(15));
        desert.setFirm("russia");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            int id = productDAO.save(desert, session);
            Desert desert1 = (Desert)productDAO.findByID(id, session).get();
            assertEquals(desert1.getFirm(), "russia");
            productDAO.delete(desert1, session);
            assertFalse(productDAO.findByID(id, session).isPresent());
        }

    }

    @Test
    public void findAllBakery() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            assertTrue(productDAO.findAllBakery(session).isEmpty());
        }
    }

    @Test
    public void findAllDrinks() throws Exception {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            assertEquals(productDAO.findAllDrinks(session).size(), 1);
            assertEquals(productDAO.findAllDrinks(session).get(0).getName(), "Coca-Cola");
        }
    }

}