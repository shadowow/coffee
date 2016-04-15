package com.coffee.logic;

import javax.persistence.*;

/**
 * Created by Юленька on 27.02.2016.
 */
@Entity
@Table(name = "desert")
public class Desert extends Product {

    @Column(name = "weight")
    private Integer weight;
    @Column(name = "firm")
    private String firm;

    public Desert() {
    }

    public Desert(Product product) {
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

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }
}
