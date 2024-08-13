import { createStore as _createStore } from 'vuex';

const NOTIFICATION_TIMEOUT = 6000;

export function createStore(pizzas, specialtyPizzas, sides) {
  let store = _createStore({
    state: {
      shoppingCart: [],
      notification: null,
    },
    mutations: {
        SET_SPECIALTY_PIZZAS(state, specialtyPizzas) {
          state.specialtyPizzas = specialtyPizzas;
        },
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
        },
        SET_NOTIFICATION(state, notification) {
          // Clear the current notification if one exists
          if (state.notification) {
            this.commit('CLEAR_NOTIFICATION');
          }
  
          if (typeof notification === 'string') {
            // If only a string was sent, create a notification object with defaults
            notification = {
              message: notification,
              type: 'error',
              timeout: NOTIFICATION_TIMEOUT
            }
          } else {
            // Else add default values if needed
            notification.type = notification.type || 'error';
            notification.timeout = notification.timeout || NOTIFICATION_TIMEOUT;
          }
  
          // Set the notification in state
          state.notification = notification;
  
          // Clear the message after timeout (see https://developer.mozilla.org/en-US/docs/Web/API/setTimeout)
          notification.timer = window.setTimeout(() => {
            this.commit('CLEAR_NOTIFICATION');
          }, notification.timeout);
        },
  
        CLEAR_NOTIFICATION(state) {
          if (state.notification && state.notification.timer) {
            window.clearTimeout(state.notification.timer);
          }
          state.notification = null;
        },
      },
    });
    return store;
  }
  