package com.techelevator.dao;

import com.techelevator.model.Item;
import com.techelevator.model.Side;
import com.techelevator.model.SpecialtyPizza;
import com.techelevator.model.Topping;

import java.util.List;

public interface SpecialtyPizzaDao {
    List<SpecialtyPizza> getSpecialtyPizzas();

    SpecialtyPizza getSpecialtyPizza(int id);
}
