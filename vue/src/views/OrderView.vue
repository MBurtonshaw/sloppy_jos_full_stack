<template>

  <navbar />

  <body>

    <address-search />

    <!-- grid for photos -->
    <div class="container mt-3" v-if="isScreenWide">
      <h4 class="text-center my-3">Build Your Own and Make it Extra Sloppy!</h4>
      <div class="row justify-content-center">
        <div class="col-11 col-lg-5 grid-item p-0">
          <div class="card">
            <img src="@/assets/Pizzas3.png" alt="Custom Pizza" class="card-img-top">
            <p class="card-text description">Create Your Own Sloppy Pizza!</p>
            <router-link v-bind:to="{ name: 'custom' }">
              <button class="btn btn-primary add-to-cart-btn">Build Now</button>
            </router-link>
          </div>
        </div>
      </div>
    </div>

    <div class="container mt-3" v-else>
      <h4 class="text-center my-3">Build Your Own andy!</h4>
        <div class="col-12 col-lg-5 m-auto">
          <div class="card text-center">
            <img src="@/assets/Pizzas3.png" alt="Custom Pizza" class="card-img-top">
            <p class="">Create Your Own Sloppy Pizza!</p>
            <router-link class="w-50 m-auto p-2" v-bind:to="{ name: 'custom' }">
              <button class="btn btn-primary">Build Now</button>
            </router-link>
          </div>
      </div>
    </div>

    <specialty-pizzas />

    <sides />

    <pizza-footer />
  </body>
</template>

<script>

import foodService from '../services/FoodService';
import Navbar from '../components/Navbar.vue';
import PizzaFooter from '../components/PizzaFooter.vue';
import AddressSearch from '../components/AddressSearch.vue';
import SpecialtyPizzas from '../components/SpecialtyPizzas.vue';
import Sides from '../components/Sides.vue';
export default {
  components: {
    Navbar,
    PizzaFooter,
    AddressSearch,
    SpecialtyPizzas,
    Sides
  },
  data() {
        return {
            screenWidth: window.innerWidth
        };
    },
    computed: {
        isScreenWide() {
            return this.screenWidth > 768;
        }
    },
    methods: {
        handleResize() {
            this.screenWidth = window.innerWidth;
        }
    },
    mounted() {
        window.addEventListener('resize', this.handleResize);
    },
    beforeDestroy() {
        window.removeEventListener('resize', this.handleResize);
    }
}
</script>

<style scoped>
.navbar {
  font-size: 24px;
  margin-bottom: 0;
  border-radius: 0;
  color: #e0ba20c7;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
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


#page-header {
  box-shadow: 0 4px 5px 0 rgba(0, 0, 0, 0.14), 0 1px 10px 0 rgba(0, 0, 0, 0.12), 0 2px 4px -1px rgba(0, 0, 0, 0.2);
  padding-left: 10px;
  padding-right: 10px;
  grid-area: header;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #daa520;
}

#page-footer {
  box-shadow: 0 4px 5px 0 rgba(0, 0, 0, 0.14), 0 1px 10px 0 rgba(0, 0, 0, 0.12), 0 2px 4px -1px rgba(0, 0, 0, 0.2);
  padding-left: 10px;
  padding-right: 10px;
  grid-area: footer;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #e0ba20c1;
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

.footer-image {
  display: grid;
  place-items: center;
}

.inline-footer-links {
  display: grid;
  grid-template-columns: repeat(3, auto);
  column-gap: 50px;
  justify-content: center;
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



/* grid for photos /////////////////////////////////////////////////////////////////// */
.container {
  padding-bottom: 40px;
}

.row {
  margin-bottom: 40px;
}

.grid-item {
  position: relative;
  overflow: hidden;
  transition: transform 0.3s ease;
  border: 1px solid black;
}

.grid-item:hover {
  transform: scale(1.15);
  z-index: 10;
}

.card-img-top {
  transition: transform 0.3s ease;
  height: 200px;
  object-fit: cover;
}

.grid-item:hover .card-img-top {
  transform: scale(1.1);
}

.description {
  opacity: 0;
  transition: opacity 0.3s ease;
  text-align: center;
  background-color: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 10px;
  border-radius: 5px;
  position: absolute;
  bottom: 20%;
  left: 50%;
  transform: translateX(-50%);
  width: 100%;
  font-size: 0.8em;
}

.grid-item:hover .description {
  opacity: 1;
  margin: 0.5em 0;
}

.add-to-cart-btn {
  position: absolute;
  bottom: 0px;
  left: 50%;
  transform: translateX(-50%);
  opacity: 0;
  transition: opacity 0.3s ease, transform 0.3s ease;
}

.grid-item:hover .add-to-cart-btn {
  opacity: 1;
  transform: translateX(-50%) scale(1.1);
}

h4 {
  font-size: 24px;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}

.padding-left {
  padding-left: 5%;
}

h1,
h3 {

  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}
</style>