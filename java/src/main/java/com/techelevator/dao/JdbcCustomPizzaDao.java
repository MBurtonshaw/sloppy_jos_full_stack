package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Item;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCustomPizzaDao implements CustomPizzaDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCustomPizzaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item getCustomPizzaById(int id) {
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

    @Override
    public Item getCustomPizzaByOrder(int orderId, int id) {
        Item customPizza = null;
        String sql = "SELECT food_order_item.food_order_id, item.item_id, item.sauce_name, item.crust_name, item.size_name FROM item " +
                "JOIN food_order_item ON item.item_id = food_order_item.item_id WHERE food_order_item.food_order_id = ? AND item.item_id = ?;";
        try {
            // Execute the query and fetch the results
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId, id);
            while (results.next()) {
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

    @Override
    public Item updateCustomPizza(Item pizza, int id) {
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
            updatedPizza = getCustomPizzaById(id);

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
    public void deleteCustomPizza(int id) {
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

    @Override
    public int addCustomPizza(Item pizza) {
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
        int newId = 0;
        try {
            // creating a record for a custom pizza
            newId = jdbcTemplate.queryForObject(sql, int.class, pizza.getSauce(), pizza.getCrust(), pizza.getDiameter());
            // setting an id for a custom pizza

        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
        return newId;
    }

    private Item mapRowToCustomPizza(SqlRowSet rowSet) {
        Item customPizza = new Item();
        customPizza.setItemId(rowSet.getInt("item_id"));
        customPizza.setSauce(rowSet.getString("sauce_name"));
        customPizza.setCrust(rowSet.getString("crust_name"));
        customPizza.setDiameter(rowSet.getString("size_name"));
        return customPizza;
    }

}
