package com.techelevator.model;

/**
 * The Food class represents a food item, such as a pizza,
 * with an id, name, price, and ingredients.
 */
public abstract class  Food {
    private int id; // Unique identifier for the food item
    private String name; // Name of the food item
    private double price; // Price of the food item
    private String[] ingredients; // Ingredients used in the food item


    // Getter for price

    public double getPrice() {
        return price;
    }


    // Setter for price

    public void setPrice(double price) {
        this.price = price;
    }


    // Getter for id

    public int getId() {
        return id;
    }


    // Setter for id

    public void setId(int id) {
        this.id = id;
    }


    // Getter for name

    public String getName() {
        return name;
    }


    // Setter for name
    public void setName(String name) {
        this.name = name;
    }
//
//    // Getter for ingredients
//    public String[] getIngredients() {
//        return ingredients;
//    }
//
//    // Setter for ingredients
//    public void setIngredients(String[] ingredients) {
//        this.ingredients = ingredients;
//    }


}
