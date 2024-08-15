import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  addSpecialtyPizza(orderId, data) {
    return http.post(`/api/orders/${orderId}/specialty`, data);
  },

  addCustomPizza(orderId, data) {
    return http.post(`/api/orders/${orderId}/custom`, data);
  },

  addOrder(data) {
    return http.post('/api/orders', data);
  },

  addSide(orderId, data) {
    return http.post(`/api/orders/${orderId}/side`, data);
  },

  getOrder(orderId) {
    return http.get(`/api/orders/${orderId}`);
  }

  // getProduct(productId) {
  //   return http.get(`/products/${productId}`);
  // },

  // getOrders(token) {
  //   const config = {
  //     headers: {
  //       Authorization: `Bearer ${token}`
  //     }
  //   };
  //   return http.get('/orders', config);
  // },

  // addOrder(order, token) {
  //   const config = {
  //     headers: {
  //       Authorization: `Bearer ${token}`
  //     }
  //   };
  //   return http.post('/orders', order, config);
  // }


}