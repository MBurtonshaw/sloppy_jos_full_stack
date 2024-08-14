package com.techelevator.model;

import java.util.List;

public class CustomDTO {
     int id;
     String sauce;
     String crust;
     String size;
     List<Integer> toppings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public List<Integer> getToppings() {
        return toppings;
    }

    public void setToppings(List<Integer> toppings) {
        this.toppings = toppings;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
