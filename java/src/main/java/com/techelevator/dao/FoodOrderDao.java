package com.techelevator.dao;



import com.techelevator.model.Food;
import com.techelevator.model.SpecialtyPizza;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * FoodOrderDao is an interface that defines methods for
 * accessing and managing food items in a database or collection.
 */

public interface FoodOrderDao {
List<SpecialtyPizza> getSpecialtyPizzas();
SpecialtyPizza getSpecialtyPizza(int id);
    /**
     * Retrieves all specialty pizzas from the data source.
     * @return a list of Food objects representing specialty pizzas.
     */


    /**
     * Retrieves a pizza by its unique identifier.
     * @param id The unique identifier of the pizza.
     * @return a Food object representing the pizza.
     */
  Food getPizzaById(int id);

    /**
     * Adds a new pizza to the data source.
     * @param pizza The Food object representing the new pizza to add.
     */
  void addPizza(Food pizza);

    /**
     * Updates an existing pizza in the data source.
     * @param pizza The Food object representing the pizza to update.
     */
void updatePizza(Food pizza);

    /**
     * Deletes a pizza from the data source by its unique identifier.
     * @param id The unique identifier of the pizza to delete.
     */
void deletePizza(int id);
}

