package com.techelevator.model;

public class SpecialtyPizza extends Food{
    int specialty_pizza_id;
    String name;
    double base_price;
    //constructor
    public SpecialtyPizza(int specialty_pizza_id, String name, double base_price){
       // super(specialty_pizza_id,name,base_price);
        this.specialty_pizza_id =specialty_pizza_id;
        this.name = name;
        this.base_price = base_price;

    }
public  SpecialtyPizza(){

}
}
