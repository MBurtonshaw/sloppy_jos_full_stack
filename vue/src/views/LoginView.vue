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

    <main>
      <div id="login">
        <form v-on:submit.prevent="login">
          <h1 >Please Sign In</h1>
          <div role="alert" v-if="invalidCredentials">
            Invalid username and password!
          </div>
          <div role="alert" v-if="this.$route.query.registration">
            Thank you for registering, please sign in.
          </div>
          <div class="form-input-group">
            <label for="username">Username</label>
            <input type="text" id="username" v-model="user.username" required autofocus />
          </div>
          <div class="form-input-group">
            <label for="password">Password</label>
            <input type="password" id="password" v-model="user.password" required />
          </div>
          <button type="submit">Sign in</button>
          <p>
          <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
        </form>
      </div>
    </main>
    <footer id="page-footer">
      <p></p>
      <div class="footer-image">
      <router-link v-bind:to="{ name: 'home' }">
      <img src="@/assets/spLogo.png" alt="Joe's Sloppy Head"  class="resize-footer"/>
       </router-link>
       <p>&copy; 2024 Sloppy Jo's Pizza. All rights reserved.</p>
      </div>
      <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2987.979148375994!2d-81.64315672653612!3d41.50472667128512!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8830fbae42fdaa89%3A0xb4f58c976afcbb42!2s7001%20Euclid%20Ave%2C%20Cleveland%2C%20OH%2044103!5e0!3m2!1sen!2sus!4v1722952925730!5m2!1sen!2sus" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
    
    </footer>
 

  </body>

</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 15px;
  width: 100%;
  
}

.form-input-group label {
  display: block;
  margin-bottom: 5px;
}

.form-input-group input {
  width: calc(100% - 20px);
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

#login {
  display: flex; 
  justify-content: center; 
  align-items: center; 
  height: 100vh;
  
}

#login form {
  display: flex;
  flex-direction: column; 
  align-items: center; 
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  max-width: 400px;
  width: 100%;
}

label {
  margin-right: 0.5rem;
}

button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

body {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  background-image: url('@/assets/pizza5.png');
  background-position: center;
  background-size: cover;
  

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
  background-color:  #e0ba20c1;;
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