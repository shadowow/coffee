package com.coffee.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Юленька on 27.02.2016.
 */
@Entity
@Table(name = "bakery")
public class Bakery extends Product {

    @Column(name = "weight")
    private Integer weight;
    @Column(name = "date")
    private Timestamp date;

    public Bakery() {
    }

    public Bakery(Product product) {
        if (product.getID() != null) {
            setID(product.getID());
        }
        setPrice(product.getPrice());
        setName(product.getName());
        setCount(product.getCount());
        setPicture(product.getPicture());
        setNote(product.getNote());
    }

    public int getWeight() {

        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
