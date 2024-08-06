<template>
<<<<<<< HEAD
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sloppy Joe's Pizza</title>
  </head>
<body>
    <div class="container">
        <header id="page-header">
         <router-link v-bind:to="{ name: 'home' }">
            <img src="@/assets/sloppy_jo_s_pizza_logo.png" alt="Joe's Sloppy Head" class="resize-image"/>
         </router-link>
            <div>
                <ul class="nav-link">
                    <router-link v-bind:to="{name: 'home'}"><li>Home</li></router-link>
                    <router-link v-bind:to="{name: 'menu'}"><li>Menu</li></router-link>
                    <router-link v-bind:to="{name: 'order'}"><li>Order Now</li></router-link>
                </ul>
            </div>
            <nav>
                <ul>
                <li><a href="#">Login</a></li>
                <router-link v-bind:to="{name: 'register'}"><li><a href='#'>Register</a></li></router-link>
                </ul>
            </nav>
        </header>
    <main>
        <h1></h1>










    </main>
    <footer id="page-footer">
      <div class="footer-image">
      <router-link v-bind:to="{ name: 'home' }">
      <img src="@/assets/sloppy_jo_s_pizza_logo.png" alt="Joe's Sloppy Head"  class="resize-image"/>
       </router-link>
       <p>&copy; 2024 Sloppy Joe's Pizza. All rights reserved.</p>
      </div>
      <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2987.979148375994!2d-81.64315672653612!3d41.50472667128512!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8830fbae42fdaa89%3A0xb4f58c976afcbb42!2s7001%20Euclid%20Ave%2C%20Cleveland%2C%20OH%2044103!5e0!3m2!1sen!2sus!4v1722952925730!5m2!1sen!2sus" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>

    </footer>
</div>
</body>
</template>

<script>
import Menu from '../components/Menu.vue';

    export default {

=======
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>News</title>
        <!-- <link rel="stylesheet" href="styles.css"> -->
    </head>

    <body>
    <header>
        <h1> Menu </h1>
    </header>
    <toppings />
    </body>
</template>

<script>
    import Toppings from '../components/Toppings.vue';
    export default {
        components: {
            Toppings
        }
>>>>>>> 4c7c3b4a0281bdc91c8c09904f6627cef85153a7
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