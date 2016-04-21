package com.coffee.service;

import com.coffee.logic.BasketEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Basket {
    private int id;
    private List<BasketEntry> positions = new ArrayList<>();

    public Basket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BasketEntry> getPositions() {
        return Collections.unmodifiableList(positions);
    }

    public boolean putInBasket(BasketEntry basketEntry) {
        if (positions.stream()
                .anyMatch(entry -> entry.getProduct().getID().equals(basketEntry.getProduct().getID()))) {
            return false;
        }
        positions.add(basketEntry);
        return true;
    }

    public void removeFromBasket(int productId) {
        Optional<BasketEntry> optional = positions.stream()
                .filter(entry -> entry.getProduct().getID() == productId)
                .findFirst();
        if (optional.isPresent()) {
            positions.remove(optional.get());
        }
    }

    public void removeFromBasket(BasketEntry basketEntry) {
        positions.remove(basketEntry);
    }

    public void updateCount(int productId, int count) {
        Optional<BasketEntry> optional = positions.stream()
                .filter(entry -> entry.getProduct().getID() == productId)
                .findFirst();
        if (optional.isPresent()) {
            optional.get().setCount(count);
        }
    }

    public void clearBasket() {
        positions = new ArrayList<>();
    }
}
