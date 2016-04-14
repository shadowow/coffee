package com.coffee.logic;

/**
 * Created by Юленька on 27.02.2016.
 */
import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @SequenceGenerator(name = "status_seq", sequenceName = "status_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_seq")
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private String status;

    public Status(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public Status() {

    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
