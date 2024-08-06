package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.SpecialtyPizza;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component

public class JdbcFoodOrderDao implements FoodOrderDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcFoodOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;  // Use the passed JdbcTemplate
    }

    public List<SpecialtyPizza> getSpecialtyPizzas() {
        List<SpecialtyPizza> specialtyPizzas = new ArrayList<>();
        String sql = "SELECT specialty_pizza_id, name, base_price FROM specialty_pizza;";  // Ensure all columns are selected
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            specialtyPizzas.add(mapRowToSpecialtyPizza(results));
        }
        return specialtyPizzas;
    }

    private SpecialtyPizza mapRowToSpecialtyPizza(SqlRowSet rowSet) {  // Correct return type
        SpecialtyPizza specialtyPizza = new SpecialtyPizza();
        specialtyPizza.setId(rowSet.getInt("specialty_pizza_id"));  // Correct column name
        specialtyPizza.setName(rowSet.getString("name"));
        specialtyPizza.setPrice(rowSet.getDouble("base_price"));
        return specialtyPizza;
    }
}
