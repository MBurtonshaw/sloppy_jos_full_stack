package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SpecialtyPizza extends Food{
    int specialty_pizza_id;
    @JsonProperty("specialty_pizza_name")
    String name;
    double base_price;

    List<Integer> toppingIds;
    //constructor
    public SpecialtyPizza(int specialty_pizza_id, String name, double base_price, List<Integer> toppingIds){
       // super(specialty_pizza_id,name,base_price);
        this.specialty_pizza_id =specialty_pizza_id;
        this.name = name;
        this.base_price = base_price;
        this.toppingIds = toppingIds;
    }
public  SpecialtyPizza(){

}
}
