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
public class FoodOrderController {

    private FoodOrderDao foodOrderDao;
    private ToppingDao toppingDao;

    private final String API_BASE_URL = "http://localhost:9000/";
    public FoodOrderController(FoodOrderDao foodOrderDao, ToppingDao toppingDao) {
        this.foodOrderDao = foodOrderDao;
        this.toppingDao = toppingDao;
    }

    @RequestMapping(path = "/menu/byo", method = RequestMethod.POST)
    public void addPizza(@RequestBody Item pizza) {
        if (pizza == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pizza object cannot be null");
        }
        try {
            // Call the DAO to add the pizza to the database
            foodOrderDao.addPizza(pizza);
            List<Topping> toppings = pizza.getToppings();
            if (!toppings.isEmpty()) {
                toppingDao.addToppings(pizza);
            }
        } catch (DaoException e) {
            // Handle database access exceptions and return an appropriate response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve custom pizza", e);
        }
    }

    @RequestMapping(path = "/menu/specialty_pizzas", method = RequestMethod.GET)
    public List<SpecialtyPizza> getSpecialtyPizzas() {
        try {
            return foodOrderDao.getSpecialtyPizzas();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve specialty pizzas", e);
        }
    }

    @RequestMapping(path = "/menu/specialty_pizzas/{id}", method = RequestMethod.GET)
    public SpecialtyPizza getSpecialtyPizza(@PathVariable int id) {
        try {
            return foodOrderDao.getSpecialtyPizza(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialty pizza not found", e);
        }
    }

}