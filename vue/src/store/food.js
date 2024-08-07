import { createStore as _createStore } from 'vuex';

export function createStore(pizzas, specialtyPizzas, sides) {
  let store = _createStore({
    state: {
      pizzas: [pizzas],
      specialtyPizzas: [specialtyPizzas],
      sides: [sides]
    },
    mutations: {
        ADD_TOPPING(topping) {
            this.pizzas.toppings.push(topping);
        }
    },
  });
  
  return store;
}
