<template>
  <navbar />

  <body>



    <div class="embed-responsive embed-responsive-16by9 pizza-video">
      <router-link v-bind:to="{ name: 'order' }">
        <video class="w-100 m-auto embed-responsive-item" autoplay loop muted>
          <source src="@/assets/fullPizza.mp4" type="video/mp4">
          Your browser does not support the video tag.
        </video>
      </router-link>
    </div>

    <div class="container text-center">
      <h1><strong>Pizza Extra Sloppy is What We Do</strong></h1><br>



      <div class="row">
        <div class="col-sm">
          <router-link class="nonchalant" v-bind:to="{ name: 'order' }">
            <img src="@/assets/Pizzas2.png" class="img-responsive" style="width:100%" alt="Pizza">
            <p>Order Now!</p>
          </router-link>
        </div>
        <div class="col-sm">
          <router-link class="nonchalant" v-bind:to="{ name: 'order' }">
            <img src="@/assets/founderPic.png" class="img-responsive" style="width:100%" alt="Pizza">
            <p>About Us</p>
          </router-link>
        </div>
        <div class="col-sm map-div">
          <div class="well">
            <iframe
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2987.979148375994!2d-81.64315672653612!3d41.50472667128512!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8830fbae42fdaa89%3A0xb4f58c976afcbb42!2s7001%20Euclid%20Ave%2C%20Cleveland%2C%20OH%2044103!5e0!3m2!1sen!2sus!4v1722952925730!5m2!1sen!2sus"
              width="100%" style="border:0;" allowfullscreen="" loading="lazy"
              referrerpolicy="no-referrer-when-downgrade"></iframe>
          </div>
        </div>
      </div>
    </div><br>

    <pizza-footer />
    <!-- 
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script> -->
  </body>
</template>

<script>
import Navbar from '../components/Navbar.vue';
import PizzaFooter from '../components/PizzaFooter.vue';
export default {
  components: {
    Navbar,
    PizzaFooter
  }
}

</script>

<style scoped>
.pizza-video {
  width: 65%;
  margin: auto;
}

.nonchalant {
  text-decoration: none;
  color: rgb(33, 37, 41)
}

.navbar {
  font-size: 24px;
  margin-bottom: 0;
  border-radius: 0;
  color: #e0ba20c7;
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
  .pizza-video {
    width: 100%;
    padding: 0.25em;
  }
  .map-div {
    width: 95%;
    margin: auto;
  }
}


footer {
  background-color: #e0ba20c1;
  padding: 25px;
  font-size: 24px;
  font-family: Impact, Haettenschweiler, 'Arial Bold', sans-serif;
}

body {
  font-family: Impact, Haettenschweiler, 'Arial', sans-serif;
  font-size: 24px;
}

.custom-video-size {
  max-width: 700px;
  /* Adjust width as needed */
  margin: 0 auto;
  /* Horizontally center the container */
}
.map-div {
  background-color: rgba(47, 79, 79, 0.7);
  height: 100%;
}
iframe {
  padding: 0.25em;
  padding-top: 0.5em;
  height: 16.75em;
}
</style>
