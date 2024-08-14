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
            <li><router-link v-bind:to="{ name: 'shopping-cart' }"><img class="navbar-brand"
                  src="@/assets/shopping-cart2.png" alt="shopping cart img"
                  style="width: 65px; height: 65px;"></router-link></li>
            <li><router-link v-bind:to="{ name: 'login' }">Login</router-link></li>
            <li><router-link v-bind:to="{ name: 'register' }">Register</router-link></li>
          </ul>
        </div>
      </div>
    </nav>
  
      <!-- custom pizza grid -->
      <div class="container">
        <h2 class="text-center">Create Your Custom Pizza</h2>
        
  
     
<!-- Size Selection -->
  <div class="form-group">
      <h4>Choose Your Pizza Size</h4>
      <div class="row justify-content-center">
          <div class="col-xs-6 col-sm-2 text-center">
              <label>
                  <input type="radio" name="size" value="Small" v-model="selectedSize">
                  <img src="@/assets/smallPizza.png" alt="Small Size" class="img-responsive pizza-size-img">
                  <div>Small</div>
              </label>
          </div>
          <div class="col-xs-6 col-sm-2 text-center">
              <label>
                  <input type="radio" name="size" value="Medium" v-model="selectedSize">
                  <img src="@/assets/mediumPizza.png" alt="Medium Size" class="img-responsive pizza-size-img">
                  <div>Medium</div>
              </label>
          </div>
          <div class="col-xs-6 col-sm-2 text-center">
              <label>
                  <input type="radio" name="size" value="Large" v-model="selectedSize">
                  <img src="@/assets/largePizza.png" alt="Large Size" class="img-responsive pizza-size-img">
                  <div>Large</div>
              </label>
          </div>
          <div class="col-xs-6 col-sm-2 text-center">
              <label>
                  <input type="radio" name="size" value="Half-Sheet" v-model="selectedSize">
                  <img src="@/assets/halfSheetPizza.png" alt="Half-Sheet Size" class="img-responsive pizza-size-img">
                  <div>Half-Sheet</div>
              </label>
          </div>
          <div class="col-xs-6 col-sm-2 text-center">
              <label>
                  <input type="radio" name="size" value="Full-Sheet" v-model="selectedSize">
                  <img src="@/assets/fullSheetPizza.png" alt="Full-Sheet Size" class="img-responsive pizza-size-img">
                  <div>Full-Sheet</div>
              </label>
          </div>
      </div>
  </div>


  
          <!-- Crust Selection -->
          <div class="form-group">
            <h4>Choose Your Crust</h4>
            <select class="form-control" v-model="selectedCrust">
              <option>Regular</option>
              <option>Thin  </option>
              <option>Pan</option>
            </select>
          </div>
  
          <!-- Sauce Selection -->
          <div class="form-group">
            <h4>Choose Your Sauce</h4>
            <select class="form-control" v-model="selectedSauce">
              <option>Traditional</option>
              <option>Basil-Pesto</option>
              <option>Garlic Parmesean</option>
            </select>
          </div>
  
          <!-- Toppings Selection -->
          <div class="form-group">
            <h4>Choose Your Toppings</h4>
            <div class="row">
              <div class="col-xs-6 col-sm-4" v-for="(topping, index) in toppings" :key="index">
                <label>
                  <input type="checkbox" v-model="selectedToppings" :value="topping">
                  {{ topping.name }}
                </label>
              </div>
            </div>
          </div>
        <button type="button" @click="makePizza()">Make Pizza (temporary button)</button>
        <!-- Add to Cart Button -->
        <button type="button" class="btn btn-primary" @click="addCustomPizzaToCart()">Add to Cart</button>
    </div>

    <footer class="container-fluid text-center">
      <p>&copy; 2024 Sloppy Jo's Pizza. All rights reserved.</p>
    </footer>
  </body>
</template>


<script>
import axios from 'axios';
import Order from '../components/Order.vue';
import foodService from '../services/FoodService';

export default {
  data() {
    return {
      crusts: ['Regular', 'Thin', 'Pan'],
      sauces: ['Traditional', 'Basil-Pesto', 'Garlic Parmesean'],
      toppings: [
        { id: 1, name: 'Pepperoni', price: 1.00 },
        { id: 2, name: 'Bacon', price: 1.00 },
        { id: 3, name: 'Ham', price: 1.00 },
        { id: 4, name: 'Mushroom', price: 1.00 },
        { id: 5, name: 'Black Olives', price: 1.00 },
        { id: 6, name: 'Green Olives', price: 1.00 },
        { id: 7, name: 'Bell Pepper', price: 1.00 },
        { id: 8, name: 'Italian Sausage', price: 1.00 },
        { id: 9, name: 'Onions', price: 1.00 },
        { id: 10, name: 'Pineapple', price: 1.00 },
        { id: 11, name: 'Extra Cheese', price: 1.00 },
        { id: 12, name: 'Prosciutto', price: 1.00 },
        { id: 13, name: 'Meatballs', price: 1.00 },
        { id: 14, name: 'Anchovies', price: 1.00 },
        { id: 15, name: 'Ginger-Infused Pineapple', price: 1.00 },
        { id: 16, name: 'Truffles', price: 1.00 },
        { id: 17, name: 'Artichokes', price: 1.00 },
        { id: 18, name: 'Blue Cheese', price: 1.00 },
        { id: 19, name: 'Jalapenos', price: 1.00 },
        { id: 20, name: 'Ground Beef', price: 1.00 },
        { id: 21, name: 'Sloppy Joe Sauce', price: 1.00 }
      ],
      selectedCrust: '',
      selectedSauce: '',
      selectedSize: '',
      selectedToppings: [],
      customPizza: {

      }
    };
  },
  methods: {
    addTopping(topping) {
      const index = this.selectedToppings.indexOf(topping);

      if (index === -1) {
        // Topping is not in the list, so add it
        this.selectedToppings.push(topping);
      } else {
        // Topping is already in the list, so remove it
        this.selectedToppings.splice(index, 1);
      }
    },
    makePizza() {
      this.customPizza.sauce = this.selectedSauce;
      this.customPizza.crust = this.selectedCrust;
      this.customPizza.toppings = this.selectedToppings;
      this.customPizza.size = this.selectedSize;
      console.log(this.customPizza);
      console.log(this.customPizza.toppings);
    },
    addCustomPizzaToCart() {
      let newPizza = this.makePizza();
      this.$store.commit('ADD_TO_CART', {type: 'Custom', obj: {id: this.customPizza.id, name: 'Custom', price: this.customPizza.price}});
    },
  },
};
</script>

<style scoped>
body {
  font-family: 'Arial', sans-serif;
  font-size: 24px;
}

.navbar {
  font-size: 24px;
  margin-bottom: 0;
  border-radius: 0;
  color: #e0ba20c7;
  font-family: Impact, Haettenschweiler, 'Arial', sans-serif;

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


h4 {
  font-size: 24px;
  padding-left: 5%;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}

/* grid for custom pizza ///////////// */

.container {
  margin-top: 20px;
}

h2 {
  text-align: center;
  margin-bottom: 30px;
}

.form-group {
  margin-bottom: 15px;
}

.checkbox label {
  font-size: 16px;
}

button {
  display: block;
  margin: 20px auto;
}

.pizza-size-img {
    width: 150px;  /* Adjust width as needed */
    height: 150px; /* Adjust height as needed */
    object-fit: cover; /* Ensures the image covers the entire area without distortion */
}

</style>