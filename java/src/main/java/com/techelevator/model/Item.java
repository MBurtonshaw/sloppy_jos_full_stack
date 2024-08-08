package com.techelevator.model;

public class Item {

    private int itemId;
    private String diameter; //
    private String sauce; //
    private Topping[] toppings; //
    private String crust;

    public Item() {
    }

    // Constructor with parameters
    public Item(int itemId, String diameter, String sauce, Topping[] topping, String crust) {
        this.itemId = itemId;
        this.diameter = diameter;
        this.sauce = sauce;
        this.toppings = toppings;
        this.crust = crust;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getSauce() {
        return sauce;
    }

    public String getCrust() {
        return crust;
    }

    public Topping[] getToppings() {
        return toppings;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public void setToppings(Topping[] toppings) {
        this.toppings = toppings;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}