package com.coffee.logic;

import javax.persistence.*;

/**
 * Created by Юленька on 27.02.2016.
 */
@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @SequenceGenerator(name = "basket_seq", sequenceName = "basket_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_seq")
    private Integer id;
    @Column(name = "count")
    private Integer count;
    @Column(name = "product")
    private Integer productID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order", nullable = false)
    private Order order;

    public Basket(int id, int count, Product product, Order order) {
        this.id = id;
        this.count = count;
        this.productID = product.getID();
        this.order = order;
    }

    public Basket() {

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

    public int getProductID() {
        return productID;
    }

    public void setProduct(Product product) {
        this.productID = product.getID();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
