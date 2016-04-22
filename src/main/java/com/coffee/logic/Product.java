package com.coffee.logic;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Юленька on 26.02.2016.
 */
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {

    @Id
    @SequenceGenerator(name = "product_seq", sequenceName = "product_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "picture")
    private String picture;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "count")
    private int count;
    @Column(name = "note")
    private String note;
  /*  @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "product")
    private Set<BasketEntry> baskets = new HashSet<>();*/

    public Product(String name, String picture, BigDecimal price, int count) {
        this.name = name;
        this.picture = picture;
        this.price = price;
        this.count = count;
    }

    public Product() {

    }

    public Integer getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNote() { return note; }

    public void setNote(String note) {
        this.note = note;
    }
}
