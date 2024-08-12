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
    public FoodOrder getOrder(int id) {
        FoodOrder order = null;
        String sql = "SELECT food_order_id, user_id, customer_id FROM food_order WHERE food_order_id = ?;";
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

     @Override
     public void addOrder(FoodOrder order) {
     String sql = "INSERT INTO food_order(user_id, customer_id) VALUES (?, ?) RETURNING food_order_id";
     try {
         jdbcTemplate.queryForRowSet(sql, order.getUser_id(), order.getCustomer_id());
     } catch (CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
     }
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
        FoodOrder newOrder = new FoodOrder();
        newOrder.setFood_order_id(rowSet.getInt("food_order_id"));
        newOrder.setUser_id(rowSet.getInt("user_id"));
        newOrder.setCustomer_id(rowSet.getInt("customer_id"));
        return newOrder;
    }

}
