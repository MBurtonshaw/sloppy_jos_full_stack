package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.FoodOrderDao;
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

@RestController
@CrossOrigin
public class FoodOrderController {

    private FoodOrderDao foodOrderDao;


    private final String API_BASE_URL = "http://localhost:9000/";

    public FoodOrderController(FoodOrderDao foodOrderDao) {

    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public void getPizzas() {

    }
}
