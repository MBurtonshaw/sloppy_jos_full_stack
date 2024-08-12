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
public class JdbcSideDao implements SideDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSideDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    // Returns a side by id
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


    // Format side based on model
    private Side mapRowToSide(SqlRowSet rowSet) {
        Side side = new Side();

        side.setId(rowSet.getInt("side_id"));
        side.setName(rowSet.getString("side_name"));
        side.setPrice(rowSet.getDouble("base_price"));
        return side;
    }

}
