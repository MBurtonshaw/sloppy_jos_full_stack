<template>
  <navbar />
  <body>
    <div class="container mt-4">
      <div class="text-start">
        <h1 class="text-left cart-summary">Cart Summary</h1>
        <h2 class="total mb-5">Total: ${{ cartTotal }}</h2>
        <table class="table table-bordered mb-5">
          <thead>
            <tr>
              <th>Product</th>
              <th>Price</th>
              <th>Actions</th>
            </tr>
          </thead>
          <!-- If there are items in the cart -->
          <tbody v-if="$store.state.shoppingCart.length > 0">
            <tr v-for="(food, i) in $store.state.shoppingCart" v-bind:key="i">
              <td width="300">
                {{ food.obj.name }}
              </td>
              <td>${{ food.obj.price }}</td>
              <td>
                <input type="button" value="+" @click="addToCart(food)">
                <input type="button" value="-" @click="removeFromCart(food)">
              </td>
            </tr>
          </tbody>
          <!-- If there are no items in the cart -->
          <tbody v-else>
            <tr>
              <td colspan="3">Your Cart is Empty</td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- Checkout button -->
      <div class="submit" style="display: flex; justify-content: center; width: 100%; margin: auto;">
        <p style="margin-top: 1.25em;">Click Me to Checkout!</p>
        <button v-on:click="checkout()" style="border: none; background: none;">
          <img src="@/assets/spLogo.png" alt="Joe's Sloppy Head" class="img-fluid custom-img"
            style="width:12.5em; padding: 0; " />
        </button>
      </div>
    </div>
    <div class="mt-5">
      <pizza-footer />
    </div>
  </body>
</template>

<script>
import Navbar from '../components/Navbar.vue';
import PizzaFooter from '../components/PizzaFooter.vue';

export default {
  components: {
    Navbar,
    PizzaFooter
  },
  data() {
    return {
      checkoutButtonText: 'Click Me to Checkout!',
    };
  },
  computed: {
    cartTotal() {
      return this.$store.state.shoppingCart.reduce((total, food) => {
        return total + food.obj.price;
      }, 0).toFixed(2);
    },
  },
  methods: {
    addToCart(product) {
      this.$store.commit('ADD_TO_CART', product);
    },
    removeFromCart(product) {
      this.$store.commit('REMOVE_FROM_CART', product);
    },
    checkout() {
      this.$router.push('/customerInfo');
    },
  }
}
</script>

<style scoped>
body {
  font-family: Impact, Haettenschweiler, 'Arial', sans-serif;
  font-size: 24px;
}
.navbar {
  font-size: 24px;
  margin-bottom: 0;
  border-radius: 0;
}
.navbar-logo {
  max-height: 100px;
  width: auto;
  margin-top: -25px;
}
.cart-summary {
  margin-top: 2em;
  letter-spacing: 2px;
}
.submit {
  padding-top: 0.5em;
}
@media (max-width: 768px) {
  .navbar-logo {
    max-height: 40px;
  }
}
.custom-img {
  width: 12.5em;
  margin: -40px;
  margin-top: -50px;
  padding: 0;
}
.customer-information {
  padding: 1em 0;
}
.resize-image {
  max-width: 200px;
  max-height: auto;
  margin-top: 20px;
}
.nav-link {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
}
.nav-link li {
  margin-right: 50px;
  font-size: 35px;
}
.nav-link li:last-child {
  margin-right: 50px;
}
.nav-link a {
  text-decoration: none;
  color: black;
}
.nav-link a:hover {
  color: #0000ff;
}
</style>