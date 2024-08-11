
package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.techelevator.model.User;

public abstract class CustomerDao implements UserDao {

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    public abstract User getCustomerById(int customerId);

    public abstract List<User> getCustomers();

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User createUser(RegisterUserDto user) {
        return null;
    }
}
