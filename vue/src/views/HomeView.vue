<template>
  <head>
    <title>Sloppy Joe's Menu</title>
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

  <div class="container mt-4">
    <div class="embed-responsive embed-responsive-16by9">
        <video class="embed-responsive-item" autoplay loop muted>
            <source src="@/assets/fullPizza.mp4" type="video/mp4">
            Your browser does not support the video tag.
        </video>
    </div>
</div>
    
  <div class="container text-center">    
    <h3><strong>Pizza Extra Sloppy is What We Do</strong></h3><br>
    <div class="row">
      <div class="col-sm-4">
        <router-link v-bind:to="{name: 'order'}">
        <img src="@/assets/Pizzas2.png" class="img-responsive"  style="width:100%" alt="Pizza">
        <p><strong>Order Specialty</strong></p>
        </router-link>
      </div>
      <div class="col-sm-4"> 
        <router-link v-bind:to="{name: 'about-us'}">
        <img src="@/assets/spLogo.png" class="img-responsive" style="width:100%" alt="Pizza">
        <p><strong>~We Are Sloppy Jo~</strong></p>
      </router-link>
      </div>
      <div class="col-sm-4">
        <div class="well">
          <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2987.979148375994!2d-81.64315672653612!3d41.50472667128512!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8830fbae42fdaa89%3A0xb4f58c976afcbb42!2s7001%20Euclid%20Ave%2C%20Cleveland%2C%20OH%2044103!5e0!3m2!1sen!2sus!4v1722952925730!5m2!1sen!2sus" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
      </div>
    </div>
  </div><br>

  <footer class="container-fluid text-center">
    <p>&copy; 2024 Sloppy Jo's Pizza. All rights reserved.</p>
  </footer>
<!-- 
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script> -->
  </body>
</template>

<script>
export default {
  
}

</script>

<style scoped>
  .navbar {
      font-size: 24px;
      margin-bottom: 0;
      border-radius: 0;
      color: #e0ba20c7;
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


  footer {
    background-color: #e0ba20c1;;
    padding: 25px;
    font-size: 24px;
    
  }
  




</style>
