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


    public FoodOrderController(FoodOrderDao foodOrderDao, ToppingDao toppingDao, SideDao sideDao,
                               SpecialtyPizzaDao specialtyPizzaDao, CustomPizzaDao customPizzaDao) {
        this.foodOrderDao = foodOrderDao;
        this.toppingDao = toppingDao;
        this.sideDao = sideDao;
        this.specialtyPizzaDao = specialtyPizzaDao;
        this.customPizzaDao = customPizzaDao;
    }

    @RequestMapping(path = "/menu/byo", method = RequestMethod.POST)
    public void addPizza(@RequestBody Item pizza) {
        if (pizza == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pizza object cannot be null");
        }
        try {
            // Call the DAO to add the pizza to the database
            customPizzaDao.addPizza(pizza);
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

//    @RequestMapping(path = "/menu/byo/{id}", method = RequestMethod.GET)
//    public Item addPizza(@PathVariable int id) {
//        try {
//            // Call the DAO to add the pizza to the database
//            return customPizzaDao.getPizzaById(id);
//        } catch (DaoException e) {
//            // Handle database access exceptions and return an appropriate response
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve custom pizza", e);
//        }
//    }

//    @RequestMapping(path = "/menu/byo/{id}", method = RequestMethod.PUT)
//    public Item updatePizza(@RequestBody Item pizza, @PathVariable int id) {
//        try {
//            // Call the DAO to add the pizza to the database
//            return customPizzaDao.updatePizza(pizza, id);
//        } catch (DaoException e) {
//            // Handle database access exceptions and return an appropriate response
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update custom pizza", e);
//        }
//    }

//    @RequestMapping(path = "/menu/byo/{id}", method = RequestMethod.DELETE)
//    public void deletePizza(@PathVariable int id) {
//        try {
//            // Call the DAO to add the pizza to the database
//            customPizzaDao.deletePizza(id);
//        } catch (DaoException e) {
//            // Handle database access exceptions and return an appropriate response
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete custom pizza", e);
//        }
//    }

//    @RequestMapping(path = "/order", method = RequestMethod.GET)
//    public List<SpecialtyPizza> getSpecialtyPizzas() {
//        try {
//            return specialtyPizzaDao.getSpecialtyPizzas();
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve specialty pizzas",
//                    e);
//        }
//    }

//    @RequestMapping(path = "/menu/specialty_pizzas/{id}", method = RequestMethod.GET)
//    public SpecialtyPizza getSpecialtyPizza(@PathVariable int id) {
//        try {
//            return specialtyPizzaDao.getSpecialtyPizza(id);
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialty pizza not found", e);
//        }
//    }

//    @RequestMapping(path = "/menu/sides", method = RequestMethod.GET)
//    public List<Side> getSides() {
//        try {
//            return sideDao.getSides();
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sides not found", e);
//        }
//    }

//    @RequestMapping(path = "/menu/sides/{id}", method = RequestMethod.GET)
//    public Side getSide(@PathVariable int id) {
//        try {
//            return sideDao.getSide(id);
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sides not found", e);
//        }
//    }

    @RequestMapping(path = "api/orders/{id}", method = RequestMethod.GET)
    public FoodOrder getOrder(@PathVariable int id) {
        try {
            return foodOrderDao.getOrder(id);
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

//    @RequestMapping(path="api/orders/{id}", method = RequestMethod.GET)
//    public FoodOrder checkoutOrder(@RequestBody FoodOrder order) {
//
//    }

    @RequestMapping(path = "/api/orders/{orderId}/specialty", method = RequestMethod.POST)
    public void addSpecialtyPizzaToOrder(@PathVariable int orderId, @RequestBody SpecialtyDTO specialtyDTO) {
        try {
            foodOrderDao.addSpecialtyPizzaToOrder(orderId, specialtyDTO.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

    @RequestMapping(path = "/api/orders/{orderId}/side", method = RequestMethod.POST)
    public void addSideToOrder(@PathVariable int orderId, @RequestBody SideDTO sideDTO) {
        try {
            foodOrderDao.addSideToOrder(orderId, sideDTO.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

//    @RequestMapping(path = "/menu/specialty_pizzas/{id}", method = RequestMethod.DELETE)
//    public void deleteSpecialtyPizzaFromOrder(@PathVariable int id, @RequestBody int orderId) {
//        try {
//            foodOrderDao.removeSpecialtyPizzaFromOrder(orderId, id);
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
//        }
//    }

    @RequestMapping(path = "/api/orders/{orderId}/custom", method = RequestMethod.POST)
    public void addCustomPizzaToOrder(@PathVariable int orderId, @RequestBody Item pizza) {
        try {
            int newId = customPizzaDao.addPizza(pizza);
            foodOrderDao.addCustomPizzaToOrder(orderId, newId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }



//    @RequestMapping(path = "/menu/byo/{id}", method = RequestMethod.DELETE)
//    public void removeCustomPizzaFromOrder(@PathVariable int id, @RequestBody int orderId) {
//        try {
//            foodOrderDao.removeCustomPizzaFromOrder(orderId, id);
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
//        }
//    }

    @RequestMapping(path = "/order/{id}", method = RequestMethod.DELETE)
    public FoodOrder removeOrder(@PathVariable int id) {
        try {
            return foodOrderDao.removeOrder(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }

     @RequestMapping(path = "/menu/sides/{id}", method = RequestMethod.POST)
     public void addSideToOrder(@PathVariable int id, @RequestBody int food_order_id)
     {
         try {
            foodOrderDao.addSideToOrder(food_order_id, id);
         } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
         }
     }

    @RequestMapping(path = "/menu/sides/{id}", method = RequestMethod.DELETE)
    public void removeSideFromOrder(@PathVariable int id, @RequestBody int food_order_id)
    {
        try {
            foodOrderDao.removeSideFromOrder(food_order_id, id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", e);
        }
    }


    //addSideToOrder
    //addCustomPizzaToOrder
    //addSpecialtyPizzaToOrder

}