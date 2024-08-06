<template>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sloppy Joe's Pizza</title>
  </head>

  <header id="page-header">
      <img src="@/assets/sloppy_jo_s_pizza_logo.png" alt="Joe's Sloppy Head" class="resize-image"/>
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
    
  <div id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>

</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
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
