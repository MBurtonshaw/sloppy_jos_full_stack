<template>
  <navbar />

  <body>



    <div class="container">
      <h3 class="text-center p-4"><strong>Pizza Extra Sloppy is What We Do</strong></h3><br>
      <div class="">
        <div class="Hours">
          <p class="text-right">
            <strong>Hours of Operation</strong><br>
            <span>Monday-Thursday: 11:00 AM - 10:30 PM</span><br>
            <span>Friday-Saturday: 11:00 AM - 2:00 AM</span><br>
          </p>
        </div>
        <div class="text-center p-4">
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2987.979148375994!2d-81.64315672653612!3d41.50472667128512!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8830fbae42fdaa89%3A0xb4f58c976afcbb42!2s7001%20Euclid%20Ave%2C%20Cleveland%2C%20OH%2044103!5e0!3m2!1sen!2sus!4v1722952925730!5m2!1sen!2sus"
            width="350" height="350" style="border:0;" allowfullscreen="" loading="lazy"
            referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
      </div>
    </div><br>
    <div class="pt-2">
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
  }
}
</script>

<style scoped>
body {
  font-family: Impact, Haettenschweiler, 'Arial', sans-serif;
  font-size: 20px;
}

.Hours {
  text-align: center;
}

.custom-img {
  width: 12.5em;
  margin: -40px;
  /* Adjust this value as needed */
  margin-top: -50px;
  padding: 0;
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

@media (max-width: 768px) {
  .navbar-logo {
    max-height: 40px;
  }
}

footer {
  background-color: #e0ba20c1;
  padding: 25px;
  font-size: 24px;
  font-family: Impact, Haettenschweiler, 'Arial Bold', sans-serif;
}

.carousel-inner img {
  width: 100%;
  margin: auto;
  min-height: 200px;
  max-height: fit-content;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media (max-width: 600px) {
  .carousel-caption {
    display: none;
  }
}
</style>
