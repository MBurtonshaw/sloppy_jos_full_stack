package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.FoodOrder;
import com.techelevator.model.Item;
import com.techelevator.model.Side;
import com.techelevator.model.SpecialtyPizza;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFoodOrderDao implements FoodOrderDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcFoodOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FoodOrder getOrder(int orderId) {
        FoodOrder order = null;
        //join specialty
        //join custom
        //join sides
        String sql = "SELECT food_order.food_order_id, food_order.user_id, food_order.customer_id, specialty_pizza.specialty_pizza_name, food_order_item.item_id, side.side_name FROM food_order " +
                "JOIN food_order_specialty ON food_order.food_order_id = food_order_specialty.food_order_id " +
                "JOIN specialty_pizza ON food_order_specialty.specialty_pizza_id = specialty_pizza.specialty_pizza_id " +
                "JOIN food_order_item ON food_order.food_order_id = food_order_item.food_order_id " +
                "JOIN food_order_side ON food_order.food_order_id = food_order_side.food_order_id " +
                "JOIN side ON food_order_side.side_id = side.side_id WHERE food_order.food_order_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId);
            if (results.next()) {
                order = mapRowToOrder(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return order;
    }

    @Override
    public List<FoodOrder> getOrders() {
        List<FoodOrder> orders = new ArrayList<>();
        String sql = "SELECT food_order_id, user_id, customer_id FROM food_order;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                orders.add(mapRowToOrder(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return orders;
    }

     @Override
     public FoodOrder addOrder(FoodOrder order) {
        FoodOrder newOrder = new FoodOrder();
     String sql = "INSERT INTO food_order(user_id, customer_id) VALUES (?, ?) RETURNING food_order_id";
     try {
         int result = jdbcTemplate.queryForObject(sql, int.class, order.getUser_id(), order.getCustomer_id());
         newOrder.setFood_order_id(result);
         newOrder.setCustomer_id(order.getCustomer_id());
         newOrder.setUser_id(order.getUser_id());
     } catch (CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
     }
     return newOrder;
     }

    @Override
    public FoodOrder removeOrder(int id) {
        FoodOrder order = null;
        String sql = "DELETE FROM food_order WHERE food_order_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                order = mapRowToOrder(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return order;
    }

    public void addSpecialtyPizzaToOrder(int orderId, int specialtyId) {
        String sql = "INSERT INTO food_order_specialty(food_order_id, specialty_pizza_id) VALUES(?, ?);";
        try {
            jdbcTemplate.update(sql, orderId, specialtyId);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    public void removeSpecialtyPizzaFromOrder(int orderId, int specialtyId) {
        String sql = "DELETE FROM food_order_specialty WHERE food_order_id = ? AND specialty_pizza_id = ?;";
        try {
            jdbcTemplate.update(sql, orderId, specialtyId);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    public void addCustomPizzaToOrder(int orderId, int customId) {
        String sql = "INSERT INTO food_order_item(food_order_id, item_id) VALUES(?, ?);";
        try {
            jdbcTemplate.update(sql, orderId, customId);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    public void removeCustomPizzaFromOrder(int orderId, int customId) {
        String sql = "DELETE FROM food_order_item WHERE food_order_id = ? AND item_id = ?;";
        try {
            jdbcTemplate.update(sql, orderId, customId);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    public void addSideToOrder(int orderId, int sideId) {
        String sql = "INSERT INTO food_order_side(food_order_id, side_id) VALUES(?, ?);";
        try {
            jdbcTemplate.update(sql, orderId, sideId);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    public void removeSideFromOrder(int orderId, int sideId) {
        String sql = "DELETE FROM food_order_side WHERE food_order_id = ? AND side_id = ?;";
        try {
            jdbcTemplate.update(sql, orderId, sideId);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    private FoodOrder mapRowToOrder(SqlRowSet rowSet) {
        FoodOrder newOrder = null;
        List<String> specialtyPizzas = new ArrayList<>();
        List<Integer> customPizzas = new ArrayList<>();
        List<String> sides = new ArrayList<>();

        while (rowSet.next()) {
            if (newOrder == null) {
                // Initialize the FoodOrder object once
                newOrder = new FoodOrder();
                newOrder.setFood_order_id(rowSet.getInt("food_order_id"));
                newOrder.setUser_id(rowSet.getInt("user_id"));
                newOrder.setCustomer_id(rowSet.getInt("customer_id"));

                // Set empty lists
                newOrder.setSpecialtyPizzas(specialtyPizzas);
                newOrder.setCustomPizzas(customPizzas);
                newOrder.setSides(sides);
            }

            // Only add unique items to the lists
            String pizzaName = rowSet.getString("specialty_pizza_name");
            if (pizzaName != null && !specialtyPizzas.contains(pizzaName)) {
                specialtyPizzas.add(pizzaName);
            }

            int itemId = rowSet.getInt("item_id");
            if (itemId != 0 && !customPizzas.contains(itemId)) {
                customPizzas.add(itemId);
            }

            String sideName = rowSet.getString("side_name");
            if (sideName != null && !sides.contains(sideName)) {
                sides.add(sideName);
            }
        }

        return newOrder;
    }

}
