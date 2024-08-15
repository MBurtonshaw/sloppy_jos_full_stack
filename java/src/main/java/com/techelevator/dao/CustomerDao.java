package com.techelevator.dao;

import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;

import java.util.List;
 abstract class CustomerDao implements UserDao{

    public abstract List<User> getUsers();

    public abstract User getUserById(int id);

    public abstract User getCustomerById(int customerId);

    public abstract List<User> getCustomers();

    public abstract User getUserByUsername(String username);

    public abstract User createUser(RegisterUserDto user);
}
