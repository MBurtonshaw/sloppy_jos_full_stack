package com.techelevator.dao;

import com.techelevator.model.Item;
import com.techelevator.model.Topping;

import java.util.List;

public interface ToppingDao {
    void addToppings(Item pizza);
}
