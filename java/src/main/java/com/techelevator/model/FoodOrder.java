package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FoodOrder {

    @JsonProperty("order")
    private int food_order_id;
    @JsonProperty("user")
    private int user_id;
    @JsonProperty("customer")
    private int customer_id;
    @JsonProperty("specialty_pizzas")
    private List<Integer> specialty_pizza_ids;
    @JsonProperty("custom_pizzas")
    private List<Integer> custom_pizza_ids;
    @JsonProperty("sides")

    private List<Integer> side_ids;

    // getters


    public List<Integer> getCustom_pizza_ids() {
        return custom_pizza_ids;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    // setters


    public List<Integer> getSide_ids() {
        return side_ids;
    }

    public void setCustom_pizza_ids(List<Integer> custom_pizza_ids) {
        this.custom_pizza_ids = custom_pizza_ids;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setSide_ids(List<Integer> side_ids) {
        this.side_ids = side_ids;
    }

    public List<Integer> getSpecialty_pizza_ids() {
        return specialty_pizza_ids;
    }

    public void setSpecialty_pizza_ids(List<Integer> specialty_pizza_ids) {
        this.specialty_pizza_ids = specialty_pizza_ids;
    }

    public int getFood_order_id() {
        return food_order_id;
    }

    public void setFood_order_id(int food_order_id) {
        this.food_order_id = food_order_id;
    }
}
