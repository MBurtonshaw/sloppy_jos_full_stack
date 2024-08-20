<template>
    <navbar />
    <div class="container">
      <div class="row">
        <!-- Image Column -->
        <div class="col-md-4 text-center">
          <img src="@/assets/founderPic.png" alt="Joe in a cape" class="img-fluid founder-img" />
        </div>

        <!-- Text Column -->
        <div class="col-md-8 founder-text">
          <h2>Our Founder</h2>
          <p>Sloppy Joe's Pizzeria was founded by Joe Peloso, a passionate pizza enthusiast from Cleveland, Ohio. 
            From a young age, Joe had a deep love for pizza, spending countless hours in the kitchen experimenting with flavors and techniques. 
            This passion eventually led him to create his signature dish, the Sloppy Joe Pizza – a unique twist on the classic comfort food that 
            combines the hearty flavors of a traditional Sloppy Joe with the crispy, cheesy goodness of pizza.</p>
          <p>Joe’s vision for Sloppy Joe's Pizzeria was simple: to create a place where people could enjoy innovative, delicious pizzas in a warm 
            and welcoming atmosphere. His dedication to quality and creativity is reflected in every slice, making Sloppy Joe's Pizzeria a beloved 
            spot for pizza lovers near and far.</p>
          <p>Come visit us and experience the mouthwatering taste of our Sloppy Joe Pizza – a one-of-a-kind creation that embodies Joe's lifelong 
            passion for pizza.</p>
        </div>
      </div>
      <!-- Brick Oven and Developer Text Row -->
      <div class="row">
        <!-- Brick Oven Image Column -->
        <div class="col-md-4 text-center">
          <img src="@/assets/joe.png" alt="oven" class="img-fluid brick-img" />
          <p>The actual Joe Peloso</p>
        </div>

        <!-- Developer Text Column -->
        <div class="col-md-8 founder-text">
          <h2>Sloppy Jo's Developed By:</h2>
          <div class="row">
            <div class="col-xs-6 col-sm-3 text-center">
              <img src="@/assets/armour.png" alt="Developer 1" class="img-fluid dev-img" />
              <p>Matthew Armour</p>
            </div>
            <div class="col-xs-6 col-sm-3 text-center">
              <a class="nonchalant" href="https://verdant-cranachan-f205b0.netlify.app/">
                <img src="@/assets/matt.png" alt="Developer 2" class="img-fluid dev-img" />
                <p>Matt Burtonshaw</p>
              </a>
            </div>
            <div class="col-xs-6 col-sm-3 text-center">
              <img src="@/assets/andrew.png" alt="Developer 3" class="img-fluid dev-img" />
              <p>Andrew Le</p>
            </div>
            <div class="col-xs-6 col-sm-3 text-center">
              <img src="@/assets/bryan.png" alt="Developer 4" class="img-fluid dev-img" />
              <p>Bryan Stewart</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <pizza-footer />

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
    font-size: 24px;
  }
  
.navbar {
  font-size: 24px;
  margin-bottom: 0;
  border-radius: 0;
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

.container {
  margin-top: 20px; /* Add margin to the top to create space */
}

.founder-img {
  width: 100%; /* Ensure image scales properly within the column */
  height: auto; /* Maintain aspect ratio */
  max-width: 100%; /* Prevent the image from exceeding the column size */
  max-height: 500px; /* Limit the maximum height */
  margin-bottom: 20px; /* Add margin at the bottom to prevent overlap */
}

.founder-text {
  font-size: 16px; /* Increase text size */
  line-height: 1.6; /* Adjust line height for readability */
  padding-left: 5%;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}

.founder-text h2 {
  font-size: 32px; /* Increase heading size */
  margin-bottom: 20px;
}



@media (max-width: 600px) {
  .carousel-caption {
    display: none; 
  }
}

.dev-img {
  max-width: 100%; /* Make sure the image scales properly */
  height: auto;
  margin-top: 10px;
  margin-bottom: 10px;
}

.brick-img {
  width: 100%; /* Ensure image scales properly within the column */
  height: auto; /* Maintain aspect ratio */
  max-width: 100%; /* Prevent the image from exceeding the column size */
  max-height: 500px; /* Limit the maximum height */
  margin-bottom: 20px; /* Add margin at the bottom to prevent overlap */
}

.nonchalant {
  text-decoration: none;
  color: rgb(33, 37, 41);
}

</style>
