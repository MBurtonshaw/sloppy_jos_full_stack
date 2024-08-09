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
          <!-- <a class="navbar-brand"> -->
            <img class="navbar-brand" src="@/assets/spLogo.png" alt="Joe's Sloppy Pizza" />
          <!-- </a> -->
        </div>

        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav">
            <li class="active"><router-link v-bind:to="{name: 'home'}">Home</router-link></li>
            <li><router-link v-bind:to="{name: 'menu'}">Menu</router-link></li>
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
      <h3 class="text-center"><strong>Pizza Extra Sloppy is What We Do</strong></h3><br>
      <div class="row">
        <div class="col-sm-4 float-start">
          <router-link v-bind:to="{ name: 'home' }">
      <img src="@/assets/spLogo.png" alt="Joe's Sloppy Head" />
       </router-link>
          <p><strong></strong></p>
        </div>
        <div class="col-sm-4"> 
          <img src="@/assets/Pizzas2.png" class="img-responsive" style="width:100%" alt="Pizza">
          <p><strong>We are here to serve you</strong></p>
        </div>
        <div class="col-sm-4">
            <iframe float-right src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2987.979148375994!2d-81.64315672653612!3d41.50472667128512!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8830fbae42fdaa89%3A0xb4f58c976afcbb42!2s7001%20Euclid%20Ave%2C%20Cleveland%2C%20OH%2044103!5e0!3m2!1sen!2sus!4v1722952925730!5m2!1sen!2sus" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
            <p class="text-right"><strong>Find us at 7001 Euclid Ave. Cleveland, OH 44103</strong></p>
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
        
    }
    

    footer {
      background-color: #e0ba20c7;
      padding: 25px;
      font-size: 24px;
      
    }
    
  .carousel-inner img {
      width: 100%; 
      margin: auto;
      min-height:200px;
      max-height: fit-content;
  }

  /* Hide the carousel text when the screen is less than 600 pixels wide */
  @media (max-width: 600px) {
    .carousel-caption {
      display: none; 
    }
  }


</style>