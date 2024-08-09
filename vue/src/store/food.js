import { createStore as _createStore } from 'vuex';

export function createStore(pizzas, specialtyPizzas, sides) {
  let store = _createStore({
    state: {
      pizzas: [pizzas],
      specialtyPizzas: [specialtyPizzas],
      sides: [sides],
      shoppingCart: [],
      
    },
    mutations: {
        ADD_TOPPING(topping) {
            this.pizzas.toppings.push(topping);
        },
        ADD_CRUST(crust) {
          this.pizzas.crust.push(crust);
        },
        ADD_SAUCE(sauce)  {
          this.pizzas.sauce.push(sauce);
        },
        ADD_SIDE(side) {
          this.sides.side.push(side);
        }
      },
    });
    return store;
  }
  