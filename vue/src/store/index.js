import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import foodService from '../services/FoodService';

const NOTIFICATION_TIMEOUT = 6000;

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      shoppingCart: [],
      orders: [],
      orderId: '',
      pizzas: {
        toppings: []
      },
      specialtyPizzas: [],
      notification: null
    },
    mutations: {
      ADD_TOPPING(state, topping) {
        if (!state.pizzas.toppings.includes(topping)) {
          state.pizzas.toppings.push(topping);
        }
      },
      REMOVE_TOPPING(state, topping) {
        const index = state.pizzas.toppings.indexOf(topping);
        if (index !== -1) {
          state.pizzas.toppings.splice(index, 1);
        }
      },
      SET_SPECIALTY_PIZZAS(state, specialtyPizzas) {
        state.specialtyPizzas = specialtyPizzas;
      },
      ADD_TO_CART(state, item) {
        console.log(item);
        state.shoppingCart.push(item);
      },
      REMOVE_FROM_CART(state, item) {
        const index = state.shoppingCart.indexOf(item);
        if (index !== -1) {
          state.shoppingCart.splice(index, 1);
        }
      },
      CLEAR_CART(state) {
        state.shoppingCart = [];
      },
      async CHECKOUT(state) {
        try {
          // Add the order
          const order = { user: 1, customer: 1 };
          const response = await foodService.addOrder(order);

          // Add items to the order
          const orderId = response.data.order;

          for (const item of state.shoppingCart) {
            if (item.type === 'Specialty') {
              try {
                await foodService.addSpecialtyPizza(orderId, item.obj);
              } catch (error) {
                console.error('Error adding specialty pizza:', error);
                state.notification = {
                  message: 'Failed to add specialty pizza.',
                  type: 'error',
                  timeout: NOTIFICATION_TIMEOUT
                };
              }
            } else if (item.type === 'Custom') {
              try {
                await foodService.addCustomPizza(orderId, item.obj);
              } catch (error) {
                console.error('Error adding custom pizza:', error);
                state.notification = {
                  message: 'Failed to add custom pizza.',
                  type: 'error',
                  timeout: NOTIFICATION_TIMEOUT
                };
              }
            } else if (item.type === 'Side') {
              try {
                await foodService.addSide(orderId, item.obj);
              } catch (error) {
                console.error('Error adding side order:', error);
                state.notification = {
                  message: 'Failed to add side order.',
                  type: 'error',
                  timeout: NOTIFICATION_TIMEOUT
                };
              }
            }
          }
          
          // Clear the cart after successful checkout
          state.shoppingCart = [];

        } catch (error) {
          console.error('Error during checkout:', error);
          state.notification = {
            message: 'Checkout failed, please try again.',
            type: 'error',
            timeout: NOTIFICATION_TIMEOUT
          };
        }
      },
      SET_NOTIFICATION(state, notification) {
        if (state.notification && state.notification.timer) {
          clearTimeout(state.notification.timer);
        }

        if (typeof notification === 'string') {
          notification = {
            message: notification,
            type: 'error',
            timeout: NOTIFICATION_TIMEOUT
          };
        } else {
          notification.type = notification.type || 'error';
          notification.timeout = notification.timeout || NOTIFICATION_TIMEOUT;
        }

        state.notification = notification;

        notification.timer = setTimeout(() => {
          state.notification = null;
        }, notification.timeout);
      },
      CLEAR_NOTIFICATION(state) {
        if (state.notification && state.notification.timer) {
          clearTimeout(state.notification.timer);
        }
        state.notification = null;
      },
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
    },
  });
  return store;
}