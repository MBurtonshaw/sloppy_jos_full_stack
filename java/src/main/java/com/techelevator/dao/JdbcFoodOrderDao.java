package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import com.techelevator.model.SpecialtyPizza;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
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

    public List<SpecialtyPizza> getSpecialtyPizzas() {
        List<SpecialtyPizza> specialtyPizzas = new ArrayList<>();

        String sql = "SELECT specialty_pizza_id, name, base_price FROM specialty_pizza;";  // Ensure all columns are selected
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                specialtyPizzas.add(mapRowToSpecialtyPizza(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return specialtyPizzas;
    }

    public SpecialtyPizza getSpecialtyPizza(int id) {
        SpecialtyPizza specialtyPizza = null; // Initialize as null
        String sql = "SELECT * FROM specialty_pizza WHERE specialty_pizza_id = ?;";
        try {
            // Execute the query and fetch the results
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) { // Check if a result was returned
                specialtyPizza = mapRowToSpecialtyPizza(results); // Map the row to SpecialtyPizza
            }
            return specialtyPizza; // Return the specialty pizza or null if not found
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e); // Catch other DB-related exceptions
        }
    }

    @Override
    public Food getPizzaById(int id) {
        return null;
    }

    @Override
    public void addPizza(Food pizza) {

    }

    @Override
    public void updatePizza(Food pizza) {

    }

    @Override
    public void deletePizza(int id) {

    }

//    public Item addPizza(pizza) {
//        Item newItem = new Item();
//        String sql = "INSERT INTO item (sauce_id, topping_id, crust_id)" +
//                "VALUES(?, ?, ?)" +
//                "RETURNING item_id";
//        try {
//            int newPizzaId = jdbcTemplate.queryForObject(sql, int.class);
//            newItem = getPizzaById();
//        } catch (Exception e) {
//            throw new DaoException(e.getMessage());
//        }
//
//        return newItem;
//    }

    private SpecialtyPizza mapRowToSpecialtyPizza(SqlRowSet rowSet) {
        SpecialtyPizza specialtyPizza = new SpecialtyPizza();
        specialtyPizza.setId(rowSet.getInt("specialty_pizza_id"));
        specialtyPizza.setName(rowSet.getString("name"));
        specialtyPizza.setPrice(rowSet.getDouble("base_price"));
        return specialtyPizza;
    }

}
