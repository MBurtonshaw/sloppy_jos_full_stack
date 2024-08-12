
package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;

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

    void addPizza(Item pizza);

    Item getPizzaById(int id);

    Item updatePizza(Item pizza, int id);

    void deletePizza(int id);

    FoodOrder getOrder(int id);
    FoodOrder addOrder(int id);
    FoodOrder removeOrder(int id);

    void addSpecialtyPizzaToOrder(int orderId, int specialtyId);
    void removeSpecialtyPizzaFromOrder(int orderId, int specialtyId);

    void addSideToOrder(int order, int side);

    void removeSideFromOrder(int orderId, int sideId);

}
