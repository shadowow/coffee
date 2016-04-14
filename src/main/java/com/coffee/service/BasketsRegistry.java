package com.coffee.service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by user on 14.04.2016.
 */
public enum BasketsRegistry {
    INSTANCE;

    private Map<Integer, Basket> activeBaskets = new ConcurrentHashMap<>();

    public static BasketsRegistry getInstance() {
        return INSTANCE;
    }

    public Map<Integer, Basket> getActiveBaskets() {
        return Collections.unmodifiableMap(activeBaskets);
    }

    void addBasket(Basket basket) {
        int basketID;
        if (activeBaskets.isEmpty()) {
            basketID = 0;
        } else {
            basketID = Collections.max(activeBaskets.keySet());
        }
        basket.setId(basketID);
        activeBaskets.put(basketID, basket);
    }

    public Basket getBasket(int basketID) {
        return activeBaskets.get(basketID);
    }

    public void removeBasket(Basket basket) {
        activeBaskets.remove(basket.getId());
    }

    public void removeBasket(int basketID) {
        activeBaskets.remove(basketID);
    }
}
