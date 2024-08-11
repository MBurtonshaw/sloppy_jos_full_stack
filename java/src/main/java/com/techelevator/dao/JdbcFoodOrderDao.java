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
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return null;
    }

    @Override
    public Item updatePizza(Item pizza, int id) {
        Item updatedPizza = null;
        List<Integer> newToppingIds = new ArrayList<>();
        String sql = "UPDATE item SET sauce_name = ?, crust_name = ?, size_name = ? WHERE item_id = ?;";

        try {
            // Update the main pizza attributes
            int numberOfRows = jdbcTemplate.update(sql, pizza.getSauce(), pizza.getCrust(),
                    pizza.getDiameter(), id);

            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected when updating pizza, expected at least one");
            }

            // Get the updated pizza
            updatedPizza = getPizzaById(id);

            // Clear existing toppings for the pizza
            String deleteToppingsSql = "DELETE FROM item_topping WHERE item_id = ?;";
            jdbcTemplate.update(deleteToppingsSql, id);

            // Update the new topping IDs
            List<Integer> toppingIds = pizza.getToppingIds();
            String insertToppingSql = "INSERT INTO item_topping (item_id, topping_id) VALUES (?, ?);";
            for (Integer toppingId : toppingIds) {
                jdbcTemplate.update(insertToppingSql, id, toppingId);
                newToppingIds.add(toppingId);
            }

            // Set the new topping IDs
            updatedPizza.setToppingIds(newToppingIds);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation during pizza update", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error during pizza update", e);
        }

        return updatedPizza;
    }

    @Override
    public void deletePizza(int id) {
        String sql = "DELETE FROM item_topping WHERE item_id = ?;";
        String sql2 = "DELETE FROM item WHERE item_id = ?;";
        try {
            int success = jdbcTemplate.update(sql, id);
            if (success > 0) {
                jdbcTemplate.update(sql2, id);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    // Returns a custom pizza by id
    @Override
    public Item getPizzaById(int id) {
        Item customPizza = null;
        String sql = "SELECT item_id, sauce_name, crust_name, size_name FROM item WHERE item_id = ?;";
        try {
            // Execute the query and fetch the results
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                customPizza = mapRowToCustomPizza(results);

                // Retrieving toppingIds for custom pizza
                List<Integer> newToppings = new ArrayList<>();
                String sql2 = "SELECT * FROM item_topping WHERE item_id = ?;";
                SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, id);
                while (results2.next()) {
                    newToppings.add(results2.getInt("topping_id"));
                }
                // Setting toppingIds for custom pizza
                customPizza.setToppingIds(newToppings);
            }
            return customPizza; // Return the specialty pizza or null if not found
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e); // Catch other DB-related exceptions
        }
    }

    // Create a custom pizza
    @Override
    public void addPizza(Item pizza) {
        // Inserting custom pizza into db through sql string
        // Using pizza object in parameters
        String sql = "INSERT INTO item (sauce_name, crust_name, size_name) VALUES (?, ?, ?) RETURNING item_id";
        if (pizza.getSauce() == null) {
            throw new DaoException("Sauce cannot be null");
        }
        if (pizza.getCrust() == null) {
            throw new DaoException("Crust cannot be null");
        }
        if (pizza.getDiameter() == null) {
            throw new DaoException("Size cannot be null");
        }
        try {
            // creating a record for a custom pizza
            int newId = jdbcTemplate.queryForObject(sql, int.class, pizza.getSauce(), pizza.getCrust(),
                    pizza.getDiameter());
            // setting an id for a custom pizza
            pizza.setItemId(newId);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    // Returns all sides

    @Override
    public FoodOrder getOrder(int id) {
        FoodOrder order = null;
        String sql = "SELECT food_order_id, item_id, user_id, customer_id, side_id, specialty_pizza_id FROM food_order WHERE food_order_id = ?;";
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

    // @Override
    // public FoodOrder addOrder(int id) {
    // FoodOrder order = null;
    // String sql = "INSERT INTO food_order (food_order_id) VALUES (?) RETURNING
    // food_order_id";
    // try {
    // SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
    // if (results.next()) {
    // order = mapRowToOrder(results);
    // }
    // } catch (CannotGetJdbcConnectionException e) {
    // throw new DaoException("Unable to connect to server or database", e);
    // }
    // return order;
    // }

    public void addSideToOrder(int orderId, int sideId) {
        String sql = "INSERT INTO food_order_side VALUES (food_order_id, side_id);";
        try {
            jdbcTemplate.update(sql, orderId, sideId);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    // Format custom pizza based on model
    private Item mapRowToCustomPizza(SqlRowSet rowSet) {
        Item customPizza = new Item();
        customPizza.setItemId(rowSet.getInt("item_id"));
        customPizza.setSauce(rowSet.getString("sauce_name"));
        customPizza.setCrust(rowSet.getString("crust_name"));
        customPizza.setDiameter(rowSet.getString("size_name"));
        return customPizza;
    }

    private FoodOrder mapRowToOrder(SqlRowSet rowSet) {
        FoodOrder newOrder = new FoodOrder();
        newOrder.setFood_order_id(rowSet.getInt("food_order_id"));
        List<Integer> custom_ids = new ArrayList<>();
        custom_ids.add(rowSet.getInt("item_id"));
        newOrder.setCustom_pizza_ids(custom_ids);
        List<Integer> specialty_ids = new ArrayList<>();
        specialty_ids.add(rowSet.getInt("specialty_pizza_id"));
        newOrder.setSpecialty_pizza_ids(specialty_ids);
        List<Integer> side_ids = new ArrayList<>();
        side_ids.add(rowSet.getInt("side_id"));
        newOrder.setSide_ids(side_ids);
        newOrder.setUser_id(rowSet.getInt("user_id"));
        newOrder.setCustomer_id(rowSet.getInt("customer_id"));
        return newOrder;
    }

}
