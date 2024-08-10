
package com.techelevator.dao;
import com.techelevator.model.Item;
import com.techelevator.model.Side;
import com.techelevator.model.SpecialtyPizza;
import org.springframework.core.annotation.Order;
import java.util.List;

/**
 * FoodOrderDao is an interface that defines methods for
 * accessing and managing food items in a database or collection.
 */

public interface FoodOrderDao {
    Order addOrder(Order order);

    List<Order> getOrdersByUserId(int userId);

    List<SpecialtyPizza> getSpecialtyPizzas();
    SpecialtyPizza getSpecialtyPizza(int id);
    List<Side> getSides();
    Side getSide(int id);
    void addPizza(Item pizza);
    Item getPizzaById(int id);

    Item updatePizza(Item pizza);

    void deletePizza(int id);


}



