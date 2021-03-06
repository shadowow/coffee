package com.coffee.logic;

import javax.persistence.*;

/**
 * Created by Юленька on 27.02.2016.
 */
@Entity
@Table(name = "basket")
public class BasketEntry {

    @Id
    @SequenceGenerator(name = "basket_seq", sequenceName = "basket_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_seq")
    private Integer id;
    @Column(name = "count")
    private Integer count;
    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "_order", nullable = false)
    private Order order;

    public BasketEntry(int id, int count, Product product, Order order) {
        this.id = id;
        this.count = count;
        this.product = product;
        this.order = order;
    }

    public BasketEntry(Integer count, Product product) {
        this.count = count;
        this.product = product;
    }

    public BasketEntry() {
    }

    public int getID() { return id; }

    public void setID(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
