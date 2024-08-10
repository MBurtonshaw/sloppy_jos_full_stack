package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcFoodOrderDao implements FoodOrderDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcFoodOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Returns all specialty pizzas
    public List<SpecialtyPizza> getSpecialtyPizzas() {
        //Fetching all specialty pizzas
        List<SpecialtyPizza> specialtyPizzas = new ArrayList<>();
        String sql = "SELECT specialty_pizza_id, specialty_pizza_name, base_price FROM specialty_pizza;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                specialtyPizzas.add(mapRowToSpecialtyPizza(results));
            }
            // Retrieving toppingIds for each specialty pizzas
            for (SpecialtyPizza specialtyPizza : specialtyPizzas) {
                List<Integer> newToppings = new ArrayList<>();
                String sql2 = "SELECT topping_id FROM specialty_topping WHERE specialty_id = ?;";
                SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, specialtyPizza.getId());
                while (results2.next()) {
                    newToppings.add(results2.getInt("topping_id"));
                }
                //Setting toppingIds for specialty pizzas
                specialtyPizza.setToppingIds(newToppings);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return specialtyPizzas;
    }

    //Returns specialty pizzas by id
    public SpecialtyPizza getSpecialtyPizza(int id) {
        SpecialtyPizza specialtyPizza = null;
        String sql = "SELECT specialty_pizza_id, specialty_pizza_name, base_price FROM specialty_pizza WHERE specialty_pizza_id = ?;";
        try {
            // Execute the query and fetch the results
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                specialtyPizza = mapRowToSpecialtyPizza(results);

                //Retrieving toppingIds for specialty pizza
                List<Integer> newToppings = new ArrayList<>();
                String sql2 = "SELECT topping_id FROM specialty_topping WHERE specialty_id = ?;";
                SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, id);
                while (results2.next()) {
                    newToppings.add(results2.getInt("topping_id"));
                }
                //Setting toppingIds for specialty pizza
                specialtyPizza.setToppingIds(newToppings);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e); // Catch other DB-related exceptions
        }
        return specialtyPizza;
    }

    @Override
    public Item updatePizza(Item pizza) {
        Item updatedPizza = null;
        String sql = "UPDATE item SET sauce_name = ?, crust_name = ?, size_name = ? WHERE item_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, pizza.getSauce(), pizza.getCrust(),
                    pizza.getDiameter(), pizza.getItemId());

            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedPizza = getPizzaById(pizza.getItemId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedPizza;
    }

    @Override
    public void deletePizza(int id) {
        boolean success = false;
        int numberOfRows;
        String sql = "DELETE FROM item WHERE item_id = ?;";
        try {
            jdbcTemplate.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    //Returns a custom pizza by id
    @Override
    public Item getPizzaById(int id) {
        Item customPizza = null;
        String sql = "SELECT item_id, sauce_name, crust_name, size_name FROM item WHERE item_id = ?;";
        try {
            // Execute the query and fetch the results
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                customPizza = mapRowToCustomPizza(results);

                //Retrieving toppingIds for custom pizza
                List<Integer> newToppings = new ArrayList<>();
                String sql2 = "SELECT * FROM item_topping WHERE item_id = ?;";
                SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, id);
                while (results2.next()) {
                    newToppings.add(results2.getInt("topping_id"));
                }
                //Setting toppingIds for custom pizza
                customPizza.setToppingIds(newToppings);
            }
            return customPizza; // Return the specialty pizza or null if not found
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e); // Catch other DB-related exceptions
        }
    }

    //Create a custom pizza
    @Override
    public void addPizza(Item pizza) {
        //Inserting custom pizza into db through sql string
        //Using pizza object in parameters
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
            //creating a record for a custom pizza
            int newId = jdbcTemplate.queryForObject(sql,int.class, pizza.getSauce(), pizza.getCrust(), pizza.getDiameter());
            //setting an id for a custom pizza
            pizza.setItemId(newId);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

    //Returns all sides
    @Override
    public List<Side> getSides() {
        List<Side> sides = new ArrayList<>();
        String sql = "SELECT side_id, side_name, base_price FROM side;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                sides.add(mapRowToSide(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sides;
    }

    //Returns a side by id
    @Override
    public Side getSide(int id) {
        Side side = null;
        String sql = "SELECT side_id, side_name, base_price FROM side WHERE side_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) { // Check if a result was returned
                side = mapRowToSide(results); // Map the row to SpecialtyPizza
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return side;
    }

    //Format custom pizza based on model
    private Item mapRowToCustomPizza(SqlRowSet rowSet) {
        Item customPizza = new Item();
        customPizza.setItemId(rowSet.getInt("item_id"));
        customPizza.setSauce(rowSet.getString("sauce_name"));
        customPizza.setCrust(rowSet.getString("crust_name"));
        customPizza.setDiameter(rowSet.getString("size_name"));
        return customPizza;
    }

    //Format specialty pizza based on model
    private SpecialtyPizza mapRowToSpecialtyPizza(SqlRowSet rowSet) {
        SpecialtyPizza specialtyPizza = new SpecialtyPizza();
        specialtyPizza.setId(rowSet.getInt("specialty_pizza_id"));
        specialtyPizza.setName(rowSet.getString("specialty_pizza_name"));
        specialtyPizza.setPrice(rowSet.getDouble("base_price"));
        return specialtyPizza;
    }

    //Format side based on model
    private Side mapRowToSide(SqlRowSet rowSet) {
        Side side = new Side();
        side.setId(rowSet.getInt("side_id"));
        side.setName(rowSet.getString("side_name"));
        side.setPrice(rowSet.getDouble("base_price"));
        return side;
    }

}
