package com.coffee.service;

import com.coffee.logic.BasketEntry;
import com.coffee.logic.Desert;
import com.coffee.logic.Drink;
import com.coffee.logic.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 14.04.2016.
 */
public class BasketsRegistryTest {
    private BasketsRegistry basketsRegistry = BasketsRegistry.getInstance();

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
        Basket basket = new Basket();
     //   basketsRegistry.addBasket(basket);
        basket.putInBasket(basketEntry);
        basket.putInBasket(basketEntry1);
    }

    @Test
    public void putInBasket() throws Exception {
        Basket basket = basketsRegistry.getBasket(0);
        BasketEntry basketEntry = new BasketEntry();
        basketEntry.setCount(5);
        basketEntry.setProductID(basket.getPositions().get(0).getProductID());
        assertFalse(basket.putInBasket(basketEntry));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getActiveBaskets() throws Exception {
        Product drink = new Drink();
        drink.setID(1);
        drink.setName("pepsi");

        BasketEntry basketEntry = new BasketEntry(1, drink);
        Basket basket = new Basket();
        assertTrue(basket.putInBasket(basketEntry));
        basketsRegistry.getActiveBaskets().put(33, basket);
        basketsRegistry.getActiveBaskets().remove(0);
    }

}