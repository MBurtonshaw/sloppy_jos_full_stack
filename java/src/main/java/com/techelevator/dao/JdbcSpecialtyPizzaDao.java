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
public class JdbcSpecialtyPizzaDao implements SpecialtyPizzaDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSpecialtyPizzaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Returns all specialty pizzas
    public List<SpecialtyPizza> getSpecialtyPizzas() {
        // Fetching all specialty pizzas
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
                List<String> toppingNames = new ArrayList<>();
                String sql2 = "SELECT topping_id FROM specialty_topping WHERE specialty_id = ?;";
                SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, specialtyPizza.getId());
                while (results2.next()) {
                    newToppings.add(results2.getInt("topping_id"));
                }
                String sql3 = "SELECT topping_name FROM topping WHERE topping_id = ?;";
                for (Integer topping : newToppings) {
                    SqlRowSet results3 = jdbcTemplate.queryForRowSet(sql3, topping);
                    while (results3.next()) {
                        toppingNames.add(results3.getString("topping_name"));
                    }
                }
                // Setting toppingIds for specialty pizzas
                specialtyPizza.setToppings(toppingNames);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return specialtyPizzas;
    }

    // Returns specialty pizzas by id
    public SpecialtyPizza getSpecialtyPizza(int id) {
        SpecialtyPizza specialtyPizza = null;
        String sql = "SELECT specialty_pizza_id, specialty_pizza_name, base_price FROM specialty_pizza WHERE specialty_pizza_id = ?;";
        try {
            // Execute the query and fetch the results
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                specialtyPizza = mapRowToSpecialtyPizza(results);

                // Retrieving toppingIds for specialty pizza
                List<Integer> newToppings = new ArrayList<>();
                List<String> toppingNames = new ArrayList<>();
                String sql2 = "SELECT topping_id FROM specialty_topping WHERE specialty_id = ?;";
                SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, id);
                while (results2.next()) {
                    newToppings.add(results2.getInt("topping_id"));
                }
                String sql3 = "SELECT topping_name FROM topping WHERE topping_id = ?;";
                for (Integer topping : newToppings) {
                    SqlRowSet results3 = jdbcTemplate.queryForRowSet(sql3, topping);
                    while (results3.next()) {
                        toppingNames.add(results3.getString("topping_name"));
                    }
                }

                // Setting toppingIds for specialty pizza
                specialtyPizza.setToppings(toppingNames);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e); // Catch other DB-related exceptions
        }
        return specialtyPizza;
    }

    // Format specialty pizza based on model
    private SpecialtyPizza mapRowToSpecialtyPizza(SqlRowSet rowSet) {
        SpecialtyPizza specialtyPizza = new SpecialtyPizza();
        specialtyPizza.setId(rowSet.getInt("specialty_pizza_id"));
        specialtyPizza.setName(rowSet.getString("specialty_pizza_name"));
        specialtyPizza.setPrice(rowSet.getDouble("base_price"));
        return specialtyPizza;
    }
}
