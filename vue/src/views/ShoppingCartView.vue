<template>

  <head>
    <title>Sloppy Joe's Menu</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  </head>

  <body>
    <!-- header -->
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand">
            <img class="navbar-logo" src="@/assets/spLogo.png" alt="Joe's Sloppy Pizza" />
          </a>
        </div>

        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav">
            <li class="active"><router-link v-bind:to="{ name: 'home' }">Home</router-link></li>
            <li><router-link v-bind:to="{ name: 'about-us' }">About Us</router-link></li>
            <li><router-link v-bind:to="{ name: 'order' }">Order Now</router-link></li>
            <li><router-link v-bind:to="{ name: 'contact' }">Contact Us</router-link></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><router-link v-bind:to="{name: 'shopping-cart'}"><img class="navbar-brand" src="@/assets/shopping-cart2.png" alt="shopping cart img" 
                  style="width: 65px; height: 65px;"></router-link></li>
            <li><router-link v-bind:to="{ name: 'login' }">Login</router-link></li>
            <li><router-link v-bind:to="{ name: 'register' }">Register</router-link></li>
          </ul>
        </div>
      </div>
    </nav>

  <div class="container mt-4">
    <div class="row">
    <!-- Left Column: Customer Table and Cart Table -->
      <div class="col-md-8">
          <div class="mb-4">
              <h2 class="text-left">Customer Information</h2>
              <form>
                  <table class="table">
                      <tbody>
                          <tr>
                              <td><label for="customerName">Name:</label></td>
                              <td><input type="text" class="form-control" id="customerName" placeholder="Enter your name"></td>
                          </tr>
                          <tr>
                              <td><label for="customerEmail">Email:</label></td>
                              <td><input type="email" class="form-control" id="customerEmail" placeholder="Enter your email"></td>
                          </tr>
                          <tr>
                              <td><label for="customerPhone">Phone Number:</label></td>
                              <td><input type="tel" class="form-control" id="customerPhone" placeholder="Enter your phone number"></td>
                          </tr>
                          <tr>
                              <td><label for="customerAddress">Address:</label></td>
                              <td><textarea class="form-control" id="customerAddress" rows="3" placeholder="Enter your address"></textarea></td>
                          </tr>
                          <tr>
                              <td><label for="creditCardNumber">Credit Card Number:</label></td>
                              <td><input type="text" class="form-control" id="creditCardNumber" placeholder="Enter your credit card number"></td>
                          </tr>
                          <tr>
                              <td><label for="creditCardExpiry">Expiry Date:</label></td>
                              <td><input type="text" class="form-control" id="creditCardExpiry" placeholder="MM/YY"></td>
                          </tr>
                          <tr>
                              <td><label for="creditCardCVV">CVV:</label></td>
                              <td><input type="text" class="form-control" id="creditCardCVV" placeholder="Enter CVV"></td>
                          </tr>
                      </tbody>
                  </table>
              </form>
          </div>
</div>


    <!-- Cart Table //////////////////////////////////////////////// -->
  
  <div class="row">
    <div class="col-md-8">
      <h2 class="text-left">Cart Summary</h2>
      <h2 class="text-right">Total: ${{ cartTotal }}</h2>
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Actions</th>
          </tr>
        </thead>
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
        <tbody v-else>
          <tr>
            <td colspan="3">Your Cart is Empty</td>
          </tr>
        </tbody>
      </table>
      </div>
    </div>


    <!-- jo photo /////////////////////////////////////// -->
    <div class="col-md-4 d-flex justify-content-end align-items-start">
      <button v-on:click="checkout()" style="border: none; background: none; padding: 0;">
        <img src="@/assets/spLogo.png" alt="Joe's Sloppy Head" class="img-fluid" />
      </button>
      <p>Click Me to Checkout!</p>
    </div>
  </div>
</div>


<footer class="container-fluid text-center">
  <p>&copy; 2024 Sloppy Jo's Pizza. All rights reserved.</p>
</footer>
  </body>
</template>

<script>
import ShoppingCart from '../components/ShoppingCart.vue';

export default {
  components: ShoppingCart,
  computed: {
    cartTotal() {
      return this.$store.state.shoppingCart.reduce((total, food) => {
        return total + food.obj.price; // Assuming food.obj.price is the price of each item
      }, 0).toFixed(2); // Fixed to 2 decimal places for currency format
    }
  },
  methods: {
    addToCart(product) {
      this.$store.commit('ADD_TO_CART', product);
    },
    removeFromCart(product) {
      this.$store.commit('REMOVE_FROM_CART', product);
    },
    checkout() {
      this.$store.commit('CHECKOUT');
      //loop thru all objects in store and remove_from_cart upon submission
      this.$store.commit('CLEAR_CART');
      window.alert(
        `Order Submitted`
      );
      this.$router.push('/');
    }
  }

}
</script>

<style scoped>
body {

  background-color: #a9a9a9;
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
  /* Adjust the max height as needed */
  width: auto;
  /* Maintain aspect ratio */
  margin-top: -25px;
}

@media (max-width: 768px) {
  .navbar-logo {
    max-height: 40px;
    /* Adjust size for smaller screens */
  }
}


.resize-image {
  max-width: 200px;
  max-height: auto;
  margin-top: 20px;
}

footer {
  background-color: #e0ba20c7;
  padding: 25px;
  font-size: 24px;
  font-family: Impact, Haettenschweiler, 'Arial Bold', sans-serif;
}


/* nav  ///////////////////////////////////////// */
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