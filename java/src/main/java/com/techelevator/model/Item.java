package com.techelevator.model;

public class Item {

    private int itemId;
    private String diameter; //
    private String sauce; //
    private String[] topping; //
    private String crust;

    public Item() {
    }

    // Constructor with parameters
    public Item(int itemId, String diameter, String sauce, String[] topping, String crust) {
        this.itemId = itemId;
        this.diameter = diameter;
        this.sauce = sauce;
        this.topping = topping;
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

    public String[] getTopping() {
        return topping;
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

    public void setTopping(String[] topping) {
        this.topping = topping;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}