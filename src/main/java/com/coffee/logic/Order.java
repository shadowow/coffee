package com.coffee.logic;

import javax.persistence.*;

/**
 * Created by Юленька on 27.02.2016.
 */
@Entity
@Table(name = "order")
public class Order {

    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "order_number_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status", nullable = false)
    private Status status;
    @Column(name = "note")
    private String note;

    public Order(int number, String phone, String street, String building, String appartment, Status status, String note) {
        this.number = number;
        this.phone = phone;
        this.street = street;
        this.building = building;
        this.appartment = appartment;
        this.status = status;
        this.note = note;
    }

    public Order() {

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
}