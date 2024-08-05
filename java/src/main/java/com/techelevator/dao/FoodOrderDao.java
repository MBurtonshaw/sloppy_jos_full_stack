package com.techelevator.dao;

import java.util.List;

public interface FoodOrderDao {
    List<FoodOrderDao> getAllSpecialtyPizzas();
    FoodOrderDao getPizzaById(int id);
        void addPizza(FoodOrderDao pizza);
        void updatePizza(FoodOrderDao pizza);
        void deletePizza(int id);
    }



