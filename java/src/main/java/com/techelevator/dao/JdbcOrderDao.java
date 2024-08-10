package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Item;
import com.techelevator.model.Side;
import com.techelevator.model.SpecialtyPizza;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOrderDao implements FoodOrderDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order addOrder(Order order) {
        Order newOrder = null;
        String sql = "INSERT INTO orders (user_id, product_id, qty) " +
                "VALUES (?, ?, ?) RETURNING order_id;";
        try {
            int newOrderId = jdbcTemplate.queryForObject(sql, int.class,
                    order.getCustomerId(), order.getItemId(), order.getQty());

            newOrder = getOrderById(newOrderId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newOrder;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, user_id, product_id, qty " +
                "FROM orders " +
                "WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                orders.add(mapRowToOrder(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return orders;
    }

    public Order getOrderById(int orderId) {
        Order newOrder = null;
        String sql = "SELECT order_id, user_id, product_id, qty " +
                "FROM orders " +
                "WHERE order_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId);
            if (results.next()) {
                newOrder = mapRowToOrder(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return newOrder;
    }

    private Order mapRowToOrder(SqlRowSet rowSet) {
        Order order = new Order();
        order.setOrderId(rowSet.getInt("order_id"));
        order.setUserId(rowSet.getInt("user_id"));
        order.setItemId(rowSet.getInt("product_id"));
        order.setQty(rowSet.getInt("qty"));
        return order;
    }

    @Override
    public List<SpecialtyPizza> getSpecialtyPizzas() {
        return null;
    }

    @Override
    public SpecialtyPizza getSpecialtyPizza(int id) {
        return null;
    }

    @Override
    public List<Side> getSides() {
        return null;
    }

    @Override
    public Side getSide(int id) {
        return null;
    }

    @Override
    public void addPizza(Item pizza) {

    }

    @Override
    public Item getPizzaById(int id) {
        return null;
    }

    @Override
    public Item updatePizza(Item pizza) {
        return null;
    }

    @Override
    public void deletePizza(int id) {

    }
}
