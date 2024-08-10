
package com.techelevator.dao;



import com.techelevator.model.Food;
import com.techelevator.model.Item;
import com.techelevator.model.Side;
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
    List<Side> getSides();
    Side getSide(int id);
    void addPizza(Item pizza);
    Item getPizzaById(int id);

    Item updatePizza(Item pizza);

    void deletePizza(int id);


}