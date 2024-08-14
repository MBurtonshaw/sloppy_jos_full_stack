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

<div class="container-fluid text-center img-responsive">
  <h1 class="fw-bolder text-center">Our Founder</h1>
  <img src="@/assets/founderPic.png" alt="Jo in a cape" />
</div>

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