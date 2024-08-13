
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

    FoodOrder getOrder(int id);
    FoodOrder addOrder(FoodOrder foodOrder);
    FoodOrder removeOrder(int id);

    void addSpecialtyPizzaToOrder(int orderId, int specialtyId);
    void removeSpecialtyPizzaFromOrder(int orderId, int specialtyId);
    void addCustomPizzaToOrder(int orderId, int customId);
    void removeCustomPizzaFromOrder(int orderId, int customId);

    void addSideToOrder(int order, int side);

    void removeSideFromOrder(int orderId, int sideId);

}
