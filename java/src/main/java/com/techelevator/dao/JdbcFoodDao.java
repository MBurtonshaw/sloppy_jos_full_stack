package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import com.techelevator.model.SpecialtyPizza;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
<<<<<<< HEAD
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
=======

import org.springframework.stereotype.Component;
>>>>>>> 090e21314d3249caac5db010100705d93d54092a

import java.util.ArrayList;
import java.util.List;
@Component
<<<<<<< HEAD
// Add this annotation
=======


>>>>>>> 090e21314d3249caac5db010100705d93d54092a
public class JdbcFoodDao implements FoodOrderDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcFoodDao(JdbcTemplate jdbcTemplate) {
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

    private SpecialtyPizza mapRowToSpecialtyPizza(SqlRowSet rowSet) {
        SpecialtyPizza specialtyPizza = new SpecialtyPizza();
        specialtyPizza.setId(rowSet.getInt("specialty_pizza_id"));
        specialtyPizza.setName(rowSet.getString("name"));
        specialtyPizza.setPrice(rowSet.getDouble("base_price"));
        return specialtyPizza;
    }
    public SpecialtyPizza getSpecialtyPizza(int id){
        SpecialtyPizza specialtyPizza = new SpecialtyPizza();
        String sql ="SELECT * FROM specialty_pizza WHERE id = ?";
        return  specialtyPizza;
    }

}
