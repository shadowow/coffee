package com.coffee.logic;

import javax.persistence.*;

/**
 * Created by Юленька on 27.02.2016.
 */
@Entity
@Table(name = "drink")
public class Drink extends Product {

    @Column(name = "volume")
    private Float volume;
    @Column(name = "type")
    private Boolean type;

    public Drink(){
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
