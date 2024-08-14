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
            <li class="active"><router-link v-bind:to="{name: 'home'}">Home</router-link></li>
            <li><router-link v-bind:to="{name: 'about-us'}">About Us</router-link></li>
            <li><router-link v-bind:to="{name: 'order'}">Order Now</router-link></li>
            <li><router-link v-bind:to="{name: 'contact'}">Contact Us</router-link></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><router-link v-bind:to="{name: 'shopping-cart'}"><img class="navbar-brand" src="@/assets/shopping-cart2.png" alt="shopping cart img" 
              style="width: 65px; height: 65px;"></router-link></li>
            <li><router-link v-bind:to="{name: 'login'}">Login</router-link></li>
            <li><router-link v-bind:to="{name: 'register'}">Register</router-link></li>
          </ul>
        </div>
      </div>
    </nav>
  
      <!-- custom pizza grid -->
      <div class="container">
        <h2 class="text-center">Create Your Custom Pizza</h2>
        <form>
  
      <!-- Size Selection -->
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
                  {{ topping }}
                </label>
              </div>
            </div>
          </div>
  
          <!-- Add to Cart Button -->
          <button type="button" class="btn btn-primary" @click="addToCart">Add to Cart</button>
        </form>
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
        'Pepperoni', 'Bacon', 'Ham', 'Mushroom', 'Black Olives', 'Green Olives',
        'Bell Pepper', 'Italian Sausage', 'Onions', 'Pineapple', 'Extra Cheese',
        'Prosciutto', 'Meatballs', 'Anchioves', 'Ginger-Infused Pineapple', 'Truffles',
        'Artichokes', 'Blue Cheese', 'Jalepenos', 'Ground Beef', 'Sloppy Joe Sauce'
      ],
      selectedCrust: '',
      selectedSauce: '',
      selectedToppings: []
    };
  },
  methods: {
    
    getSpecialtyPizzas() {
            foodService.getProducts()
                .then(response => {
                    this.$store.commit('SET_SPECIALTY_PIZZAS', response.data);
                    this.isLoading = false;
                })
                .catch(error => {
                    if (error.response) {
                        this.$store.commit('SET_NOTIFICATION',
                        "Error getting products. Response received was '" + error.response.statusText + "'.");
                    } else if (error.request) {
                        this.$store.commit('SET_NOTIFICATION', "Error getting products. Server could not be reached.");
                    } else {
                        this.$store.commit('SET_NOTIFICATION', "Error getting products. Request could not be created.");
                    }
                });
            }, 
      addToCart(id, name, price) {
          this.$store.commit('ADD_TO_CART', {type: 'Specialty', obj: {id, name, price}});
      }
  },        
  created() {
        this.getSpecialtyPizzas();
    },
    addCustomPizzaToCart(id, name, price) {
          this.$store.commit('ADD_TO_CART', {type: 'Specialty', obj: {id: id, name: name, price: price}});
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
    max-height: 100px; /* Adjust the max height as needed */
    width: auto; /* Maintain aspect ratio */
    margin-top: -25px;
   }
      @media (max-width: 768px) {
      .navbar-logo {
        max-height: 40px; /* Adjust size for smaller screens */
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