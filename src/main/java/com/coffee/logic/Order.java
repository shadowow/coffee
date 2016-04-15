package com.coffee.logic;

import com.coffee.service.Basket;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Юленька on 27.02.2016.
 */
@Entity
@Table(name = "_order")
public class Order {

    @Id
    @SequenceGenerator(name = "_order_seq", sequenceName = "_order_number_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_order_seq")
    @Column(name = "number")
    private Integer number;
    @Column(name = "phone")
    private String phone;
    @Column(name = "street")
    private String street;
    @Column(name = "building")
    private String building;
    @Column(name = "appartment")
    private String appartment;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "status", nullable = false)
    private Status status;
    @Column(name = "note")
    private String note;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = BasketEntry.class)
    @Column(table = "basket")
    private Set<BasketEntry> basketEntrySet = new HashSet<>();

    @Transient
    private Basket basket;

    public Order(int number, String phone, String street, String building, String appartment, Status status, String note, Basket basket) {
        this.number = number;
        this.phone = phone;
        this.street = street;
        this.building = building;
        this.appartment = appartment;
        this.status = status;
        this.note = note;

        this.basket = basket;
        basket.getPositions().forEach(position -> {
            position.setOrder(this);
        });
    }

    public Order(Basket basket) {
        this.basket = basket;
        basket.getPositions().forEach(position -> {
            basketEntrySet.add(position);
            position.setOrder(this);
        });
    }

    private Order() {

    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<BasketEntry> getBasketEntrySet() {
        return basketEntrySet;
    }

    public void setBasketEntrySet(Set<BasketEntry> basketEntrySet) {
        this.basketEntrySet = basketEntrySet;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
        basket.getPositions().forEach(position -> {
            basketEntrySet.add(position);
            position.setOrder(this);
        });
    }
}
