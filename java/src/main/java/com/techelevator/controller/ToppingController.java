package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.FoodOrderDao;
import com.techelevator.dao.ToppingDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

import java.util.List;

@RestController
@CrossOrigin
public class ToppingController {

    private FoodOrderDao foodOrderDao;
    private ToppingDao toppingDao;

    private final String API_BASE_URL = "http://localhost:9000/";


    public ToppingController(ToppingDao toppingDao) {
        this.toppingDao = toppingDao;
    }

    @RequestMapping(path = "/menu/byo", method = RequestMethod.POST)
    public void addToppings(@RequestBody Item pizza) {
        if (pizza == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pizza object cannot be null");
        }
        try {
            toppingDao.addToppings(pizza);
        } catch (DaoException e) {
            // Handle database access exceptions and return an appropriate response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve custom pizza", e);
        }
    }

}