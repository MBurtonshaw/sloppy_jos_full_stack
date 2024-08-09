// import axios from 'axios';

// const http = axios.create({
//   baseURL: import.meta.env.VITE_REMOTE_API
// });

// export default {

//   getProducts() {
//     return http.get('/products');
//   },

//   getProduct(productId) {
//     return http.get(`/products/${productId}`);
//   },

//   getOrders(token) {
//     const config = {
//       headers: {
//         Authorization: `Bearer ${token}`
//       }
//     };
//     return http.get('/orders', config);
//   },

//   addOrder(order, token) {
//     const config = {
//       headers: {
//         Authorization: `Bearer ${token}`
//       }
//     };
//     return http.post('/orders', order, config);
//   }


// }