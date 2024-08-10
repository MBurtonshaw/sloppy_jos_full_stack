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

    private final FoodOrderDao foodOrderDao;
    private final ToppingDao toppingDao;

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
            List<Integer> toppingIds = pizza.getToppingIds();
            System.out.println(pizza.getToppingIds());
            if (!toppingIds.isEmpty()) {
                toppingDao.addToppings(pizza);
            }
        } catch (DaoException e) {
            // Handle database access exceptions and return an appropriate response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve custom pizza", e);
        }
    }

    @RequestMapping(path = "/menu/byo/{id}", method = RequestMethod.GET)
    public Item addPizza(@PathVariable int id) {
        try {
            // Call the DAO to add the pizza to the database
            return foodOrderDao.getPizzaById(id);
        } catch (DaoException e) {
            // Handle database access exceptions and return an appropriate response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve custom pizza", e);
        }
    }

    @RequestMapping(path = "/menu/byo/{id}", method = RequestMethod.PUT)
    public Item updatePizza(@RequestBody Item pizza) {
        try {
            // Call the DAO to add the pizza to the database
            return foodOrderDao.updatePizza(pizza);
        } catch (DaoException e) {
            // Handle database access exceptions and return an appropriate response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update custom pizza", e);
        }
    }

    @RequestMapping(path = "/menu/byo/{id}", method = RequestMethod.DELETE)
    public void deletePizza(@PathVariable int id) {
        try {
            // Call the DAO to add the pizza to the database
            foodOrderDao.deletePizza(id);
        } catch (DaoException e) {
            // Handle database access exceptions and return an appropriate response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete custom pizza", e);
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

    @RequestMapping(path = "/menu/sides", method = RequestMethod.GET)
    public List<Side> getSides() {
        try {
            return foodOrderDao.getSides();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sides not found", e);
        }
    }

    @RequestMapping(path = "/menu/sides/{id}", method = RequestMethod.GET)
    public Side getSide(@PathVariable int id) {
        try {
            return foodOrderDao.getSide(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sides not found", e);
        }
    }

    @RequestMapping(path = "/order/{id}", method = RequestMethod.GET)
    public FoodOrder getOrder(@PathVariable int id) {
        try {
            return foodOrderDao.getOrder(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

//    @RequestMapping(path = "/side/{id}", method = RequestMethod.POST)
//    public void addSideToOrder(@RequestBody int sideId, @RequestBody int orderId) {
//        try {
//            foodOrderDao.addSideToOrder(orderId, sideId);
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
//        }
//    }

}