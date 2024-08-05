package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.SpecialtyPizza;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository  // Add this annotation
public class JdbcFoodDao implements FoodOrderDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcFoodDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SpecialtyPizza> getSpecialtyPizzas() {
        List<SpecialtyPizza> specialtyPizzas = new ArrayList<>();
        String sql = "SELECT specialty_pizza_id, name, base_price FROM specialty_pizza;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            specialtyPizzas.add(mapRowToSpecialtyPizza(results));
        }
        return specialtyPizzas;
    }

    private static SpecialtyPizza mapRowToSpecialtyPizza(SqlRowSet rowSet) {
        SpecialtyPizza specialtyPizza = new SpecialtyPizza();
        specialtyPizza.setId(rowSet.getInt("specialty_pizza_id"));
        specialtyPizza.setName(rowSet.getString("name"));
        specialtyPizza.setPrice(rowSet.getDouble("base_price"));
        return specialtyPizza;
    }
}
