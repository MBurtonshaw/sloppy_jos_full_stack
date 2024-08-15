<template>
  <head>
    <title>Sloppy Joe's</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  </head>
  <body>

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

        <div class="col-md-4 text-right">
          <img src="@/assets/founderPic.png" alt="Joe in a cape" class="img-fluid founder-img" />
        </div>
      </div>
    </div>

    <footer class="container-fluid text-center">
      <p>&copy; 2024 Sloppy Jo's Pizza. All rights reserved.</p>
    </footer>

  </body>
</template>

<script>
export default {
    
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

footer {
  background-color: #e0ba20c7;
  padding: 25px;
  font-size: 24px;
  font-family: Impact, Haettenschweiler, 'Arial Bold', sans-serif;
}

@media (max-width: 600px) {
  .carousel-caption {
    display: none; 
  }
}
</style>
