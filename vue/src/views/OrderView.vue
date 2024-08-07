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
            <li><router-link v-bind:to="{name: 'login'}">Login</router-link></li>
            <li><router-link v-bind:to="{name: 'register'}">Register</router-link></li>
          </ul>
        </div>
      </div>
    </nav>


          <h1></h1>
  
  
  
  
  
  
  
  
  
  

      <footer class="container-fluid text-center">
      <p>&copy; 2024 Sloppy Joe's Pizza. All rights reserved.</p>
    </footer>
  </body>
</template>
  
  <script>
  import Order from '../components/Order.vue';
  
      export default {
  
      }
  </script>
  
  <style scoped>
  body {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    background-color: #a9a9a9;
  }
  
  div.container {
    display: grid;
    grid-template-columns: 300px 1fr 250px;
    grid-template-rows: 100px 1fr 1fr;
    gap: 15px;
    background-color: #a9a9a9;
    grid-template-areas:
      "header header header"
      "main main main"
      "footer footer footer";
  }
  
  #page-header {
    box-shadow: 0 4px 5px 0 rgba(0,0,0,0.14), 0 1px 10px 0 rgba(0,0,0,0.12), 0 2px 4px -1px rgba(0,0,0,0.2);
    padding-left: 10px;
    padding-right: 10px;
    grid-area: header;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #daa520;
  }
  
  #page-footer {
    box-shadow: 0 4px 5px 0 rgba(0,0,0,0.14), 0 1px 10px 0 rgba(0,0,0,0.12), 0 2px 4px -1px rgba(0,0,0,0.2);
    padding-left: 10px;
    padding-right: 10px;
    grid-area: footer;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #daa520;
  }
  
  .resize-image {
    max-width: 200px;
    max-height: auto;
    margin-top: 20px;
  }
  
  footer {
      height: auto;
      padding: 10px 20px;
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
  
  
  
  </style>