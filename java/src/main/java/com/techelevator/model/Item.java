package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Item {

    @JsonProperty("item_id")
    private int itemId;
    @JsonProperty("size_name")
    private String diameter;
    @JsonProperty("sauce_name")
    private String sauce;
    @JsonProperty("toppings")
    private List<Integer> toppingIds;
    @JsonProperty("crust_name")
    private String crust;

    public Item() {
        this.toppingIds = new ArrayList<>();
    }

    // Constructor with parameters
    public Item(int itemId, String diameter, String sauce, List<Integer> toppingIds, String crust) {
        this.itemId = itemId;
        this.diameter = diameter;
        this.sauce = sauce;
        this.toppingIds = toppingIds; // Correctly set the toppings
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

    public List<Integer> getToppingIds() {
        return toppingIds;
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

    public void setToppingIds(List<Integer> toppingIds) {
        this.toppingIds = toppingIds;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}