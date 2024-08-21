package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class FoodOrderController {

    private final FoodOrderDao foodOrderDao;
    private final ToppingDao toppingDao;
    private final SideDao sideDao;
    private final SpecialtyPizzaDao specialtyPizzaDao;
    private final CustomPizzaDao customPizzaDao;


    public FoodOrderController(FoodOrderDao foodOrderDao, ToppingDao toppingDao, SideDao sideDao, SpecialtyPizzaDao specialtyPizzaDao, CustomPizzaDao customPizzaDao) {
    this.foodOrderDao = foodOrderDao;
    this.toppingDao = toppingDao;
    this.sideDao = sideDao;
    this.specialtyPizzaDao = specialtyPizzaDao;
    this.customPizzaDao = customPizzaDao;
}


/*
///////////////////////////////////////
/////////////////////////////////////// ORDERS
///////////////////////////////////////
*/
    @RequestMapping(path = "api/orders", method = RequestMethod.GET)
    public List<FoodOrder> getOrders() {
        try {
            return foodOrderDao.getOrders();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

    @RequestMapping(path = "api/orders/{orderId}", method = RequestMethod.GET)
    public FoodOrder getOrder(@PathVariable int orderId) {
        try {
            return foodOrderDao.getOrder(orderId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

    @RequestMapping(path = "api/orders", method = RequestMethod.POST)
    public FoodOrder addOrder(@RequestBody FoodOrder order) {
        try {
            return foodOrderDao.addOrder(order);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }





    @RequestMapping(path = "/order/{id}", method = RequestMethod.DELETE)
    public FoodOrder removeOrder(@PathVariable int id) {
        try {
            return foodOrderDao.removeOrder(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

/*
///////////////////////////////////////
/////////////////////////////////////// CUSTOM
///////////////////////////////////////
*/
    @RequestMapping(path = "/api/orders/{orderId}/custom", method = RequestMethod.POST)
    public void addCustomPizzaToOrder(@PathVariable int orderId, @RequestBody Item pizza) {
        try {
            int newId = customPizzaDao.addCustomPizza(pizza);
            foodOrderDao.addCustomPizzaToOrder(orderId, newId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

    @RequestMapping(path = "/api/orders/{orderId}/custom", method = RequestMethod.GET)
    public Item getCustomPizzaByOrder(@PathVariable int orderId, @RequestBody int id) {
        try {
            // Call the DAO to add the pizza to the database
            return customPizzaDao.getCustomPizzaByOrder(orderId, id);
        } catch (DaoException e) {
            // Handle database access exceptions and return an appropriate response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve custom pizza", e);
        }
    }

//    @RequestMapping(path = "/api/orders/{orderId}/custom", method = RequestMethod.PUT)
//    public Item updateCustomPizza(@RequestBody Item pizza, @PathVariable int id) {
//        try {
//            // Call the DAO to add the pizza to the database
//            return customPizzaDao.updatePizza(pizza, id);
//        } catch (DaoException e) {
//            // Handle database access exceptions and return an appropriate response
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update custom pizza", e);
//        }
//    }

//    @RequestMapping(path = "/api/orders/{orderId}/custom", method = RequestMethod.DELETE)
//    public void deleteCustomPizza(@PathVariable int id) {
//        try {
//            // Call the DAO to add the pizza to the database
//            customPizzaDao.deletePizza(id);
//        } catch (DaoException e) {
//            // Handle database access exceptions and return an appropriate response
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete custom pizza", e);
//        }
//    }

/*
///////////////////////////////////////
/////////////////////////////////////// SPECIALTY
///////////////////////////////////////
*/

    @RequestMapping(path = "/api/orders/{orderId}/specialty", method = RequestMethod.POST)
    public void addSpecialtyPizzaToOrder(@PathVariable int orderId, @RequestBody SpecialtyDTO specialtyDTO) {
        try {
            foodOrderDao.addSpecialtyPizzaToOrder(orderId, specialtyDTO.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

    @RequestMapping(path = "/api/orders/{orderId}/specialty", method = RequestMethod.DELETE)
    public void removeSpecialtyPizzaFromOrder(@PathVariable int orderId, @RequestBody SpecialtyDTO specialtyDTO) {
        try {
            foodOrderDao.removeSpecialtyPizzaFromOrder(orderId, specialtyDTO.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

    @RequestMapping(path = "/api/specialty_pizzas", method = RequestMethod.GET)
    public List<SpecialtyPizza> getSpecialtyPizzas() {
        try {
            return specialtyPizzaDao.getSpecialtyPizzas();
        } catch (DaoException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialty pizza not found", e);
        }
    }

    @RequestMapping(path = "/api/specialty_pizzas/{id}", method = RequestMethod.GET)
    public SpecialtyPizza getSpecialtyPizza(@PathVariable int id) {
        try {
            return specialtyPizzaDao.getSpecialtyPizza(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialty pizza not found", e);
        }
    }

/*
///////////////////////////////////////
/////////////////////////////////////// SIDES
///////////////////////////////////////
*/

    @RequestMapping(path = "/api/orders/{orderId}/side", method = RequestMethod.POST)
    public void addSideToOrder(@PathVariable int orderId, @RequestBody SideDTO sideDTO) {
        try {
            foodOrderDao.addSideToOrder(orderId, sideDTO.getId());
        } catch (DaoException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

    @RequestMapping(path = "/api/orders/{orderId}/side", method = RequestMethod.DELETE)
    public void removeSideFromOrder(@PathVariable int orderId, @RequestBody SideDTO sideDTO) {
        try {
            foodOrderDao.removeSideFromOrder(orderId, sideDTO.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

    @RequestMapping(path = "/api/sides", method = RequestMethod.GET)
    public List<Side> getSides() {
        try {
            return sideDao.getSides();
        } catch (DaoException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sides not found", e);
        }
    }

    @RequestMapping(path = "/api/sides/{id}", method = RequestMethod.GET)
    public Side getSide(@PathVariable int id) {
        try {
            return sideDao.getSide(id);
        } catch (DaoException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sides not found", e);
        }
    }


    //addSideToOrder
    //addCustomPizzaToOrder
    //addSpecialtyPizzaToOrder

}