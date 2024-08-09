package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import com.techelevator.model.Item;
import com.techelevator.model.SpecialtyPizza;
import com.techelevator.model.Topping;
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
public class JdbcToppingDao implements ToppingDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcToppingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void addToppings(Item pizza) {
        String sql = "INSERT INTO item_topping(item_id, topping_id)" +
                    "VALUES (?, ?);";
        try {
            List<Topping> toppings = pizza.getToppings();
            if (!toppings.isEmpty()) {
                for (Topping topping : toppings) {
                    // Insert the topping and associate it with the pizza's ID
                    jdbcTemplate.update(sql, pizza.getItemId(), topping.getTopping_id()); // Make sure this matches your Topping class
                }
            }
        } catch (DataAccessException e) {
            throw new DaoException("Database access error", e);
        }
    }

}
