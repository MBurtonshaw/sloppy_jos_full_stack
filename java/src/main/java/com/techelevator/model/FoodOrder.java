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
    private List<String> specialtyPizzas;
    @JsonProperty("custom_pizzas")
    private List<Integer> customPizzas;
    @JsonProperty("sides")
    private List<String> sides;


    public int getCustomer_id() {
        return customer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    // setters


    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }



    public int getFood_order_id() {
        return food_order_id;
    }

    public void setFood_order_id(int food_order_id) {
        this.food_order_id = food_order_id;
    }

    public List<String> getSpecialtyPizzas() {
        return specialtyPizzas;
    }

    public void setSpecialtyPizzas(List<String> specialtyPizzas) {
        this.specialtyPizzas = specialtyPizzas;
    }

    public List<Integer> getCustomPizzas() {
        return customPizzas;
    }

    public void setCustomPizzas(List<Integer> customPizzas) {
        this.customPizzas = customPizzas;
    }

    public List<String> getSides() {
        return sides;
    }

    public void setSides(List<String> sides) {
        this.sides = sides;
    }
}
