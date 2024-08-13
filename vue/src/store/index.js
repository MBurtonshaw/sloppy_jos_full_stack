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
    },
    mutations: {
      SET_SPECIALTY_PIZZAS(state, specialtyPizzas) {
        state.specialtyPizzas = specialtyPizzas;
      },
      ADD_TO_CART(state, item) {
        state.shoppingCart.push(item);
      },
      REMOVE_FROM_CART(state, item) {
        const index = state.shoppingCart.indexOf(item);
        if (index !== -1) {
          state.shoppingCart.splice(index, 1);
        }
      },
      CHECKOUT(state) {
        let order = {
          "user" : state.user.id,
          "customer" : state.user.id
        };
        foodService.addOrder(order).then(response => {
          // Iterate over the shopping cart to add specialty pizzas
          state.shoppingCart.forEach(item => {
            if (item.type === 'Specialty') {
              foodService.addSpecialtyPizza(response.data.order, item.obj).catch(error => {
                console.error('Error adding specialty pizza:', error);
              });
            }
          });
        }).catch(error => {
          console.error('Error adding order:', error); // Handle error from addOrder
        });
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
      // ADD_TO_CART(state, product) {
      //   let thisProduct = state.shoppingCart.find((sp) => {
      //     return sp.productId == product.productId;
      //   });
      //   if (thisProduct) {
      //     thisProduct.qty++;
      //   }
      //   else {
      //     let newShoppingProduct = {
      //       productId: product.productId,
      //       qty: 1
      //     };
      //     state.shoppingCart.push(newShoppingProduct);

      //   }
      // },
      // REMOVE_FROM_CART(state, product) {
      //   let thisProduct = state.shoppingCart.find((sp) => {
      //     return sp.productId == product.productId;
      //   });

      //   if (thisProduct) {
      //     if (thisProduct.qty <= 1) {
      //       state.shoppingCart.pop(thisProduct);
      //     }
      //     else {
      //       thisProduct.qty--;
      //     }
      //   }
      // },
      // EMPTY_CART(state) {
      //     state.shoppingCart = [];
      // }
    },
    
  });
  return store; 
}