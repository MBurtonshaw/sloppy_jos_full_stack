package com.techelevator.model;

public class FoodOrder {

    private int food_order_id;
    private int item_id;
    private int user_id;
    private int customer_id;
    private int side_id;
    private int specialty_pizza_id;
    //getters
    public int getItem_id() {
        return item_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getUser_id() {
        return user_id;
    }
    //setters
    public int getSide_id() {
        return side_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setSide_id(int side_id) {
        this.side_id = side_id;
    }
    public int getSpecialty_pizza_id() {
        return specialty_pizza_id;
    }
    public void setSpecialty_pizza_id(int specialty_pizza_id) {
        this.specialty_pizza_id = specialty_pizza_id;
    }

    public int getFood_order_id() {
        return food_order_id;
    }

    public void setFood_order_id(int food_order_id) {
        this.food_order_id = food_order_id;
    }
}