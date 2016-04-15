package com.coffee.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Юленька on 27.02.2016.
 */
@Entity
@Table(name = "drink")
public class Drink extends Product {

    @Column(name = "volume")
    private Float volume;
    @Column(name = "hot")
    private Boolean hot;

    public Drink(){
    }

    public Drink(Product product) {
        if (product.getID() != null) {
            setID(product.getID());
        }
        setPrice(product.getPrice());
        setName(product.getName());
        setCount(product.getCount());
        setPicture(product.getPicture());
        setNote(product.getNote());
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }
}
